package collections.optionalTask.task5;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    private static List<Integer> listOfNumbers = new ArrayList<>();

    public static void main(String[] args) {
        listOfNumbers.add(-6);
        listOfNumbers.add(7);
        listOfNumbers.add(-3);
        listOfNumbers.add(-4);
        listOfNumbers.add(-8);
        listOfNumbers.add(1);
        listOfNumbers.add(-9);
        listOfNumbers.add(40);
        int sizeList = listOfNumbers.size();
        for (int i = 0; i < sizeList; i++) {
            int number = listOfNumbers.get(i);
            if (number < 0) {
                listOfNumbers.add(number);
                listOfNumbers.set(i, null);
            }
        }
        listOfNumbers.stream().filter((s) -> s != null).forEach(System.out::println);
    }
}