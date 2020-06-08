package lesson2;

import java.util.Arrays;
import java.util.Random;

public class Test {

    private static final int ARRAY_SIZE = 10_000;
    private static final int MAX_VALUE = 10_000;

    public static void main(String[] args) {

        Integer[] initials = new Integer[ARRAY_SIZE];
        for (int i = 0; i < initials.length; i++) {
            initials[i] = new Random().nextInt(MAX_VALUE);
        }

        Array<Integer> arr1 = new ArrayImpl(Arrays.copyOf(initials, initials.length));
        Array<Integer> arr2 = new ArrayImpl(Arrays.copyOf(initials, initials.length));
        Array<Integer> arr3 = new ArrayImpl(Arrays.copyOf(initials, initials.length));

        measure(arr1::sortBubble, "sortBubble");
        measure(arr2::sortSelect, "sortSelect");
        measure(arr3::sortInsert, "sortInsert");
    }

    private static void measure(Runnable action, String actionName) {
        long start = System.currentTimeMillis();
        action.run();
        System.out.printf("Sorting time for method '%s' - %d\n", actionName, System.currentTimeMillis() - start);
    }

}
        //заглушка к уроку 6