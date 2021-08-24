package com.safronova.webproject.model.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DessertValidatorTest {
    @DataProvider(name = "validName")
    public static Object[][] validName() {
        return new Object[][]{{"Cheesecake"}, {"Pecan tart"}, {"Dessert Pavlova"}};
    }

    @DataProvider(name = "invalidName")
    public static Object[][] invalidName() {
        return new Object[][]{{"1"}, {null}, {"S#{/}f"}};
    }

    @DataProvider(name = "validDescription")
    public static Object[][] validDescription() {
        return new Object[][]{{"Swiss meringue, fresh cherries combined with guava puree, whipped cream with mascarpone cheese."},
                {"Almond saber, almond cream baked with apricot and Adyghe cheese, fresh apricot"},
                {"Bourbon vanilla cheese cream, whole grain shortbread base with oatmeal, fresh berries."}};
    }

    @DataProvider(name = "invalidDescription")
    public static Object[][] invalidDescription() {
        return new Object[][]{{"f"}, {"aaaa"}, {""}};
    }

    @DataProvider(name = "validPrice")
    public static Object[][] validPrice() {
        return new Object[][]{{"10.00"}, {"15.50"}, {"717.30"}};
    }

    @DataProvider(name = "invalidPrice")
    public static Object[][] invalidPrice() {
        return new Object[][]{{null}, {"1.2"}, {"12345.60"}};
    }

    @DataProvider(name = "validWeight")
    public static Object[][] validWeight() {
        return new Object[][]{{"100"}, {"175"}, {"20"}};
    }

    @DataProvider(name = "invalidWeight")
    public static Object[][] invalidWeight() {
        return new Object[][]{{"1234567"}, {"20.2"}, {""}};
    }

    @DataProvider(name = "validId")
    public static Object[][] validId() {
        return new Object[][]{{"1"}, {"2"}, {"3"}};
    }

    @DataProvider(name = "invalidId")
    public static Object[][] invalidId() {
        return new Object[][]{{""}, {"anna"}, {"#"}};
    }

    @Test(dataProvider = "validName")
    public void validateNamePositiveTest(String name) {
        Assert.assertTrue(DessertValidator.validateName(name));
    }

    @Test(dataProvider = "invalidName")
    public void validateNameNegativeTest(String name) {
        Assert.assertFalse(DessertValidator.validateName(name));
    }

    @Test(dataProvider = "validDescription")
    public void validateDescriptionPositiveTest(String description) {
        Assert.assertTrue(DessertValidator.validateDescription(description));
    }

    @Test(dataProvider = "invalidDescription")
    public void validateDescriptionNegativeTest(String description) {
        Assert.assertFalse(DessertValidator.validateDescription(description));
    }

    @Test(dataProvider = "validPrice")
    public void validatePricePositiveTest(String price) {
        Assert.assertTrue(DessertValidator.validatePrice(price));
    }

    @Test(dataProvider = "invalidPrice")
    public void validatePriceNegativeTest(String price) {
        Assert.assertFalse(DessertValidator.validatePrice(price));
    }

    @Test(dataProvider = "validWeight")
    public void validateWeightPositiveTest(String weight) {
        Assert.assertTrue(DessertValidator.validateWeight(weight));
    }

    @Test(dataProvider = "invalidWeight")
    public void validateWeightNegativeTest(String weight) {
        Assert.assertFalse(DessertValidator.validatePrice(weight));
    }

    @Test(dataProvider = "validId")
    public void validateIdPositiveTest(String id) {
        Assert.assertTrue(DessertValidator.validateDessertTypeId(id));
    }

    @Test(dataProvider = "invalidId")
    public void validateIdNegativeTest(String id) {
        Assert.assertFalse(DessertValidator.validateDessertTypeId(id));
    }
}
