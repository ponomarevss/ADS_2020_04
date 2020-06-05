package lesson6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import static lesson6.Constants.INITIAL_LEVEL;
import static lesson6.Constants.MAX_LEVEL;

public class IntTreeImpl implements IntTree {


    private IntNode root;
    private int size;

    @Override
    public boolean add(int value) {
        IntNode newNode = new IntNode(value);
        if (isEmpty()) {
            root = newNode;
            size++;
            return true;
        }

        NodeAndItsParent nodeAndItsParent = findParentForNewNode(value);
        IntNode parent = nodeAndItsParent.parent;
//        IntNode parent = findParentForNewNode(value);
        if (parent == null || nodeAndItsParent.level >= MAX_LEVEL) {
            return false;
        }
        else if (value > parent.getValue()) {
            parent.setRightChild(newNode);
        }
        else {
            parent.setLeftChild(newNode);
        }
        size++;
        return true;
    }

    @Override
    public boolean contains(int value) {
        return doFind(value).current != null;
    }

    @Override
    public boolean remove(int value) {
        NodeAndItsParent nodeAndItsParent = doFind(value);
        IntNode removedNode = nodeAndItsParent.current;
        IntNode parent = nodeAndItsParent.parent;

        if (removedNode == null) {
            return false;
        }
        if (removedNode.isLeaf()) {
            removeLeafNode(removedNode, parent);
        }
        else if (hasOnlySingleChildNode(removedNode)) {
            removeNodeWithOnlySingleChild(removedNode, parent);
        }
        else {
            removeCommonNode(removedNode, parent);
        }
        size--;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void display() {
        Stack<IntNode> globalStack = new Stack<>();
        globalStack.push(root);
        int nBlanks = 64;

        boolean isRowEmpty = false;
        System.out.println("......................................................................................................................");

        while (!isRowEmpty) {
            Stack<IntNode> localStack = new Stack<>();

            isRowEmpty = true;
            for (int i = 0; i < nBlanks; i++) {
                System.out.print(" ");
            }

            while (!globalStack.isEmpty()) {
                IntNode tempNode = globalStack.pop();
                if (tempNode != null) {
                    System.out.print(tempNode.getValue());
                    localStack.push(tempNode.getLeftChild());
                    localStack.push(tempNode.getRightChild());
                    if (tempNode.getLeftChild() != null || tempNode.getRightChild() != null) {
                        isRowEmpty = false;
                    }
                }
                else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(" ");
                }
            }

            System.out.println();

            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }

            nBlanks /= 2;
        }
        System.out.println("......................................................................................................................");
    }

    @Override
    public void traverse(TraverseMode mode) {
        switch (mode) {
            case IN_ORDER:
                inOrder(root);
                break;
            case PRE_ORDER:
                preOrder(root);
                break;
            case POST_ORDER:
                postOrder(root);
                break;
            default:
                throw new IllegalArgumentException("Unknown mode: " + mode);
        }
    }

    @Override
    public boolean isBalanced() {
        IntNode current = root;
        if (current.getLeftChild() == null || current.getRightChild() == null) return false;
        List<Integer> leafLevelList = new ArrayList<>();
        getLeavesLevels(current, leafLevelList, INITIAL_LEVEL);
//        for (Integer leafLevel : leafLevelList) {
//            System.out.println(leafLevel);
//        }
        return Collections.max(leafLevelList) - Collections.min(leafLevelList) <= 1;
    }

    private void getLeavesLevels(IntNode current, List<Integer> list, int level) {
        level++;
        if (current == null) {
            return;
        }
        getLeavesLevels(current.getLeftChild(), list, level);
        if (current.isLeaf()) list.add(level);
        getLeavesLevels(current.getRightChild(), list, level);
    }

    private void inOrder(IntNode current) {
        if (current == null) {
            return;
        }
        inOrder(current.getLeftChild());
        System.out.println(current.getValue());
        inOrder(current.getRightChild());
    }

    private void preOrder(IntNode current) {
        if (current == null) {
            return;
        }
        System.out.println(current.getValue());
        preOrder(current.getLeftChild());
        preOrder(current.getRightChild());
    }

    private void postOrder(IntNode current) {
        if (current == null) {
            return;
        }
        postOrder(current.getLeftChild());
        postOrder(current.getRightChild());
        System.out.println(current.getValue());
    }

    private void removeCommonNode(IntNode removedNode, IntNode parent) {
        IntNode successor = getSuccessor(removedNode);
        if (removedNode == root) {
            root = successor;
        }
        else if (parent.getLeftChild() == removedNode) {
            parent.setLeftChild(successor);
        }
        else {
            parent.setRightChild(successor);
        }
        successor.setLeftChild(removedNode.getLeftChild());
    }

    private IntNode getSuccessor(IntNode removedNode) {
        IntNode successor = removedNode;
        IntNode successorParent = null;
        IntNode current = removedNode.getRightChild();

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = successor.getLeftChild();
        }
        if (successor != removedNode.getRightChild() && successorParent != null) {
            successorParent.setLeftChild(successor.getRightChild());
            successor.setRightChild(removedNode.getRightChild());
        }
        return successor;
    }

    private void removeNodeWithOnlySingleChild(IntNode removedNode, IntNode parent) {
        IntNode childNode = removedNode.getLeftChild() != null
                ? removedNode.getLeftChild()
                : removedNode.getRightChild();
        if (removedNode == root) {
            root = childNode;
        }
        else if (parent.getLeftChild() == removedNode) {
            parent.setLeftChild(childNode);
        }
        else {
            parent.setRightChild(childNode);
        }
    }

    private boolean hasOnlySingleChildNode(IntNode removedNode) {
        return removedNode.getLeftChild() != null ^ removedNode.getRightChild() != null;
    }

    private void removeLeafNode(IntNode removedNode, IntNode parent) {
        if (parent == null) {
            root = null;
        }
        else if (parent.getLeftChild() == removedNode) {
            parent.setLeftChild(null);
        }
        else {
            parent.setRightChild(null);
        }
    }

    private NodeAndItsParent findParentForNewNode(int value) {
        return doFind(value);
    }
//    private IntNode findParentForNewNode(int value) {
//        return doFind(value).parent;
//    }

    private NodeAndItsParent doFind(int value) {
        IntNode parent = null;
        IntNode current = root;
        int level = 0;
        while (current != null) {
            if (current.getValue() == value) {
                return new NodeAndItsParent(current, parent, level);
            }
            parent = current;
            if (value > current.getValue()) {
                current = current.getRightChild();
            }
            else {
                current = current.getLeftChild();
            }
            level++;
        }
        return new NodeAndItsParent(null, parent, level);
    }


    private class NodeAndItsParent {
        IntNode current;
        IntNode parent;
        int level;

        public NodeAndItsParent(IntNode current, IntNode parent, int level) {
            this.current = current;
            this.parent = parent;
            this.level = level;
        }
    }
}
