package Seminar_3;

import java.util.Random;

public class hw_3{
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        int length = 9;
        Random random = new Random();
        for (int i = 0; i < length; i++){
            list.add(random.nextInt(10));
        }
        list.print();
        list.revert();
        list.print();
    }
}
 