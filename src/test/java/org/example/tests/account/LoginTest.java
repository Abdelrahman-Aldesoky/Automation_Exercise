package org.example.tests.account;

import org.example.base.BaseTest;
import org.example.pages.account.LoginPage;
import org.example.pages.common.HomePage;
import org.example.pages.common.Navigation;
import org.example.utils.TestData;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Test successful login functionality")
    public void testSuccessfulLogin() {
        Reporter.log("Starting login test", true);

        Navigation navigation = new Navigation(driver);
        LoginPage loginPage = navigation.navigateToLoginSignup();
        Reporter.log("Navigated to login page", true);

        HomePage homePage = loginPage.login(TestData.getEmail(), TestData.getPassword());
        Reporter.log("Performed login with credentials", true);

        Assert.assertTrue(homePage.isLoggedIn(), "User should be logged in successfully");
        Reporter.log("Verified user is logged in", true);
    }
}