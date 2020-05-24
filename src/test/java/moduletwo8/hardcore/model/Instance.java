package moduletwo8.hardcore.model;

public class Instance {

    private Integer number;
    private Software software;
    private VMClass vmClass;
    private Type type;
    private GPU gpu;
    private LocalSSD ssd;
    private Location location;
    private Usage usage;

    public static class Builder {
        private Instance newInstance;

        public Builder(String number) {
            this.newInstance = new Instance();
            newInstance.number = Integer.parseInt(number);
        }

        public Instance build() {
            return newInstance;
        }

        public Builder addUsage(Usage usage) {
            newInstance.usage = usage;
            return this;
        }

        public Builder addLocation(Location location) {
            newInstance.location = location;
            return this;
        }

        public Builder addLocalSSD(LocalSSD ssd) {
            newInstance.ssd = ssd;
            return this;
        }

        public Builder addSoftware(Software software) {
            newInstance.software = software;
            return this;
        }

        public Builder addGPU(GPU gpu) {
            newInstance.gpu = gpu;
            return this;
        }

        public Builder addVmClass(VMClass vmClass) {
            newInstance.vmClass = vmClass;
            return this;
        }

        public Builder addType(Type type) {
            newInstance.type = type;
            return this;
        }
    }

//    public Instance() {
//    }
//
    public Usage getUsage() {
        return usage;
    }
//
//    public void setNumber(Integer number) {
//        this.number = number;
//    }
//
//    public void setSoftware(Software software) {
//        this.software = software;
//    }
//
//    public void setVmClass(VMClass vmClass) {
//        this.vmClass = vmClass;
//    }
//
//    public void setType(Type type) {
//        this.type = type;
//    }
//
//    public void setGpu(GPU gpu) {
//        this.gpu = gpu;
//    }
//
//    public void setSsd(LocalSSD ssd) {
//        this.ssd = ssd;
//    }
//
//    public void setLocation(Location location) {
//        this.location = location;
//    }
//
//    public void setUsage(Usage usage) {
//        this.usage = usage;
//    }

    public Integer getNumber() {
        return number;
    }

    public Location getLocation() {
        return location;
    }

    public LocalSSD getSsd() {
        return ssd;
    }

    public Software getSoftware() {
        return software;
    }

    public VMClass getVmClass() {
        return vmClass;
    }

    public Type getType() {
        return type;
    }

    public GPU getGpu() {
        return gpu;
    }
}
