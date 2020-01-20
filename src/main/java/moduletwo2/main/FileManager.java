package moduletwo2.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManager {

    private static final Logger LOGGER = Logger.getLogger(FileManager.class.getName());
    private static final String RESULT_FILE = "resMainTask.txt";
    private static final String DIR = "data\\";
    private static final String REGEX_SLASH = "\\\\";
    private static final int DECREASE1 = 1;
    private static final String PREFIX = "|   ";
    private static final String PREFIX_FOR_FOLDERS = "+";
    private static final String REGEX = "\\..+";
    private static final char DELIMITER = '|';
    private static final int INCREASE1 = 1;


    public static void writeFolderStructureToFile(String directory) {
        File fileRes = new File(DIR + File.separator + RESULT_FILE);
        try (Stream<Path> pathStream = Files.walk(Paths.get(directory), FileVisitOption.FOLLOW_LINKS)) {
            fileRes.createNewFile();
            List<File> fileList = pathStream.map(Path::toFile).collect(Collectors.toList());
            writeListToFile(fileList, fileRes);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }

    public static void displayFileData(File file) {
        String messCountFolders = "Number of folders =";
        String messCountFiles = " Number of files =";
        String messAverageLength = "Average length of file name =";
        String messAverageCountFolders = "Average number of files in a folder =";
        List<String> listFromFile = readListFromFile(file);
        int countFolders = (int) listFromFile.stream().map(s -> s.replace(PREFIX, ""))
                .filter(s -> s.contains(PREFIX_FOR_FOLDERS)).count();
        int countFiles = listFromFile.size() - countFolders;
        System.out.println(messCountFolders + countFolders + messCountFiles + countFiles);
        long averageLength = Math.round(listFromFile.stream().map(s -> s.replace(PREFIX, ""))
                .filter(s -> !s.contains(PREFIX_FOR_FOLDERS))
                .mapToInt(s -> s.replaceFirst(REGEX, "").length()).average().orElse(0));
        System.out.println(messAverageLength + averageLength);
        long averageCountFolders = Math.round(getNumberOfFoldersInDirectories(listFromFile)
                .entrySet().stream().mapToInt(Map.Entry::getValue).average().orElse(0));
        System.out.println(messAverageCountFolders + averageCountFolders);
    }

    private static void writeListToFile(List<File> fileList, File file) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            for (File fileFromList : fileList) {
                String[] str = fileFromList.getAbsolutePath().split(REGEX_SLASH);
                for (int i = 0; i < str.length - DECREASE1; i++) {
                    if (i > 0) {
                        writer.print(PREFIX);
                    }
                }
                if (fileFromList.isDirectory()) {
                    writer.print(PREFIX_FOR_FOLDERS);
                }
                writer.println(str[str.length - DECREASE1]);
            }
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

    private static Map<String, Integer> getNumberOfFoldersInDirectories(List<String> listFromFile) {
        Map<String, Integer> folderMap = new HashMap<>();
        for (int i = 0; i < listFromFile.size(); i++) {
            String folder = listFromFile.get(i);
            if (folder.contains(PREFIX_FOR_FOLDERS)) {
                int count = 0;
                boolean inTheDirectory = true;
                long folderDepth = folder.chars().filter(ch -> ch == DELIMITER).count();
                for (int j = i + INCREASE1; j < listFromFile.size(); j++) {
                    long directoryDepth = listFromFile.get(j).chars().filter(ch -> ch == DELIMITER).count();
                    if (directoryDepth == folderDepth) {
                        inTheDirectory = false;
                    }
                    if ((directoryDepth == folderDepth + INCREASE1) && (inTheDirectory)) {
                        count++;
                    }
                }
                folderMap.put(folder, count);
            }
        }
        return folderMap;
    }
}
