package org.example.tests.account;

import org.example.base.BaseTest;
import org.example.pages.account.AccountDeletedPage;
import org.example.pages.account.LoginPage;
import org.example.pages.common.HomePage;
import org.example.pages.common.Navigation;
import org.example.utils.TestDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Map;

public class DeleteAccountTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(DeleteAccountTest.class);

    @Test(dependsOnGroups = "purchase", dataProvider = "loginData", dataProviderClass = TestDataProvider.class,
            description = "Test user account deletion functionality")
    public void testDeleteAccount(Map<String, String> data) {
        logger.info("Starting account deletion test for account: {}", data.get("id"));
        Reporter.log("Starting account deletion test for account: " + data.get("id"), true);

        Navigation navigation = new Navigation(driver);
        navigation.navigateToLoginSignup();
        LoginPage loginPage = new LoginPage(driver);
        Reporter.log("Navigated to login page", true);

        loginPage.login(data.get("email"), data.get("password"));
        HomePage homePage = new HomePage(driver);
        Reporter.log("Attempted login with email: " + data.get("email"), true);

        boolean shouldSucceed = Boolean.parseBoolean(data.get("shouldSucceed"));

        if (shouldSucceed) {
            // Only verify login and attempt deletion if login should succeed
            Assert.assertTrue(homePage.isLoggedIn(), "User is not logged in");
            Reporter.log("Verified user is logged in", true);

            homePage.clickDeleteAccount();
            AccountDeletedPage accountDeletedPage = new AccountDeletedPage(driver);
            Reporter.log("Clicked on Delete Account button", true);

            boolean isAccountDeleted = accountDeletedPage.isAccountDeletedSuccessfully();
            Assert.assertTrue(isAccountDeleted, "Account was not deleted successfully");
            Reporter.log("Account deleted successfully", true);

            accountDeletedPage.clickContinue();
            homePage = new HomePage(driver);
            Reporter.log("Clicked continue after account deletion", true);
        } else {
            // For invalid credentials, verify login failed
            Assert.assertFalse(homePage.isLoggedIn(), "User should not be logged in with invalid credentials");
            Reporter.log("Verified login failed as expected", true);
        }
    }
}