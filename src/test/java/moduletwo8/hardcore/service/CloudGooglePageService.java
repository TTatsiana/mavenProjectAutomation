package moduletwo8.hardcore.service;

import moduletwo8.hardcore.page.CloudGoogleCalculatorPage;
import moduletwo8.hardcore.page.CloudGooglePage;

public class CloudGooglePageService {

    public static CloudGoogleCalculatorPage goToCloudGoogleCalculatorPage(String str) {
        return CloudGooglePage.openPage().clickToSearch().typeTextInSearchBox(str).sendEnterAndGoToCalculatorPage();
    }
}
