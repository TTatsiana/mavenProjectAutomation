package module_two_1;

import module_two_1.exceptions.IllegalMarksException;
import module_two_1.exceptions.NullDisciplineException;
import module_two_1.models.Student;
import module_two_1.models.enums.DisciplineNames;

import java.util.*;

public class Helper {
    private static final String REGEX = ";";
    private static final String REGEX_COMMA = ",";
    private static final int INDEX_0 = 0;

    public List<Student> getListStudents(Scanner scanner) throws NullDisciplineException, IllegalMarksException {
        int increaseIndex=1;
        List<Student> studentList = new ArrayList<>();
        while (scanner.hasNext()) {
            String csvLine = scanner.nextLine();
            csvLine = csvLine.replace(" ", "");
            String[] objectFields = csvLine.split(REGEX);
            String name = objectFields[INDEX_0];
            Map<DisciplineNames, List<Integer>> mapMarks = new HashMap<>();
            if (objectFields.length > 1) {
                for (int i = 1; i < objectFields.length; i = i + 2) {
                    DisciplineNames disciplineNames = DisciplineNames.valueOf(objectFields[i]);
                    List<Integer> listMarks = new ArrayList<>();
                    String[] marks = objectFields[i + increaseIndex].split(REGEX_COMMA);
                    for (int j = 0; j < marks.length; j++) {
                        listMarks.add(Integer.parseInt(marks[j]));
                    }
                    mapMarks.put(disciplineNames, listMarks);
                }
            }
            studentList.add(new Student(name, mapMarks));
        }
        return studentList;
    }
}