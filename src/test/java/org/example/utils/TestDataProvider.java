package org.example.utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return ExcelUtils.getTestData("login_data.xlsx", "Credentials");
    }

    @DataProvider(name = "signupData")
    public Object[][] getSignupData() {
        return ExcelUtils.getTestData("signup_data.xlsx", "UserData");
    }

    @DataProvider(name = "purchaseTestData")
    public Object[][] getPurchaseTestData() {
        return ExcelUtils.getTestData("purchase_test_data.xlsx", "TestData");
    }
}