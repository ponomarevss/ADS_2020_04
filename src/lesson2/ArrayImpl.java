package lesson2;

import java.util.Arrays;

public class ArrayImpl<E extends Object & Comparable<? super E>> implements Array<E> {

    protected E[] data;
    protected int size;

    public ArrayImpl(int initialCapacity) {
        this(null, initialCapacity);
    }

    public ArrayImpl(E... data) {
        this(data, data.length);
        this.size = data.length;
    }

    public ArrayImpl() {
        this(null, INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayImpl(E[] data, int capacity) {
        this.data = data != null ? data : (E[]) new Object[capacity];
        this.size = data != null ? data.length : 0;
    }

    @Override
    public void add(E value) {
        checkAndGrow();
        data[size++] = value;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    @Override
    public boolean remove(E value) {
        int index = indexOf(value);
        if (index == -1) return false;
        try {
            return remove(index) != null;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E removedValue = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
        return removedValue;
    }

    @Override
    public int indexOf(E value) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void sortBubble() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (data[j].compareTo(data[j + 1]) > 0) {
                    swap(j, j + 1);
                }
            }
        }
    }

    @Override
    public void sortSelect() {
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (data[j].compareTo(data[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(minIndex, i);
            }
        }
    }

    @Override
    public void sortInsert() {
        for (int i = 1; i < size; i++) {
            E temp = data[i];
            int in = i;
            while (in > 0 && data[in - 1].compareTo(temp) > 0) {
                data[in] = data[in - 1];
                in--;
            }
            data[in] = temp;
        }
    }

    private void checkAndGrow() {
        if (data.length == size) {
            data = Arrays.copyOf(data, data.length * 2);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= data.length) {
            String errMsg = String.format("Invalid index %d for array with size %d", index, size);
            throw new IndexOutOfBoundsException(errMsg);
        }
    }

    private void swap(int i, int j) {
        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public E[] getData() {
        return data;
    }
}
