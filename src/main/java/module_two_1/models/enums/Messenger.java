package module_two_1.models.enums;

public enum Messenger {
    NULL_FACULTY("Отсутствие факультетов в университете"), NULL_GROUP("Отсутствие групп на факультете"),
    NULL_STUDENT("Отсутствие студентов в группе"), NULL_DISCIPLINE("Отсутсвие предметов у студента"),
    ILLEGAL_MARKS("Оценка ниже 0 или выше 10"),
    COUNT_BY_STUDENT("Cредний балл по всем предметам студента"),
    COUNT_BY_ALL_UNIVERSITY("Средний балл для всего университета по предмету"),
    COUNT_BY_CONDITION("Cредний балл по (предмету,группа,факультет :");

    private String mess;

    private Messenger(String mess) {
        this.mess = mess;
    }

    @Override
    public String toString() {
        return mess;
    }
}