package moduletwo2.optional.task3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Runner {

    private static final Logger LOGGER = Logger.getLogger(Runner.class.getName());
    private static final String FILE = "fileForTask2.txt";
    private static final String FILE_RES = "fileForTask3.txt";
    private static final String DIR = "d:\\";

    public static void main(String[] args) {
        File file = new File(DIR + File.separator + FILE);
        File fileRes = new File(DIR + File.separator + FILE_RES);
        List<StringBuffer> listFromFile = readListFromFile(file);
        try {
            file.createNewFile();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        writeListToFile(listFromFile, fileRes);
    }

    private static void writeListToFile(List<StringBuffer> list, File file) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            list.stream().map(StringBuffer::reverse).forEach(writer::println);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }

    private static List<StringBuffer> readListFromFile(File file) {
        List<StringBuffer> list = new ArrayList<>();
        String readLine;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((readLine = reader.readLine()) != null) {
                list.add(new StringBuffer(readLine));
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return list;
    }
}
