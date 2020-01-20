package moduletwo2.main;

import java.io.File;

public class Runner {

    private static final int INDEX_0 = 0;

    public static void main(String[] args) {
        File file = new File(args[INDEX_0]);
        if (file.isDirectory()) {
            FileManager.writeFolderStructureToFile(args[INDEX_0]);
        } else {
            FileManager.displayFileData(file);
        }
    }
}