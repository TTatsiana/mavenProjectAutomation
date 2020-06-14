package moduletwo10.framework.bo;

import moduletwo10.yandex.page.constants.StringСonstants;

public class FolderFactory {

    public static Folder getFolder() {
        return new Folder(StringСonstants.FOLDER_NAME);
    }
}
