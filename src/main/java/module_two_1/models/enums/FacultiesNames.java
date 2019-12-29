package module_two_1.models.enums;

public enum FacultiesNames {
    HISTORY("Исторический"), MATHEMATICS("Математический"), LAW("Юридический");
    private String title;

    FacultiesNames(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}