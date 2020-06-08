package lesson3.dequeue;

public interface Dequeue<E> {



    boolean insertLeft (E value);
    boolean insertRight (E value);

    E removeLeft();
    E removeRight();

    E peekRight();
    E peekLeft();

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    boolean isFull();

}
