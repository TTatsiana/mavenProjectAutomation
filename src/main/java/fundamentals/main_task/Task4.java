package fundamentals.main_task;

public class Task4 {
    public static int sum(String[] args) {
        int resultSum = 0;
        int[] array = convert(args);
        for (int arrayElement : array) {
            resultSum += arrayElement;
        }
        return resultSum;
    }

    public static int multiply(String[] args) {
        int[] array = convert(args);
        int resultMultiply = array[0];
        for (int i = 1; i < array.length; i++) {
            resultMultiply *= array[i];
        }
        return resultMultiply;
    }

    private static int[] convert(String[] args) {
        int[] resultConvert = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            resultConvert[i] = Integer.parseInt(args[i]);
        }
        return resultConvert;
    }
}