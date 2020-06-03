package moduletwo8.hardcore.model;

public class GPU {

    private NumberOfGPUs number;
    private GPUType type;

    public GPU(NumberOfGPUs number, GPUType type) {
        this.number = number;
        this.type = type;
    }

    public NumberOfGPUs getNumber() {
        return number;
    }

    public GPUType getType() {
        return type;
    }
}
