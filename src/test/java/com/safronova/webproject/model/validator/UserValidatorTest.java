package com.safronova.webproject.model.validator;

import com.safronova.webproject.model.entity.SignUpData;
import com.safronova.webproject.model.entity.User;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserValidatorTest {
    @DataProvider(name = "validEmail")
    public static Object[][] validEmail() {
        return new Object[][]{{"safronova9821@gmail.com"}, {"anya.safrr@gmail.com"}, {"anna.safrr@gmail.com"}};
    }

    @DataProvider(name = "invalidEmail")
    public static Object[][] invalidEmail() {
        return new Object[][]{{"anya.safronova@gmailcom"}, {null}, {"Anna"}};
    }

    @DataProvider(name = "validUsername")
    public static Object[][] validUsername() {
        return new Object[][]{{"anna2001"}, {"Safrrr2"}, {"anyto4ka"}};
    }

    @DataProvider(name = "invalidUsername")
    public static Object[][] invalidUsername() {
        return new Object[][]{{"a"}, {"2"}, {""}};
    }

    @DataProvider(name = "validFIO")
    public static Object[][] validFIO() {
        return new Object[][]{{"Anna"}, {"Safronova"}, {"Сафронова"}};
    }

    @DataProvider(name = "invalidFIO")
    public static Object[][] invalidFIO() {
        return new Object[][]{{"Anna222"}, {""}, {"anya_1w"}};
    }

    @DataProvider(name = "validPhone")
    public static Object[][] validPhone() {
        return new Object[][]{{"+375291020398"}, {"+375441122334"}, {"+375331010101"}};
    }

    @DataProvider(name = "invalidPhone")
    public static Object[][] invalidPhone() {
        return new Object[][]{{"375298976542"}, {""}, {"+2537281"}};
    }

    @Test(dataProvider = "validEmail")
    public void validateEmailPositiveTest(String email) {
        Assert.assertTrue(UserValidator.validateEmail(email));
    }

    @Test(dataProvider = "invalidEmail")
    public void validateEmailNegativeTest(String email) {
        Assert.assertFalse(UserValidator.validateEmail(email));
    }

    @Test(dataProvider = "validUsername")
    public void validateUsernamePositiveTest(String username) {
        Assert.assertTrue(UserValidator.validateUsername(username));
    }

    @Test(dataProvider = "invalidUsername")
    public void validateUsernameNegativeTest(String username) {
        Assert.assertFalse(UserValidator.validateUsername(username));
    }

    @Test(dataProvider = "validFIO")
    public void validateFIOPositiveTest(String name) {
        Assert.assertTrue(UserValidator.validateFIO(name));
    }

    @Test(dataProvider = "invalidFIO")
    public void validateFIONegativeTest(String name) {
        Assert.assertFalse(UserValidator.validateFIO(name));
    }

    @Test(dataProvider = "validPhone")
    public void validatePhonePositiveTest(String phone) {
        Assert.assertTrue(UserValidator.validatePhoneNumber(phone));
    }

    @Test(dataProvider = "invalidPhone")
    public void validatePhoneNegativeTest(String phone) {
        Assert.assertFalse(UserValidator.validatePhoneNumber(phone));
    }
}

