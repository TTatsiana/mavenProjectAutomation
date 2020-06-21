package moduletwo10.framework.bo.factory;

import moduletwo10.framework.bo.model.Document;
import moduletwo10.framework.constants.StringСonstants;

public class DocumentFactory {

    public static Document getDocument() {
        return new Document(StringСonstants.DOCUMENT_NAME, StringСonstants.DOCUMENT_TEXT);
    }
}
