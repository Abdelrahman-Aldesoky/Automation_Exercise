package org.example.tests.account;

import org.example.base.BaseTest;
import org.example.pages.account.AccountDeletedPage;
import org.example.pages.account.LoginPage;
import org.example.pages.common.HomePage;
import org.example.pages.common.Navigation;
import org.example.utils.TestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class DeleteAccountTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(DeleteAccountTest.class);

    @Test(description = "Test user account deletion functionality")
    public void testDeleteAccount() {
        // Log the start of the test
        logger.info("Starting account deletion test");
        Reporter.log("Starting account deletion test", true);

        // Initialize navigation and navigate to login page
        Navigation navigation = new Navigation(driver);
        LoginPage loginPage = navigation.navigateToLoginSignup();

        logger.info("Navigated to login page");
        Reporter.log("Navigated to login page", true);

        // Login with existing credentials
        HomePage homePage = loginPage.login(TestData.getEmail(), TestData.getPassword());

        logger.info("Logged in with credentials: {}", TestData.getEmail());
        Reporter.log("Logged in successfully with email: " + TestData.getEmail(), true);

        // Verify login was successful
        Assert.assertTrue(homePage.isLoggedIn(), "User is not logged in");
        logger.info("Verified user is logged in");
        Reporter.log("Verified user is logged in", true);

        // Delete account
        AccountDeletedPage accountDeletedPage = homePage.clickDeleteAccount();
        logger.info("Clicked on Delete Account button");
        Reporter.log("Clicked on Delete Account button", true);

        // Verify account was deleted successfully
        boolean isAccountDeleted = accountDeletedPage.isAccountDeletedSuccessfully();
        Assert.assertTrue(isAccountDeleted, "Account was not deleted successfully");

        logger.info("Account deleted successfully");
        Reporter.log("Account deleted successfully", true);

        // Click continue after account deletion
        homePage = accountDeletedPage.clickContinue();

        logger.info("Clicked continue after account deletion");
        Reporter.log("Clicked continue after account deletion", true);
    }
}