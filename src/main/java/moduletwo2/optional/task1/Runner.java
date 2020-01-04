package moduletwo2.optional.task1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Runner {

    private static final Logger LOGGER = Logger.getLogger(Runner.class.getName());
    private static final String FILE = "resultTask1.txt";
    private static final String DIR = "d:\\";
    private static final int SOME_LIMIT = 10;
    private static final int SOME_FACTOR = 100;

    public static void main(String[] args) {
        File file = new File(DIR + File.separator + FILE);
        List<Integer> listRandom = new ArrayList<>();
        List<Integer> list;
        try {
            file.createNewFile();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        for (int i = 0; i < SOME_LIMIT; i++) {
            listRandom.add((int) (Math.random() * SOME_FACTOR));
        }
        writeListToFile(listRandom, file);
        list = readListFromFile(file);
        Collections.sort(list);
        writeListToFile(list, file);
    }

    private static void writeListToFile(List<Integer> list, File file) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            list.forEach(writer::println);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }

    private static List<Integer> readListFromFile(File file) {
        List<Integer> list = new ArrayList<>();
        String readLine;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((readLine = reader.readLine()) != null) {
                list.add(Integer.parseInt(readLine));
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return list;
    }
}