package lesson3.stack;

public class StackTest {
    public static void main(String[] args) {
        Stack<Integer> stack = new StackImpl(5);
        stack.push(2);
        stack.push(1);
        stack.push(3);
        stack.push(5);
        stack.push(4);
        while (!stack.isEmpty()) System.out.println(stack.pop());
    }
}
