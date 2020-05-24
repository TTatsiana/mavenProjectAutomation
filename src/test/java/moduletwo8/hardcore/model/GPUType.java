package moduletwo8.hardcore.model;

public enum GPUType {

    TESLAK80("//md-option[@id='select_option_346']/div");

    GPUType(String elementXpath) {
        this.elementXpath = elementXpath;
    }

    private String elementXpath;

    public String getElementXpath() {
        return elementXpath;
    }
}
