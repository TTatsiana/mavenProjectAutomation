package moduletwo8.hardcore.model;

public enum NumberOfGPUs {

    ONE(339);

    private Integer id;

    public Integer getId() {
        return id;
    }

    NumberOfGPUs(Integer id) {
        this.id = id;
    }
}
