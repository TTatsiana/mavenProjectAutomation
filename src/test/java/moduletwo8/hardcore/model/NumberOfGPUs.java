package moduletwo8.hardcore.model;

public enum NumberOfGPUs {

    ONE("//md-option[@id='select_option_339']/div");

    private String elementXpath;

    public String getElementXpath() {
        return elementXpath;
    }

    NumberOfGPUs(String elementXpath) {
        this.elementXpath = elementXpath;
    }
}
