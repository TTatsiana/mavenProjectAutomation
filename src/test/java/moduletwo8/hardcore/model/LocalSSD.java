package moduletwo8.hardcore.model;

public enum LocalSSD {
    SSD2("//md-option[@id='select_option_233']/div");
    private String elementXpath;

    LocalSSD(String elementXpath) {
        this.elementXpath = elementXpath;
    }

    public String getElementXpath() {
        return elementXpath;
    }
}
