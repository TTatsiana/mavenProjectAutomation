package module_two_1;

import module_two_1.exceptions.DataEntryExceptions;
import module_two_1.models.Faculty;
import module_two_1.models.Group;
import module_two_1.models.Student;
import module_two_1.models.University;
import module_two_1.models.enums.DisciplineNames;
import module_two_1.models.enums.FacultiesNames;
import module_two_1.models.enums.GroupNames;
import module_two_1.models.enums.Messenger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Runner {
    private static final Logger LOGGER = Logger.getLogger(Runner.class.getName());
    private static final String INPUT_FILE = "src/in.txt";
    private static final int SKIP5 = 5;
    private static final int SKIP10 = 10;
    private static final int SKIP15 = 15;
    private static final int SKIP20 = 20;
    private static final int LIMIT5 = 5;
    private static final int INDEX_0 = 0;
    private static final int INDEX_3 = 3;

    public static void main(String[] args) {
        List<Faculty> facultyList;
        Student someStudent;
        try {
            facultyList = getPreconditions();
            University university = new University("GSU", facultyList);
            System.out.println(university);
            someStudent = facultyList.get(INDEX_0).getGroups().get(INDEX_0).getStudents().get(INDEX_3);
            System.out.println(String.format("%s %s = %.2f", Messenger.COUNT_BY_STUDENT, someStudent.getName(),
                    university.calculateAverageMarkForAllStudentSubjects(someStudent)));
            System.out.println(String.format("%s %s = %.2f", Messenger.COUNT_BY_ALL_UNIVERSITY,
                    DisciplineNames.ENGLISH.getTitle(),
                    university.calculateAverageMarkForUniversityInSubject(DisciplineNames.ENGLISH)));
            System.out.println(String.format("%s %s,%s,%s) = %.2f", Messenger.COUNT_BY_CONDITION,
                    DisciplineNames.RUSSIAN_LANGUAGE.getTitle(), GroupNames.HIS_2, FacultiesNames.HISTORY.getTitle(),
                    university.calculateAverageMarkByCondition(DisciplineNames.RUSSIAN_LANGUAGE,
                            GroupNames.HIS_2, FacultiesNames.HISTORY)));
        } catch (DataEntryExceptions e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }

    private static List<Faculty> getPreconditions() throws DataEntryExceptions {
        List<Student> studentList;
        List<Group> groupListHIS = new ArrayList<>();
        List<Group> groupListMATH = new ArrayList<>();
        List<Group> groupListL = new ArrayList<>();
        List<Faculty> facultyList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileReader(INPUT_FILE))) {
            studentList = new Helper().getListStudents(scanner);
            groupListHIS.add(new Group(GroupNames.HIS_1, studentList.stream().limit(LIMIT5).collect(Collectors.toList())));
            groupListHIS.add(new Group(GroupNames.HIS_2, studentList.stream().skip(SKIP5).limit(LIMIT5).collect(Collectors.toList())));
            facultyList.add(new Faculty(FacultiesNames.HISTORY, groupListHIS));
            groupListMATH.add(new Group(GroupNames.MATH_1, studentList.stream().skip(SKIP10).limit(LIMIT5).collect(Collectors.toList())));
            groupListMATH.add(new Group(GroupNames.MATH_2, studentList.stream().skip(SKIP15).limit(LIMIT5).collect(Collectors.toList())));
            facultyList.add(new Faculty(FacultiesNames.MATHEMATICS, groupListMATH));
            groupListL.add(new Group(GroupNames.L, studentList.stream().skip(SKIP20).limit(LIMIT5).collect(Collectors.toList())));
            facultyList.add(new Faculty(FacultiesNames.LAW, groupListL));
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return facultyList;
    }
}