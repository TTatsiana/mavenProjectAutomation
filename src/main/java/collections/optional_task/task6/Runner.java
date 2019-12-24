package collections.optional_task.task6;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Runner {
    private static final String INPUT_FILE = "src/main/java/collections/optional_task/task6/in.txt";
    private static final String FILE_NOT_FOUND = "Input file is not found";


    public static void main(String[] args) {
        Scanner scanner = null;
        List<String> listOfLines;
        try {
            scanner = new Scanner(new FileReader(INPUT_FILE));
            listOfLines = new ArrayList<String>();
            while (scanner.hasNext()) {
                listOfLines.add(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
            throw new RuntimeException();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        Collections.sort(listOfLines, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        listOfLines.forEach(System.out::println);
    }
}