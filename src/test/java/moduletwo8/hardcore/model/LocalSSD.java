package moduletwo8.hardcore.model;

public enum LocalSSD {

    SSD2(233);

    private Integer id;

    LocalSSD(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
