package lesson3.stack;

import java.util.Objects;

public class StackImpl<E> implements Stack<E> {

    private final E[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public StackImpl(int maxSize) {
        this.data = (E[]) new Object[maxSize];
    }

    public StackImpl(E... data) {
        this.data = data;
        this.size = data.length;
    }


    @Override
    public void push(E value) {
        data[size] = value;
        size++;
    }

    @Override
    public E pop() {
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
