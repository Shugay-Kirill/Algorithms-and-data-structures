package Seminar_4;

import java.util.Random;
import java.util.Scanner;

public class start {
    public static void main(String[] args) {
        RBTree<Integer> tree  = new RBTree<>();

        
        Random random = new Random();
        int length = 10;
        for (int i = 0; i < length; i++) {
            int value = random.nextInt(100);
            System.out.print(value + ", ");
            tree.add(value);
        }
        System.out.println();
        tree.print();
        System.out.println("debug");

    }
}
