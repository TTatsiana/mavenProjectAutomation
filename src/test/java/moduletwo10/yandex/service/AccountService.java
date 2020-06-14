package moduletwo10.yandex.service;

import moduletwo10.framework.bo.User;
import moduletwo10.framework.loger.Log;
import moduletwo10.yandex.page.YandexAccountPage;

public class AccountService {

    public void signIn(User user) {
        new YandexAccountPage()
                .typeLogin(user.getLogin())
                .clickSubmit()
                .typePassword(user.getPassword())
                .clickSubmit();
        Log.info("Type login and password");
    }
}
