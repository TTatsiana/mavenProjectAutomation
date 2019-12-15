package classes.mainTask;

public enum ProductName {
    MILK("Milk"),
    BREAD("Bread"),
    BUTTER("Butter");
    private String title;

    ProductName(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}