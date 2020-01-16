package moduletwo1.models.enums;

public enum FacultiesNames {

    HISTORY("Faculty of history"),
    MATHEMATICS("Faculty of mathematics"),
    LAW("Faculty of law");

    private String title;

    FacultiesNames(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}