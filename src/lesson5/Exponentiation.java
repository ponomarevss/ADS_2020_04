package lesson5;

public class Exponentiation {


    public static void main(String[] args) {

        for (float i = (float) 0.5; i <= 5; i++) {
            for (int j = -3; j <= 5; j++) {
                System.out.print(exp(i, j) + "\t\t\t");
            }
            System.out.println();
        }
    }

    private static float exp(float base, int exp) {
        if (base == 0) return 0;
        int expAbs = Math.abs(exp);
        return exp >= 0 ? expAbs(base, expAbs) : 1 / expAbs(base, expAbs);
    }

    private static float expAbs(float base, int expAbs) {
        if (expAbs == 0) return 1;
        return base * expAbs(base, expAbs - 1);
    }
}
