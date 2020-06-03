package moduletwo8.hardcore.model;

public enum Software {

    FREE(60);

    private Integer id;

    Software(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
