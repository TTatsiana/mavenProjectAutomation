package module_two_1.models;

import module_two_1.exceptions.NullStudentException;
import module_two_1.models.enums.GroupNames;
import module_two_1.models.enums.Messenger;

import java.util.List;

public class Group {
    private GroupNames name;
    private List<Student> students;

    public Group(GroupNames name, List<Student> students) throws NullStudentException {
        if (students.size() == 0) {
            throw new NullStudentException(Messenger.NULL_STUDENT.toString());
        }
        this.name = name;
        this.students = students;
    }

    public GroupNames getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return String.format("\n%s, %s", name, students);
    }
}