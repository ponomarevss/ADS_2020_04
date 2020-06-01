package lesson5.backPack;

import java.util.ArrayList;
import java.util.Random;

import static lesson5.Constants.MAX_WEIGHT;
import static lesson5.Constants.SET_SIZE;

public class Backpack {


    private int capacity;

    public Backpack(ArrayList<Package> packages) {
        while (capacity < minCapacity(packages) || capacity > maxCapacity(packages)) {
            capacity = new Random().nextInt(MAX_WEIGHT * SET_SIZE);
        }
    }

    public int getCapacity() {
        return capacity;
    }

//    private static int setBackpackCapacity(ArrayList<Package> packages) {
//        int capacity = 0;
//        while (capacity < minCapacity(packages) || capacity > maxCapacity(packages)) {
//            capacity = new Random().nextInt(MAX_WEIGHT * SET_SIZE);
//        }
//        return capacity;
//    }

    private int maxCapacity(ArrayList<Package> packages) {
        int sumWeight = 0;
        for (Package pack : packages) {
            sumWeight += pack.getWeight();
        }
        return sumWeight;
    }

    private int minCapacity(ArrayList<Package> packages) {
        int maxWeight = 0;
        for (int i = 0; i < packages.size(); i++) {
            if (packages.get(i).getWeight() > maxWeight)
                maxWeight = packages.get(i).getWeight();
        }
        return maxWeight;
    }

}
