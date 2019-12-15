package fundamentals.mainTask;

public class Task2 {
    public static void printArgs(String[] args) {
        System.out.println("Command line arguments in reverse:");
        int kol = args.length;
        for (int i = kol - 1; i >= 0; i--) {
            System.out.print(args[i] + " ");
        }
    }
}