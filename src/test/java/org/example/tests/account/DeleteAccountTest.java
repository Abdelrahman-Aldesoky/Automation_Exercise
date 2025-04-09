package org.example.tests.account;

import org.example.tests.BaseTest;
import org.example.pages.account.AccountDeletedPage;
import org.example.pages.account.LoginPage;
import org.example.pages.common.HomePage;
import org.example.pages.common.Navigation;
import org.example.utils.TestDataProvider;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Map;

public class DeleteAccountTest extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class,
            description = "Test user account deletion functionality")
    public void testDeleteAccount(Map<String, String> data) {
        Reporter.log("Starting account deletion test for account: " + data.get("id"), true);
        Reporter.log("Test data: " + data, true);

        Navigation navigation = new Navigation(driver);
        navigation.navigateToLoginSignup();
        Reporter.log("Navigated to login/signup page", true);

        LoginPage loginPage = new LoginPage(driver);
        Reporter.log("Created login page object", true);

        Reporter.log("Attempting to login with email: " + data.get("email"), true);
        loginPage.login(data.get("email"), data.get("password"));
        Reporter.log("Login attempt completed", true);

        HomePage homePage = new HomePage(driver);
        Reporter.log("Created home page object", true);

        boolean shouldSucceed = Boolean.parseBoolean(data.get("shouldSucceed"));
        Reporter.log("Expected login result - shouldSucceed: " + shouldSucceed, true);

        if (shouldSucceed) {
            // Only verify login and attempt deletion if login should succeed
            Assert.assertTrue(homePage.isLoggedIn(), "User is not logged in");
            Reporter.log("Verified user is logged in successfully", true);

            Reporter.log("Clicking on Delete Account button", true);
            homePage.clickDeleteAccount();
            AccountDeletedPage accountDeletedPage = new AccountDeletedPage(driver);
            Reporter.log("Navigated to Account Deleted page", true);

            boolean isAccountDeleted = accountDeletedPage.isAccountDeletedSuccessfully();
            Assert.assertTrue(isAccountDeleted, "Account was not deleted successfully");
            Reporter.log("Verified account deleted successfully", true);

            Reporter.log("Clicking continue button", true);
            accountDeletedPage.clickContinue();
            homePage = new HomePage(driver);
            Reporter.log("Navigated back to homepage", true);
        } else {
            // For invalid credentials, verify login failed
            Assert.assertFalse(homePage.isLoggedIn(), "User should not be logged in with invalid credentials");
            Reporter.log("Verified login failed as expected with invalid credentials", true);
        }
        Reporter.log("Account deletion test completed for: " + data.get("id"), true);
    }
}