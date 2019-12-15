package fundamentals.optionalTask;

public class Service {
    public static int getLongestNumberToArray(int[] array) {
        int longestNumberToArray = array[0];
        int length = getLengthNumber(longestNumberToArray);
        for (int i = 1; i < array.length; i++) {
            if (getLengthNumber(array[i]) > length) {
                length = getLengthNumber(array[i]);
                longestNumberToArray = array[i];
            }
        }
        return longestNumberToArray;
    }

    public static int getShortestNumberToArray(int[] array) {
        int shortestNumberToArray = array[0];
        int length = getLengthNumber(shortestNumberToArray);
        for (int i = 1; i < array.length; i++) {
            if (getLengthNumber(array[i]) < length) {
                length = getLengthNumber(array[i]);
                shortestNumberToArray = array[i];
            }
        }
        return shortestNumberToArray;
    }

    public static int getLengthNumber(int number) {
        int length = 0;
        while (number != 0) {
            int remainderOfDivision = number % 10;
            number = (number - remainderOfDivision) / 10;
            length++;
        }
        return length;
    }

    public static int[] sortArray(int[] array) {
        for (int j = 0; j < array.length; j++) {
            for (int i = 0; i < array.length - 1; i++) {
                if (getLengthNumber(array[i]) > getLengthNumber(array[i + 1])) {
                    int box = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = box;
                }
            }
        }
        return array;
    }

    public static int getAverageLength(int[] array) {
        int numberOfDigitsInArray = 0;
        for (int i = 0; i < array.length; i++) {
            numberOfDigitsInArray += getLengthNumber(array[i]);
        }
        return numberOfDigitsInArray / array.length;
    }
}
