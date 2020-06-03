package moduletwo8.hardcore.model;

public enum Location {

    FRANKFURT(181);

    private Integer id;

    public Integer getId() {
        return id;
    }

    Location(Integer id) {
        this.id = id;
    }
}
