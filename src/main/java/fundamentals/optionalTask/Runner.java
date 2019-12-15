package fundamentals.optionalTask;

import java.util.Scanner;

// Optional Task 1 (1-3)
public class Runner {
    public static void main(String[] args) {
        System.out.println("Enter a number:");
        Scanner scanner = new Scanner(System.in);
        int arraySize = scanner.nextInt();
        int[] array = new int[arraySize];
        System.out.println("Enter " + arraySize + " numbers:");
        for (int i = 0; i < arraySize; i++) {
            array[i] = scanner.nextInt();
        }
        System.out.println("Task 1");
        int longestNumberToArray = Service.getLongestNumberToArray(array);
        System.out.println("Larger number is " + longestNumberToArray + ". Length=" + Service.getLengthNumber(longestNumberToArray));
        int shortestNumberToArray = Service.getShortestNumberToArray(array);
        System.out.println("Shorter number is " + shortestNumberToArray + ". Length=" + Service.getLengthNumber(shortestNumberToArray));
        System.out.println("Task 2\nincrease");
        int[] sortedArray;
        sortedArray = Service.sortArray(array);
        for (int arrayElement : sortedArray) {
            System.out.print(arrayElement + " ");
        }
        System.out.println("\ndecrease");
        for (int i = sortedArray.length - 1; i >= 0; i--) {
            System.out.print(sortedArray[i] + " ");
        }
        System.out.println("\nTask 3");
        int averageLengthNumberToArray = Service.getAverageLength(array);
        System.out.println("Average length=" + averageLengthNumberToArray);
        System.out.print("Longer than average: ");
        for (int arrayElement : array) {
            if (Service.getLengthNumber(arrayElement) > averageLengthNumberToArray) {
                System.out.print(arrayElement + " ");
            }
        }
        System.out.print("\nShorter  than average: ");
        for (int arrayElement : array) {
            if (Service.getLengthNumber(arrayElement) < averageLengthNumberToArray) {
                System.out.print(arrayElement + " ");
            }
        }
        scanner.close();
    }
}
