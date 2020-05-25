package moduletwo8.hardcore.model;

public enum VMClass {

    REGULAR("//md-option[@id='select_option_72']/div");

    private String elementXpath;

    VMClass(String elementXpath) {
        this.elementXpath = elementXpath;
    }

    public String getElementXpath() {
        return elementXpath;
    }
}
