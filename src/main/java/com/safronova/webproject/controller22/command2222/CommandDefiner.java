package com.safronova.webproject.controller22.command2222;

import com.safronova.webproject.controller22.EnumValue;
import com.safronova.webproject.controller22.command2222.impl.go.*;
import com.safronova.webproject.controller22.command2222.impl.go.error.GoTo400ErrorPageCommand;
import com.safronova.webproject.controller22.command2222.impl.go.error.GoTo403ErrorPageCommand;
import com.safronova.webproject.controller22.command2222.impl.go.error.GoTo404ErrorPageCommand;
import com.safronova.webproject.controller22.command2222.impl.go.error.GoTo500ErrorPageCommand;
import com.safronova.webproject.controller22.command2222.impl.locale.ChangeLocaleCommand;
import com.safronova.webproject.controller22.command2222.impl.user.SignInUserCommand;
import com.safronova.webproject.controller22.command2222.impl.user.SignUpUserCommand;

public enum CommandDefiner implements EnumValue {

    GO_TO_HOME_PAGE("go_to_home_page_command", new GoToHomePageCommand()),
    GO_TO_SIGNIN_PAGE("go_to_signIn_page_command", new GoToSignInPageCommand()),
    GO_TO_SIGNUP_PAGE("go_to_signUp_page_command", new GoToSignUpPageCommand()),
    GO_TO_EMAIL_PAGE("go_to_email_check_page_command", new GoToEmailCheckPageCommand()),
    GO_TO_USER_PROFILE_PAGE("user-profile-page", new GoToUserProfilePageCommand()),
    GO_TO_400_ERROR_PAGE("400", new GoTo400ErrorPageCommand()),
    GO_TO_403_ERROR_PAGE("403", new GoTo403ErrorPageCommand()),
    GO_TO_404_ERROR_PAGE("404", new GoTo404ErrorPageCommand()),
    GO_TO_500_ERROR_PAGE("500", new GoTo500ErrorPageCommand()),
    CHANGE_LOCALE("change_locale", new ChangeLocaleCommand()),
    SIGNUP_USER("signUp_user_command", new SignUpUserCommand()),
    SIGNIN_USER("signIn_user_command", new SignInUserCommand());



    private String value;
    private Command command;

    private CommandDefiner(String value, Command command) {
        this.value = value;
        this.command = command;
    }

    @Override
    public String getValue() {
        return value;
    }

    public Command getCommand() {
        return command;
    }

    public static Command defineCommand(String commandName) {
        CommandDefiner commandDefiner = CommandDefiner.fromString(commandName);
        Command command = commandDefiner.getCommand();
        return command;
    }

    public static CommandDefiner fromString(String commandName) {
        CommandDefiner result = GO_TO_404_ERROR_PAGE;
        for (CommandDefiner commandDefiner : CommandDefiner.values()) {
            if (commandDefiner.getValue().equals(commandName)) {
                result = commandDefiner;
            }
        }
        return result;
    }


}




