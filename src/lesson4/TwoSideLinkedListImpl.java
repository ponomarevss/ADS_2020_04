package lesson4;

public class TwoSideLinkedListImpl<E> extends SimpleLinkedListImpl<E> implements TwoSideLinkedList<E> {


    Entry<E> lastElement;

    @Override
    public void insertLast(E value) {
        Entry<E> entry = new Entry<>(value);
        if(isEmpty()) {
            firstElement = entry;
        }
        else {
            lastElement.next = entry;
        }
        lastElement = entry;
        size++;
    }

    @Override
    public void insertFirst(E value) {
        super.insertFirst(value);
        if(size == 1) lastElement = firstElement;
    }

    @Override
    public E removeFirst() {
        E removedValue = super.removeFirst();
        if (isEmpty()) lastElement = null;
        return removedValue;
    }

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
        else if (current == lastElement) {
            lastElement = prev;
            prev.next = null;
        }
        else {
            prev.next = current.next;
        }
        size--;
        return true;
    }
}
