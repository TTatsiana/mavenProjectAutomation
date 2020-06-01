package moduletwo8.hardcore.model;

public enum VMClass {

    REGULAR(72);

    private Integer id;

    VMClass(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
