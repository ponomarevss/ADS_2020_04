package lesson6;

public class Test {


    public static void main(String[] args) {
        IntTree tree = new IntTreeImpl();
        tree.add(60);
        tree.add(25);
        tree.add(66);
        tree.add(15);
        tree.add(5);
        tree.add(20);
        tree.add(45);
        tree.add(30);
        tree.add(55);
        tree.add(32);

        tree.traverse(IntTree.TraverseMode.POST_ORDER);
        tree.display();

        System.out.println("Contains 30: " + tree.contains(30));
        System.out.println("Contains 100: " + tree.contains(100));

        tree.remove(25);
        tree.display();
    }
}
