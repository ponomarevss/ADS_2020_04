package lesson4;

import lesson3.queue.Queue;
import lesson3.stack.Stack;

public class Test {
    public static void main(String[] args) {
        linkedListTest();
//        linkedStackTest();
//        linkedQueueTest();
//        iteratorTest();
    }

    private static void iteratorTest() {


        SimpleLinkedListImpl<Integer> linkedList = new SimpleLinkedListImpl<>();
//        Iterator<Integer> integerIterator = new IteratorImpl<>(linkedList);

        linkedList.insertFirst(10);//10
        linkedList.insertFirst(20);//20 10
        linkedList.insertFirst(30);//30 20 10
        ListIteratorImpl<Integer> integerIterator = linkedList.getTestIterator();

        integerIterator.insertAfter(3);//30 3 20 10
        integerIterator.insertAfter(2);//30 3 2 20 10

        integerIterator.nextEntry();
        System.out.println(integerIterator.getCurrent());//20
        integerIterator.insertBefore(1);//30 3 2 1 20 10
        while (!integerIterator.atEnd()) integerIterator.nextEntry();
        System.out.println(integerIterator.deleteCurrent());//10
        integerIterator.reset();
        System.out.println(integerIterator.deleteCurrent());//30
        linkedList.display();//3 2 1 20
    }


    private static void linkedQueueTest() {

        final int MAX_SIZE = 5;
        final int QUEUE_SIZE = 10;

//        Queue<Integer> queue = new QueueImpl<>(MAX_SIZE);
        Queue<Integer> queue = new LinkedQueueImpl<>();

        for (int i = 1; i <= QUEUE_SIZE; i++) {
            System.out.printf("insert %d: %b\n", i, addToQueue(queue, i));
        }

        System.out.println("size: " + queue.size());
        System.out.println("peekFront: " + queue.peekFront());

        while (!queue.isEmpty()) System.out.println("pop: " + queue.remove());

    }

    private static void linkedStackTest() {

        final int MAX_SIZE = 5;
        final int STACK_SIZE = 10;

//        Stack<Integer> stack = new StackImpl<>(MAX_SIZE);
        Stack<Integer> stack = new LinkedStackImpl<>();

        for (int i = 1; i <= STACK_SIZE; i++) {
            System.out.printf("push %d: %b\n", i, addToStack(stack, i));
        }

        System.out.println("size: " + stack.size());
        System.out.println("peek: " + stack.peek());

        while (!stack.isEmpty()) System.out.println("pop: " + stack.pop());
    }

    private static boolean addToStack(Stack<Integer> stack, int value) {
        if(stack.isFull()) return false;
        stack.push(value);
        return true;
    }

    private static boolean addToQueue(Queue<Integer> queue, int value) {
        if(queue.isFull()) return false;
        queue.insert(value);
        return true;
    }

    private static void linkedListTest() {
        LinkedList<Integer> linkedList = new SimpleLinkedListImpl<>();
//        TwoSideLinkedList<Integer> linkedList = new TwoSideLinkedListImpl<>();

        linkedList.insertFirst(1);
        linkedList.insertFirst(2);
        linkedList.insertFirst(3);
        linkedList.insertFirst(4);
//        linkedList.insertLast(0);
//        linkedList.insertLast(-1);

        for(Integer entry : linkedList) {
            System.out.println("forEach: " + entry);
        }

//        linkedList.display();
//
//        System.out.println("Find 1: " + linkedList.contains(1));
//        System.out.println("Find 2: " + linkedList.contains(2));
//        System.out.println("Find 3: " + linkedList.contains(3));
//        System.out.println("Find 404: " + linkedList.contains(404));
//
//        System.out.println("linkedList.removeFirst() : " + linkedList.removeFirst());
//        System.out.println("linkedList.remove(4) : " + linkedList.remove(4));
//        System.out.println("linkedList.remove(2) : " + linkedList.remove(2));
//
//        linkedList.display();
    }
}
