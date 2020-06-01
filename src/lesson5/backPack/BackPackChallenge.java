package lesson5.backPack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static lesson5.Constants.*;

public class BackPackChallenge {

    public static void main(String[] args) {


        ArrayList<Package> packages = getRandomSet();
        showSet("Packages", packages);

        int backpackCapacity = new Backpack(packages).getCapacity();
        showParameter("Backpack capacity", backpackCapacity);

        int maxValue = 0;
        ArrayList<Package> bestSet = new ArrayList<>();

        List<Integer[]> indexOptions = new NumAnagram(packages.size()).getIndOptions();
        for (Integer[] indexOption : indexOptions) {
            int sumWeight = 0;
            int sumValue = 0;
            ArrayList<Package> currentSet = new ArrayList<>();
            for (Integer index : indexOption) {
                Package pack = packages.get(index);
                if (sumWeight + pack.getWeight() <= backpackCapacity) {
                    currentSet.add(pack);
                    sumWeight += pack.getWeight();
                    sumValue += pack.getPrice();
                } else {
                    if (sumValue > maxValue) {
                        bestSet = currentSet;
                        maxValue = sumValue;
                    }
                    break;
                }
            }
        }
        showSet("Best set", bestSet);
        showParameter("Max value", maxValue);
    }

    private static void showParameter(String s, int p) {
        System.out.println(s + ": " + p + "\n---------------------");
    }

    private static void showSet(String s, ArrayList<Package> p) {
        System.out.print(s + ":\t");
        for (int i = 0; i < p.size(); i++) {
            System.out.print(p.get(i).getWeight() + " : " + p.get(i).getPrice() + "\t|\t");
        }
        System.out.println("\n---------------------");
    }

    private static ArrayList<Package> getRandomSet() {
        ArrayList<Package> packages = new ArrayList<>(SET_SIZE);
        for (int i = 0; i < SET_SIZE; i++) {
            packages.add(getRandomPack());
        }
        return packages;
    }

    private static Package getRandomPack() {
        return new Package(new Random().nextInt(MAX_WEIGHT), new Random().nextInt(MAX_PRICE));
    }
}
