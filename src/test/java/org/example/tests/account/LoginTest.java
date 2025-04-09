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

    @Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class,
            description = "Test login functionality with different credentials")
    public void testLogin(Map<String, String> data) {
        Reporter.log("Starting login test with credentials: " + data.get("id"), true);
        Reporter.log("Test data: " + data, true);

        Navigation navigation = new Navigation(driver);
        Reporter.log("Navigation object created", true);

        navigation.navigateToLoginSignup();
        Reporter.log("Navigated to login/signup page", true);

        LoginPage loginPage = new LoginPage(driver);
        Reporter.log("Login page object created", true);

        Reporter.log("Attempting login with email: " + data.get("email"), true);
        loginPage.login(data.get("email"), data.get("password"));
        Reporter.log("Login credentials submitted", true);

        HomePage homePage = new HomePage(driver);
        Reporter.log("Home page object created", true);

        boolean shouldSucceed = Boolean.parseBoolean(data.get("shouldSucceed"));
        Reporter.log("Expected login result - shouldSucceed: " + shouldSucceed, true);

        if (shouldSucceed) {
            Assert.assertTrue(homePage.isLoggedIn(), "User should be logged in successfully");
            Reporter.log("Verified user is logged in successfully", true);
        } else {
            Assert.assertFalse(homePage.isLoggedIn(), "User should not be logged in");
            Reporter.log("Verified login failed as expected with invalid credentials", true);
        }

        Reporter.log("Login test completed for: " + data.get("id"), true);
    }
}