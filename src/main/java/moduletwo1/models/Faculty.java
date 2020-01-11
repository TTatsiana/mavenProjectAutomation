package moduletwo1.models;

import moduletwo1.exceptions.NullGroupException;
import moduletwo1.models.enums.FacultiesNames;
import moduletwo1.models.enums.Messenger;

import java.util.List;

public class Faculty {

    private FacultiesNames name;
    private List<Group> groups;

    public Faculty(FacultiesNames name, List<Group> groups) throws NullGroupException {
        if (groups.isEmpty() || groups.contains(null)) {
            throw new NullGroupException(Messenger.NULL_GROUP.getTitle());
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
