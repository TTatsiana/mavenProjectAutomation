package moduletwo10.yandex.service;

import moduletwo10.framework.bo.model.User;
import moduletwo10.framework.loger.Log;
import moduletwo10.yandex.page.YandexAccountPage;

public class AccountService {

    public void signIn(User user) {
        Log.info("Type login and password");
        new YandexAccountPage()
                .typeLogin(user.getLogin())
                .clickSubmit()
                .typePassword(user.getPassword())
                .clickSubmit();
    }
}
