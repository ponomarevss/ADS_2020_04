package lesson3.queue;

public class QueueTest {
    public static void main(String[] args) {
//        Queue<Integer> queue = new PriorityQueue(5);
        Queue<Integer> queue = new QueueImpl(5);
        queue.insert(2);
        queue.insert(4);
        queue.insert(1);
        queue.insert(5);
        queue.insert(3);

        while (!queue.isEmpty()) System.out.println(queue.remove());
    }
}