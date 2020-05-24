package moduletwo8.hardcore.model;

public enum Usage {

    YEAR1("//md-option[@id='select_option_90']/div");

    private String elementXpath;

    Usage(String elementXpath) {
        this.elementXpath = elementXpath;
    }

    public String getElementXpath() {
        return elementXpath;
    }
}
