package moduletwo8.hardcore.model;

public enum Location {

    FRANKFURT("//md-option[@id='select_option_181']/div");

    private String elementXpath;

    Location(String elementXpath) {
        this.elementXpath = elementXpath;
    }

    public String getElementXpath() {
        return elementXpath;
    }
}
