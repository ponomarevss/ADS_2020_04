package lesson3.stack;


public class StackImpl<E> implements Stack<E> {

    private final E[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public StackImpl(int maxSize) {
        this.data = (E[]) new Object[maxSize];
    }

    @SafeVarargs
    public StackImpl(E... data) {
        this.data = data;
        this.size = data.length;
    }


    @Override
    public void push(E value) {
//        if(isFull()) return false;
        data[size] = value;
        size++;
//        return true;
    }

    @Override
    public E pop() {
        if(isEmpty()) return null;
        return data[--size];
    }

    @Override
    public E peek() {
        return data[size - 1];
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
