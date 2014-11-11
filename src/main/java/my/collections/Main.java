package my.collections;

import java.util.Collection;
public class Main {

    public static void main(String[] args) {
        Collection<Integer> bag = new Bag<>();
        bag.add(1);
        bag.add(5);
        bag.add(7);
        bag.add(8);
        bag.add(1);
        bag.add(12);
        bag.add(1);
        for (Integer i : bag) {
            System.out.print(i + ",");
        }
        // output: 1,1,1,5,7,8,12,
        System.out.println();
        for (Integer i : bag) {
            System.out.print(i + ",");
        }
    }

}
