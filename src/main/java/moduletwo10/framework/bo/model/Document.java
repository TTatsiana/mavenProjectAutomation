package moduletwo10.framework.bo.model;

public class Document {

    private String name;
    private String text;

    public Document(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }
}
