package moduletwo2.optional.task2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Runner {

    private static final Logger LOGGER = Logger.getLogger(Runner.class.getName());
    private static final String FILE = "fileForTask2.txt";
    private static final String DIR = "d:\\";

    public static void main(String[] args) {
        File file = new File(DIR + File.separator + FILE);
        List<String> listFromFile = readListFromFile(file);
        writeListToFile(listFromFile, file);
    }

    private static void writeListToFile(List<String> list, File file) {
        String search = "public";
        String replace = "private";
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            list.stream().map(s -> s.replaceAll(search, replace)).forEach(writer::println);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }

    private static List<String> readListFromFile(File file) {
        List<String> list = new ArrayList<>();
        String readLine;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((readLine = reader.readLine()) != null) {
                list.add(readLine);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return list;
    }
}
