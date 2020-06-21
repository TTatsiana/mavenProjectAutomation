package moduletwo10.framework.bo.factory;

import moduletwo10.framework.bo.model.Folder;
import moduletwo10.framework.constants.StringСonstants;

public class FolderFactory {

    public static Folder getFolder() {
        return new Folder(StringСonstants.FOLDER_NAME);
    }
}
