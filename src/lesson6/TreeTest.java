package lesson6;

import java.util.*;

import static lesson6.Constants.*;

public class TreeTest {


    public static void main(String[] args) {
        List<IntTree> trees = new ArrayList<>(TREES);
        int numberOfBalanced = 0;

        //создаем дерево, проверяем на сбалансированость, добавляем в набор
        for (int i = 0; i < TREES; i++) {
            IntTreeImpl intTree = createTree();
            if (intTree.isBalanced()) numberOfBalanced++;
            trees.add(createTree());
        }

        float percentageOfBalanced = numberOfBalanced / (float) TREES * 100;

        System.out.println("Number of trees: " + trees.size());
        trees.get(0).display();
        System.out.println("Tree is balanced: " + trees.get(0).isBalanced());
        System.out.println("Percentage of balanced trees: " + percentageOfBalanced);
    }

    private static IntTreeImpl createTree() {
        IntTreeImpl intTree = new IntTreeImpl();

        Set<Integer> values = new LinkedHashSet<>();
        int limit = (int) Math.pow(2, MAX_LEVEL) - 1;
        while (values.size() < limit) {
            values.add(new Random().nextInt(MAX_VALUE) - MAX_VALUE / 2);
        }

        for (Integer value : values) {
            intTree.add(value);
        }

        return intTree;
    }
}
