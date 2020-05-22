package lesson3.stack;

public interface Stack<E> {

    boolean push(E value);

    E pop();

    E peek();

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    boolean isFull();

}
