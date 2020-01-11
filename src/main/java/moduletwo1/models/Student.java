package moduletwo1.models;

import moduletwo1.exceptions.IllegalMarksException;
import moduletwo1.exceptions.NullDisciplineException;
import moduletwo1.models.enums.DisciplineNames;
import moduletwo1.models.enums.Messenger;

import java.util.List;
import java.util.Map;

public class Student {

    private String name;
    private Map<DisciplineNames, List<Integer>> marksOfDiscipline;


    public Student(String name, Map<DisciplineNames, List<Integer>> marksOfDiscipline) throws NullDisciplineException, IllegalMarksException {
        if (marksOfDiscipline.isEmpty()) {
            throw new NullDisciplineException(Messenger.NULL_DISCIPLINE.getTitle());
        }
        for (Map.Entry<DisciplineNames, List<Integer>> item : marksOfDiscipline.entrySet()) {
            checkListForValidity(item.getValue());
        }
        this.name = name;

        this.marksOfDiscipline = marksOfDiscipline;
    }

    public String getName() {
        return name;
    }


    public Map<DisciplineNames, List<Integer>> getMarksOfDiscipline() {
        return marksOfDiscipline;
    }

    private static void checkListForValidity(List<Integer> marks) throws IllegalMarksException {
        for (Integer mark : marks) {
            if ((mark < 0) || (mark > 10)) {
                throw new IllegalMarksException(Messenger.ILLEGAL_MARKS.getTitle());
            }
        }
    }

    @Override
    public String toString() {
        return String.format("\n%s %s", name, marksOfDiscipline);
    }
}