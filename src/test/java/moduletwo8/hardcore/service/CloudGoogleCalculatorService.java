package moduletwo8.hardcore.service;

import moduletwo8.hardcore.model.Instance;
import moduletwo8.hardcore.page.CloudGoogleCalculatorPage;
import moduletwo8.hardcore.page.ResultsPastebinPage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CloudGoogleCalculatorService {

    public static ResultsPastebinPage fillPageInstanceAndAddToEstimate(Instance instance) {
        CloudGoogleCalculatorPage calculatorPage = new CloudGoogleCalculatorPage();
        if (instance.getNumber() != null) {
            calculatorPage.fillNumberOfInstances(instance.getNumber().toString());
        }
        if (instance.getSoftware() != null) {
            calculatorPage.selectSoftware(instance.getSoftware().getId());
        }
        if (instance.getVmClass() != null) {
            calculatorPage.selectVmClass(instance.getVmClass().getId());
        }
        if (instance.getType() != null) {
            calculatorPage.selectMachineType(instance.getType().getId());
        }
        if (instance.getGpu() != null) {
            calculatorPage.addGPU(instance.getGpu().getNumber().getId(),
                    instance.getGpu().getType().getId());
        }
        if (instance.getSsd() != null) {
            calculatorPage.selectLocalSSD(instance.getSsd().getId());
        }
        if (instance.getLocation() != null) {
            calculatorPage.selectLocation(instance.getLocation().getId());
        }
        if (instance.getUsage() != null) {
            calculatorPage.selectCommittedUsage(instance.getUsage().getId());
        }
        return calculatorPage.clickByAddToEstimate();
    }

    public static String findCostInTheLine(String startLine) {
        String regExp = "USD\\s\\d+.\\d+";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(startLine);
        return matcher.find() ? matcher.group() : null;
    }
}
