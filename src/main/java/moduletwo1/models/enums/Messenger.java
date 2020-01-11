package moduletwo1.models.enums;

public enum Messenger {

    NULL_FACULTY("Lack of faculties at the university"),
    NULL_GROUP("The lack of groups at the faculty"),
    NULL_STUDENT("Lack of students in the group"),
    NULL_DISCIPLINE("Student has no items"),
    ILLEGAL_MARKS("Score below 0 or above 10"),
    AVERAGE_SCORE_STUDENT("Average mark in all subjects of the student"),
    AVERAGE_SCORE_FOR_THE_SUBJECT("Average mark for the whole university in the subject"),
    AVERAGE_SCORE_FOR_THE_SUBJECT_IN_THE_GROUP("Average mark in (subject, group, faculty:");

    private String title;

    Messenger(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}