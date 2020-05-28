package lesson4;

public class ListIteratorImpl<E> implements ListIterator<E> {

    private LinkedList.Entry<E> current;
    private LinkedList.Entry<E> previous;
    private final SimpleLinkedListImpl<E> linkedList;

    public ListIteratorImpl(SimpleLinkedListImpl<E> linkedList) {
        this.linkedList = linkedList;
        this.reset();
    }

    @Override
    public void reset() {
        current = linkedList.firstElement;
        previous = null;
    }

    @Override
    public boolean atEnd() {
        return (current.next == null);
    }

    @Override
    public void nextEntry() {
        previous = current;
        current = current.next;
    }

    @Override
    public E getCurrent() {
        return current.value;
    }

    @Override
    public void insertAfter(E value) {
        LinkedList.Entry<E> newEntry = new LinkedList.Entry<>(value);
        if(linkedList.isEmpty()) {
            linkedList.firstElement = newEntry;
            current = newEntry;
        }
        else {
            newEntry.next = current.next;
            current.next = newEntry;
            nextEntry();
        }
    }

    @Override
    public void insertBefore(E value) {
        LinkedList.Entry<E> newEntry = new LinkedList.Entry<>(value);
        if(previous == null) {
            newEntry.next = linkedList.firstElement;
            linkedList.firstElement = newEntry;
            reset();
        }
        else {
            newEntry.next = previous.next;
            previous.next = newEntry;
            current = newEntry;
        }
    }
    @Override
    public E deleteCurrent() {
        E value = current.value;
        if(previous == null) {
            linkedList.firstElement = current.next;
            reset();
        }
        else {
            previous.next = current.next;
            if(atEnd()) reset();
            else current = current.next;
        }
        return value;
    }
}
