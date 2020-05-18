package lesson2;

import java.util.Arrays;
import java.util.Random;

public class Test {

    private static final int ARRAY_SIZE = 10_000;

    public static void main(String[] args) {

        Integer[] initials = new Integer[ARRAY_SIZE];
        for (int i = 0; i < initials.length; i++) {
            initials[i] = new Random().nextInt();
        }

        Array<Integer> arr1 = new ArrayImpl(Arrays.copyOf(initials, initials.length));
        Array<Integer> arr2 = new ArrayImpl(Arrays.copyOf(initials, initials.length));
        Array<Integer> arr3 = new ArrayImpl(Arrays.copyOf(initials, initials.length));

        long start;
        start = System.currentTimeMillis();
        arr1.sortBubble();
        System.out.println((System.currentTimeMillis() - start) + " - sortBubble");

        start = System.currentTimeMillis();
        arr2.sortSelect();
        System.out.println((System.currentTimeMillis() - start) + " - sortSelection");

        start = System.currentTimeMillis();
        arr3.sortInsert();
        System.out.println((System.currentTimeMillis() - start) + " - sortInsert");

    }

}
