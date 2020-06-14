package moduletwo10.framework.bo;

import moduletwo10.yandex.page.constants.StringСonstants;

public class DocumentFactory {

    public static Document getDocument() {
        return new Document(StringСonstants.DOCUMENT_NAME, StringСonstants.DOCUMENT_TEXT);
    }
}
