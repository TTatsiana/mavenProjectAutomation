package fundamentals.main_task;

import java.util.Scanner;

public class Task3 {
    public static void printSomeRandom() {
        System.out.println("Enter a number:");
        Scanner scanner = new Scanner(System.in);
        int amountOfNumbers = scanner.nextInt();
        int[] array = new int[amountOfNumbers];
        for (int i = 0; i < amountOfNumbers; i++) {
            array[i] = (int) (Math.random() * 10);
        }
        for (int arrayElement : array) {
            System.out.println(arrayElement);
        }
        for (int arrayElement : array) {
            System.out.print(arrayElement + " ");
        }
    }
}