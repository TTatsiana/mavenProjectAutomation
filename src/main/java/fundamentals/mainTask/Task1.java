package fundamentals.mainTask;

import java.util.Scanner;

public class Task1 {
    public static void sayHello() {
        System.out.println("Enter your name:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Hello " + name);

    }
}