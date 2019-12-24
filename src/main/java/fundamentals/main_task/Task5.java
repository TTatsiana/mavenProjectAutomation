package fundamentals.main_task;

import java.util.Scanner;

public class Task5 {
    public static String findMonth() {
        System.out.println("Enter a number from 1 to 12:");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        String month;
        if ((number < 1) || (number > 12)) {
            month = "No month!";
        } else {
            switch (number) {
                case 1:
                    month = "January";
                    break;
                case 2:
                    month = "February";
                    break;
                case 3:
                    month = "March";
                    break;
                case 4:
                    month = "April";
                    break;
                case 5:
                    month = "May";
                    break;
                case 6:
                    month = "June";
                    break;
                case 7:
                    month = "July";
                    break;
                case 8:
                    month = "August";
                    break;
                case 9:
                    month = "September";
                    break;
                case 10:
                    month = "October";
                    break;
                case 11:
                    month = "November";
                    break;
                default:
                    month = "December";
                    break;
            }
        }
        scanner.close();
        return month;
    }
}
