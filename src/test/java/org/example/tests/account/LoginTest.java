package org.example.tests.account;

import org.example.tests.BaseTest;
import org.example.pages.account.LoginPage;
import org.example.pages.common.HomePage;
import org.example.pages.common.Navigation;
import org.example.utils.TestDataProvider;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Map;

public class LoginTest extends BaseTest {

    @Test(groups = "login", dependsOnGroups = "signup", dataProvider = "loginData", dataProviderClass = TestDataProvider.class,
            description = "Test login functionality with different credentials")
    public void testLogin(Map<String, String> data) {
        Reporter.log("Starting login test with credentials: " + data.get("id"), true);

        Navigation navigation = new Navigation(driver);
        navigation.navigateToLoginSignup();
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(data.get("email"), data.get("password"));
        HomePage homePage = new HomePage(driver);

        boolean shouldSucceed = Boolean.parseBoolean(data.get("shouldSucceed"));
        if (shouldSucceed) {
            Assert.assertTrue(homePage.isLoggedIn(), "User should be logged in successfully");
        } else {
            Assert.assertFalse(homePage.isLoggedIn(), "User should not be logged in");
        }
    }
}