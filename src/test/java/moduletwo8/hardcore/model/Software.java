package moduletwo8.hardcore.model;

public enum Software {
    FREE("//md-option[@id='select_option_60']/div");

    private String elementXpath;

    Software(String elementXpath) {
        this.elementXpath = elementXpath;
    }

    public String getElementXpath() {
        return elementXpath;
    }
}
