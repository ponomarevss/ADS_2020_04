package lesson6;

public class IntNode {


    private final int value;
    private IntNode leftChild;
    private IntNode rightChild;

    public IntNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public IntNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(IntNode leftChild) {
        this.leftChild = leftChild;
    }

    public IntNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(IntNode rightChild) {
        this.rightChild = rightChild;
    }

    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }
}
