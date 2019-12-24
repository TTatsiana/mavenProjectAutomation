package fundamentals.main_task;

public class Runner {
    public static void main(String[] args) {
        System.out.println("Task 1");
        Task1.sayHello();
        System.out.println("Task 2");
        Task2.printArgs(args);
        System.out.println("\nTask 3");
        Task3.printSomeRandom();
        System.out.println("\nTask 4");
        System.out.println("multiply=" + Task4.multiply(args));
        System.out.println("sum=" + Task4.sum(args));
        System.out.println("Task 5");
        System.out.println(Task5.findMonth());
    }
}