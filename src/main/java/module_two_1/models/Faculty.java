package module_two_1.models;

import module_two_1.exceptions.NullGroupException;
import module_two_1.models.enums.FacultiesNames;
import module_two_1.models.enums.Messenger;

import java.util.List;

public class Faculty {
    private FacultiesNames name;
    private List<Group> groups;

    public Faculty(FacultiesNames name, List<Group> groups) throws NullGroupException {
        if (groups.isEmpty() || groups.contains(null)) {
            throw new NullGroupException(Messenger.NULL_GROUP.toString());
        }
        this.name = name;
        this.groups = groups;
    }

    public FacultiesNames getName() {
        return name;
    }

    public List<Group> getGroups() {
        return groups;
    }

    @Override
    public String toString() {
        return String.format("\n%s, %s", name, groups);
    }
}
