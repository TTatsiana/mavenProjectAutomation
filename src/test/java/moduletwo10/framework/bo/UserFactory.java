package moduletwo10.framework.bo;

import moduletwo10.yandex.page.constants.StringСonstants;

public class UserFactory {

    public static User getDefaultUser(){
       User user= new UserBuilder()
               .login(StringСonstants.VALID_LOGIN)
               .password(StringСonstants.VALID_PASSWD)
               .build();
       return user;
    }

    public static User getWrongUser(){
        User user = new User(StringСonstants.WRONG_LOGIN, StringСonstants.WRONG_PASSWD);
        return user;
    }
}
