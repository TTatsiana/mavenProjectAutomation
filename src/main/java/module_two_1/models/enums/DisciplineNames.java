package module_two_1.models.enums;

public enum DisciplineNames {

    MATHS("математика"), HISTORY("история"), CULTURE("культура"),
    RUSSIAN_LANGUAGE("русский язык"), ENGLISH("английский язык"), BASES_OF_LAW("основы права");
    private String title;

    DisciplineNames(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}