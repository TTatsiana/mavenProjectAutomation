package moduletwo10.framework.bo.builder;

import moduletwo10.framework.bo.model.User;

public class UserBuilder {

    private User user = new User();

    public UserBuilder login(String login) {
        user.setLogin(login);
        return this;
    }

    public UserBuilder password(String password) {
        user.setPassword(password);
        return this;
    }

    public User build() {
        return user;
    }
}
