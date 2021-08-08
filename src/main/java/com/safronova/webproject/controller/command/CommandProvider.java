package com.safronova.webproject.controller.command;

import com.safronova.webproject.controller.command.impl.*;
import com.safronova.webproject.controller.command.impl.admin.impl.AddDessertCommand;
import com.safronova.webproject.controller.command.impl.admin.impl.ChangeOrderStatusCommand;
import com.safronova.webproject.controller.command.impl.admin.impl.DeleteDessertCommand;
import com.safronova.webproject.controller.command.impl.admin.impl.UpdateDessertCommand;
import com.safronova.webproject.controller.command.impl.go.*;
import com.safronova.webproject.controller.command.impl.user.impl.*;

import java.util.EnumMap;

public class CommandProvider {
    private static CommandProvider instance;
    private final EnumMap<CommandType, Command> commands = new EnumMap(CommandType.class);

    public CommandProvider() {
        commands.put(CommandType.GO_TO_HOME_PAGE_COMMAND, new GoToHomePageCommand());
        commands.put(CommandType.CHANGE_LOCALE_COMMAND, new ChangeLocaleCommand());
        commands.put(CommandType.GO_TO_SIGNIN_PAGE_COMMAND, new GoToSigninPageCommand());
        commands.put(CommandType.GO_TO_SIGNUP_PAGE_COMMAND, new GoToSignupPageCommand());
        commands.put(CommandType.SIGN_IN_COMMAND, new SignInCommand());
        commands.put(CommandType.SIGN_UP_COMMAND, new SignUpCommand());
        commands.put(CommandType.GO_TO_PROFILE_PAGE_COMMAND, new GoToProfilePageCommand());
        commands.put(CommandType.GO_TO_ORDER_HISTORY_PAGE_COMMAND, new GoToOrderHistoryPageCommand());
        commands.put(CommandType.SETTINGS_EDIT_COMMAND, new SettingsEditCommand());
        commands.put(CommandType.LOG_OUT_COMMAND, new LogoutCommand());
        commands.put(CommandType.GO_TO_ERROR_PAGE_COMMAND, new GoToErrorPageCommand());
        commands.put(CommandType.GO_TO_ITEM_PAGE_COMMAND, new GoToItemPageCommand());
        commands.put(CommandType.FIND_DESSERT_BY_CATEGORY_COMMAND, new FindDessertByCategoryCommand());
        commands.put(CommandType.UPDATE_BASKET_COMMAND, new UpdateBasketCommand());
        commands.put(CommandType.REMOVE_DESSERT_COMMAND, new RemoveDessertCommand());
        commands.put(CommandType.PLACE_ORDER_COMMAND, new PlaceOrderCommand());
        commands.put(CommandType.ADD_DESSERT_TO_BASKET_COMMAND, new AddDessertToBasketCommand());
        commands.put(CommandType.GO_TO_BASKET_PAGE_COMMAND, new GoToBasketPageCommand());
        commands.put(CommandType.GO_TO_CHECK_OUT_PAGE_COMMAND, new GoToCheckoutPageCommand());
        commands.put(CommandType.GO_TO_ADD_DESSERT_PAGE_COMMAND, new GoToAddDessertPageCommand());
        commands.put(CommandType.GO_TO_DESSERT_LIST_PAGE_COMMAND, new GoToDessertListPageCommand());
        commands.put(CommandType.GO_TO_ORDER_DETAIL_PAGE_COMMAND, new GoToOrderDetailPageCommand());
        commands.put(CommandType.GO_TO_ORDERS_PAGE_COMMAND, new GoToOrdersPageCommand());
        commands.put(CommandType.GO_TO_UPDATE_DESSERT_PAGE_COMMAND, new GoToUpdateDessertPageCommand());
        commands.put(CommandType.CHANGE_ORDER_STATUS_COMMAND, new ChangeOrderStatusCommand());
        commands.put(CommandType.ADD_DESSERT_COMMAND, new AddDessertCommand());
        commands.put(CommandType.DELETE_DESSERT_COMMAND, new DeleteDessertCommand());
        commands.put(CommandType.UPDATE_DESSERT_COMMAND, new UpdateDessertCommand());
        commands.put(CommandType.GO_TO_DESSERT_DETAIL_PAGE_COMMAND, new GoToDessertDetailPageCommand());

    }

    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }

    public Command getCommand(String commandName) {
        if (commandName == null) {
            return commands.get(CommandType.DEFAULT);
        }
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = CommandType.DEFAULT;
        }
        return commands.get(commandType);
    }
}
