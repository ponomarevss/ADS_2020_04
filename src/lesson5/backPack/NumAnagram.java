package lesson5.backPack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumAnagram {

    private final Integer[] indexes;
    private final List<Integer[]> indOptions;

    public NumAnagram(int number) {
        indexes = new Integer[number];
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = i;
        }
        indOptions = new ArrayList<>();
        getNumAnagram(number);
    }

    public void getNumAnagram(int length) {
        if (length == 1) return;

        for (int i = 0; i < length; i++) {
            getNumAnagram(length - 1);
            if (length == 2) indOptions.add(Arrays.copyOf(indexes, indexes.length));
            rotate(length);
        }
    }

    private void rotate(int length) {
        int ind = indexes.length - length;
        int temp = indexes[ind];
        for (int i = ind + 1; i < indexes.length; i++) {
            indexes[i - 1] = indexes[i];
        }
        indexes[indexes.length - 1] = temp;
    }


    public List<Integer[]> getIndOptions() {
        return indOptions;
    }
}
