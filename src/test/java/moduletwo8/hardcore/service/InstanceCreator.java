package moduletwo8.hardcore.service;

import moduletwo8.hardcore.model.*;

public class InstanceCreator {
    public static final String TESTDATA_INSTANCE_NUMBER = "testdata.instance.number";
    public static final String TESTDATA_INSTANCE_SOFTWARE = "testdata.instance.software";
    public static final String TESTDATA_INSTANCE_VMCLASS = "testdata.instance.vmClass";
    public static final String TESTDATA_INSTANCE_TYPE = "testdata.instance.type";
    public static final String TESTDATA_INSTANCE_GPU_NUMBER = "testdata.instance.gpuNumber";
    public static final String TESTDATA_INSTANCE_GPU_TYPE = "testdata.instance.gpuType";
    public static final String TESTDATA_INSTANCE_SSD = "testdata.instance.ssd";
    public static final String TESTDATA_INSTANCE_LOCATION = "testdata.instance.location";
    public static final String TESTDATA_INSTANCE_USAGE = "testdata.instance.usage";

    public static Instance withCredentionalsFromProperty() {
        Instance instance = new Instance.Builder(TestDataReader.getTestData(TESTDATA_INSTANCE_NUMBER))
                .addSoftware(Software.valueOf(TestDataReader.getTestData(TESTDATA_INSTANCE_SOFTWARE)))
                .addVmClass(VMClass.valueOf(TestDataReader.getTestData(TESTDATA_INSTANCE_VMCLASS)))
                .addType(Type.valueOf(TestDataReader.getTestData(TESTDATA_INSTANCE_TYPE)))
                .addGPU(new GPU(NumberOfGPUs.valueOf(TestDataReader.getTestData(TESTDATA_INSTANCE_GPU_NUMBER)),
                        GPUType.valueOf(TestDataReader.getTestData(TESTDATA_INSTANCE_GPU_TYPE))))
                .addLocalSSD(LocalSSD.valueOf(TestDataReader.getTestData(TESTDATA_INSTANCE_SSD)))
                .addLocation(Location.valueOf(TestDataReader.getTestData(TESTDATA_INSTANCE_LOCATION)))
                .addUsage(Usage.valueOf(TestDataReader.getTestData(TESTDATA_INSTANCE_USAGE)))
                .build();
        return instance;
    }
}
