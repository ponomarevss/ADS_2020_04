package lesson2;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;

public class Test {

    public static void main(String[] args) {

        int size = 10;
        Array<Integer> initialArray = new ArrayImpl(size);
        for (int i = 0; i < size; i++) {
            initialArray.add(new Random().nextInt(20));
        }
        initialArray.display();
        //TODO: допилить метод создания копии массива, выполнить основное задание

    }
}
