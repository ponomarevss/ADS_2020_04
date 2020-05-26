package lesson4;

public interface ListIterator<E>{

    void reset();

    boolean atEnd();

    void nextEntry();

    E getCurrent();

    void insertAfter(E value);

    void insertBefore(E value);

    E deleteCurrent();
}
