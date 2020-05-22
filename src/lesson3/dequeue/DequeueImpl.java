package lesson3.dequeue;

public class DequeueImpl<E> implements Dequeue<E> {

    private static final int DEFAULT_HEAD = 0;
    private static final int DEFAULT_TAIL = -1;

    private final E[] data;
    private int size;
    private int head;
    private int tail;

    @SuppressWarnings("unchecked")
    public DequeueImpl(int maxSize) {
        data = (E[]) new Object[maxSize];
        head = DEFAULT_HEAD;
        tail = DEFAULT_TAIL;
    }

    @Override
    public void insertLeft(E value) {
        if (tail == data.length - 1) tail = DEFAULT_TAIL;
        data[++tail] = value;
        size++;
    }

    @Override
    public void insertRight(E value) {
        if(head == DEFAULT_HEAD) head = data.length;
        data[--head] = value;
        size++;
    }

    @Override
    public E removeLeft() {
        if(tail == DEFAULT_TAIL) tail = data.length - 1;
        E removedValue = data[tail--];
        size--;
        return removedValue;
    }

    @Override
    public E removeRight() {
        if(head == data.length) head = DEFAULT_HEAD;
        E removedValue = data[head++];
        size--;
        return removedValue;
    }

    @Override
    public E peekRight() {
        if(head == data.length) head = DEFAULT_HEAD;
        return data[head];
    }

    @Override
    public E peekLeft() {
        return data[tail];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isFull() {
        return size == data.length;
    }

}
