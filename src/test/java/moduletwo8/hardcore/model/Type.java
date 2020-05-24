package moduletwo8.hardcore.model;

public enum Type {

    STANDART8("//md-option[@id='select_option_212']/div");

    private String elementXpath;

    Type(String elementXpath) {
        this.elementXpath = elementXpath;
    }

    public String getElementXpath() {
        return elementXpath;
    }
}
