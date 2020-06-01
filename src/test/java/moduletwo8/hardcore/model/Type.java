package moduletwo8.hardcore.model;

public enum Type {

    STANDART8(212);

    private Integer id;

    Type(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
