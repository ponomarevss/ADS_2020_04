package lesson6;

public interface IntTree {


    enum TraverseMode {
        IN_ORDER,
        PRE_ORDER,
        POST_ORDER,
    }

    boolean add(int value);

    boolean contains(int value);

    boolean remove(int value);

    boolean isEmpty();

    int size();

    void display();

    void traverse(TraverseMode mode);

    boolean isBalanced();
}
