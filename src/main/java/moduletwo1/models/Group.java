package moduletwo1.models;

import moduletwo1.exceptions.NullStudentException;
import moduletwo1.models.enums.GroupNames;
import moduletwo1.models.enums.Messenger;

import java.util.List;

public class Group {

    private GroupNames name;
    private List<Student> students;

    public Group(GroupNames name, List<Student> students) throws NullStudentException {
        if (students.isEmpty()) {
            throw new NullStudentException(Messenger.NULL_STUDENT.getTitle());
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