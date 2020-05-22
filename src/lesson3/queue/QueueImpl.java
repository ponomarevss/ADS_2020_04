package lesson3.queue;

public class QueueImpl<E> implements Queue<E> {

    private static final int DEFAULT_HEAD = 0;
    private static final int DEFAULT_TAIL = -1;

    protected final E[] data;
    protected int size;
    private int head;
    private int tail;

    @SuppressWarnings("unchecked")
    public QueueImpl(int maxSize) {
        data = (E[]) new Object[maxSize];
        head = DEFAULT_HEAD;
        tail = DEFAULT_TAIL;
    }

    @Override
    public void insert(E value) {
        if (tail == data.length - 1) tail = DEFAULT_TAIL;
        data[++tail] = value;
        size++;
    }

    @Override
    public E remove() {
        if(head == data.length) head = DEFAULT_HEAD;
        E removedValue = data[head++];
        size--;
        return removedValue;
    }

    @Override
    public E peekFront() {
        if(head == data.length) head = DEFAULT_HEAD;
        return data[head];
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
