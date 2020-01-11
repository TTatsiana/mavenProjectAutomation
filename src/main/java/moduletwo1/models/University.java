package moduletwo1.models;

import moduletwo1.exceptions.NullFacultyException;
import moduletwo1.models.enums.DisciplineNames;
import moduletwo1.models.enums.FacultiesNames;
import moduletwo1.models.enums.GroupNames;
import moduletwo1.models.enums.Messenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class University {

    private String name;
    private List<Faculty> facultyList;

    public University(String name, List<Faculty> facultyList) throws NullFacultyException {
        if (facultyList.isEmpty()) {
            throw new NullFacultyException(Messenger.NULL_FACULTY.getTitle());
        }
        this.name = name;
        this.facultyList = facultyList;
    }

    private List<Student> getStudentList() {
        List<Student> studentList = new ArrayList<>();
        for (Faculty faculty : facultyList) {
            for (Group group : faculty.getGroups()) {
                studentList.addAll(group.getStudents());
            }
        }
        return studentList;
    }

    public double calculateAverageMarkForAllStudentSubjects(Student student) {
        List<Student> studentListByCondition = new ArrayList<>();
        studentListByCondition.add(student);
        return calculateAverageMark(studentListByCondition, null);
    }

    public double calculateAverageMarkByCondition(DisciplineNames disciplineName, GroupNames groupNames,
                                                  FacultiesNames facultiesNames) {
        List<Group> groupListByCondition = new ArrayList<>();
        List<Student> studentList = new ArrayList<>();

        for (Faculty faculty : facultyList) {
            if (faculty.getName().equals(facultiesNames)) {
                groupListByCondition = faculty.getGroups();
            }
        }
        for (Group group : groupListByCondition) {
            if (group.getName().equals(groupNames)) {
                studentList = group.getStudents();
            }
        }
        return calculateAverageMark(studentList, disciplineName);
    }

    public double calculateAverageMarkForUniversityInSubject(DisciplineNames disciplineName) {
        return calculateAverageMark(getStudentList(), disciplineName);
    }

    private static double calculateAverageMark(List<Student> studentList, DisciplineNames disciplineName) {
        List<Integer> listMarks = new ArrayList<>();
        for (Student student : studentList) {
            Map<DisciplineNames, List<Integer>> marksOfDiscipline = student.getMarksOfDiscipline();
            for (Map.Entry<DisciplineNames, List<Integer>> entry : marksOfDiscipline.entrySet()) {
                if (entry.getKey().equals(disciplineName) || disciplineName == null) {
                    listMarks.addAll(entry.getValue());
                }
            }
        }
        return listMarks.stream().mapToInt(Integer::intValue).average().orElse(0);
    }

    @Override
    public String toString() {
        return String.format("%s, %s", name, facultyList);
    }
}