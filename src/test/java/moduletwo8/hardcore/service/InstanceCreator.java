package moduletwo8.hardcore.service;

import moduletwo8.hardcore.model.*;

public class InstanceCreator {
    public  static final String TESTDATA_INSTANCE_NUMBER="testdata.instance.number";

    public static Instance withCredentionalsFromProperty() {
        Instance instance = new Instance.Builder(TestDataReader.getTestData(TESTDATA_INSTANCE_NUMBER)).addSoftware(Software.FREE).addVmClass(VMClass.REGULAR)
                .addType(Type.STANDART8).addGPU(new GPU(NumberOfGPUs.ONE, GPUType.TESLAK80)).addLocalSSD(LocalSSD.SSD2)
                .addLocation(Location.FRANKFURT).addUsage(Usage.YEAR1).build();
        return instance;
    }
}
