package moduletwo1;

import moduletwo1.exceptions.IllegalMarksException;
import moduletwo1.exceptions.NullDisciplineException;
import moduletwo1.models.Student;
import moduletwo1.models.enums.DisciplineNames;

import java.util.*;

public class Helper {

    private static final String REGEX = ";";
    private static final String REGEX_COMMA = ",";
    private static final int INDEX_0 = 0;
    private static final int INCREASE_BY_2 = 2;
    private static final int MINIMUM_LENGTH = 2;

    public List<Student> getListStudents(Scanner scanner) throws NullDisciplineException, IllegalMarksException {
        int increaseIndex = 1;
        List<Student> studentList = new ArrayList<>();
        while (scanner.hasNext()) {
            String csvLine = scanner.nextLine();
            csvLine = csvLine.replace(" ", "");
            String[] objectFields = csvLine.split(REGEX);
            String name = objectFields[INDEX_0];
            Map<DisciplineNames, List<Integer>> mapMarks = new HashMap<>();
            if (objectFields.length >= MINIMUM_LENGTH) {
                for (int i = 1; i < objectFields.length; i = i + INCREASE_BY_2) {
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