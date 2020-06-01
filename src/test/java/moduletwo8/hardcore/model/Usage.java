package moduletwo8.hardcore.model;

public enum Usage {

    YEAR1(90);

    private Integer id;

    public Integer getId() {
        return id;
    }

    Usage(Integer id) {
        this.id = id;
    }
}
