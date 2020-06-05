package lesson5;

public class Factorial {

    public static void main(String[] args) {
        System.out.println(factorial(1));

    }

    public static int factorial(int n) {
        if (n <= 1) return n;

        return n * factorial(--n);
    }

}
