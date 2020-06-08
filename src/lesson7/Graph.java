package lesson7;

import java.util.*;

public class Graph {

    private final List<Vertex> vertexList;

    private final boolean[][] adjMat;

    public Graph(int maxVertexCount) {
        this.vertexList = new ArrayList<>(maxVertexCount);
        this.adjMat = new boolean[maxVertexCount][maxVertexCount];
    }

    public void addVertex(String label) {
        vertexList.add(new Vertex(label));
    }

    public void addEdges(String first, String second, String... others) {
        addEdge(first, second);
        for (String other : others) {
            addEdge(first, other);
        }
    }

    public int getSize() {
        return vertexList.size();
    }

    public void display() {
        for (int i = 0; i < getSize(); i++) {
            System.out.print(vertexList.get(i));
            for (int j = 0; j < getSize(); j++) {
                if (adjMat[i][j]) {
                    System.out.print(" -> " + vertexList.get(j));
                }
            }
            System.out.println();
        }
    }

    public void dfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid start label");
        }

        Stack<Vertex> stack = new Stack<>();

        Vertex vertex = vertexList.get(startIndex);
        visitVertex(vertex, stack);

        while (!stack.isEmpty()) {
            vertex = getNearestUnvisited(stack.peek());
            if (vertex != null) {
                visitVertex(vertex, stack);
            }
            else {
                stack.pop();
            }
        }
        resetVertexState();

    }

    public void bfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid start label");
        }

        Queue<Vertex> queue = new LinkedList<>();

        Vertex vertex = vertexList.get(startIndex);
        visitVertex(vertex, queue);

        while (!queue.isEmpty()) {
            vertex = getNearestUnvisited(queue.peek());
            if (vertex != null) {
                visitVertex(vertex, queue);
            }
            else {
                queue.remove();
            }
        }
        resetVertexState();
    }

    public void shortestPath(String startLabel, String finishLabel) {
        int startIndex = indexOf(startLabel);
        int finishIndex = indexOf(finishLabel);
        if (startIndex == -1 || finishIndex == -1) {
            throw new IllegalArgumentException("Invalid start/finish label");
        }

        Queue<Vertex> queue = new LinkedList<>();

        Vertex vertex = vertexList.get(startIndex);
        visitVertex(vertex, queue);

        while (!queue.isEmpty()) {
            vertex = getNearestUnvisited(queue.peek());
            if (vertex != null) {
                visitVertex(vertex, queue);
                vertex.setPrev(queue.peek());
                if (vertex.equals(vertexList.get(finishIndex))) break;
            }
            else {
                queue.remove();
            }
        }
        displayPath(vertex);
        resetVertexState();
    }





    /*
    * privet
    * */

    private void displayPath(Vertex vertex) {
        Stack<Vertex> stack = new Stack<>();
        while (vertex != null) {
            stack.push(vertex);
            vertex = vertex.getPrev();
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    private int indexOf(String vertexLabel) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexLabel.equals(vertexList.get(i).getLabel())) {
                return i;
            }
        }

        return -1;
    }

    private void addEdge(String start, String finish) {
        int startIndex = indexOf(start);
        int finishIndex = indexOf(finish);

        if (startIndex == -1 || finishIndex == -1) {
            throw new IllegalArgumentException("Invalid argument for edge");
        }

        adjMat[startIndex][finishIndex] = true;
        adjMat[finishIndex][startIndex] = true;
    }

    private void displayVertex(Vertex vertex) {
        System.out.println(vertex);
    }

    private void visitVertex(Vertex vertex, Stack<Vertex> stack) {
        displayVertex(vertex);
        stack.push(vertex);
        vertex.setVisited(true);
    }

    private void visitVertex(Vertex vertex, Queue<Vertex> queue) {
//        displayVertex(vertex);
        queue.add(vertex);
        vertex.setVisited(true);
    }

    private void resetVertexState() {
        for (Vertex vertex : vertexList) {
            vertex.setVisited(false);
            vertex.setPrev(null);
        }
    }

    private Vertex getNearestUnvisited(Vertex peek) {
        int peekIndex = vertexList.indexOf(peek);
        for (int i = 0; i < getSize(); i++) {
            if (adjMat[peekIndex][i] && !vertexList.get(i).isVisited()) {
                return vertexList.get(i);
            }
        }
        return null;
    }
}
