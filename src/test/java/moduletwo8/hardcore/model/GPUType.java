package moduletwo8.hardcore.model;

public enum GPUType {

    TESLAK80(346);

    private Integer id;

    public Integer getId() {
        return id;
    }

    GPUType(Integer id) {
        this.id = id;
    }
}
