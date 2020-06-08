package lesson4;

import java.util.Iterator;

public class SimpleLinkedListImpl<E> implements LinkedList<E> {

    protected Entry<E> firstElement;
    protected int size;

    @Override
    public void insertFirst(E value) {
        Entry<E> entry = new Entry<>(value);
        entry.next = firstElement;
        firstElement = entry;
        size++;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) return null;

        E removedValue = firstElement.value;
        firstElement = firstElement.next;
        size--;
        return removedValue;
    }

    @Override
    public boolean remove(E value) {
        if (isEmpty()) return false;
        Entry<E> prev = null;
        Entry<E> current = firstElement;

        while (current != null) {
            if(current.value.equals(value)) {
                break;
            }
            prev = current;
            current = current.next;
        }
        if(current == null) {
            return false;
        }
        if (current == firstElement) {
            firstElement = firstElement.next;
        }
        else {
            prev.next = current.next;
        }
        size--;
        return true;
    }

    @Override
    public boolean contains(E value) {
        if (isEmpty()) return false;
        Entry<E> current = firstElement;

        while (current != null) {
            if(current.value.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void display() {
        System.out.println("----------");
        Entry<E> current = firstElement;
        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
        System.out.println("----------");
    }

    @Override
    public E getFirst() {
        return firstElement.value;
    }

    public ListIteratorImpl<E> getTestIterator() {
        return  new ListIteratorImpl<>(this);
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Entry<E> current = firstElement;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E result = current.value;
                current = current.next;
                return result;
            }
        };
    }
}