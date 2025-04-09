package org.example.tests.account;

import org.example.base.BaseTest;
import org.example.pages.account.AccountCreatedPage;
import org.example.pages.account.LoginPage;
import org.example.pages.account.SignupPage;
import org.example.pages.common.HomePage;
import org.example.pages.common.Navigation;
import org.example.utils.TestDataProvider;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Map;

public class SignupTest extends BaseTest {

    @Test(groups = "signup", dataProvider = "signupData", dataProviderClass = TestDataProvider.class,
            description = "Test user signup functionality with different data")
    public void testUserSignup(Map<String, String> data) {
        // Log the start of the test
        Reporter.log("Starting user signup test with data ID: " + data.get("id"), true);

        // Initialize navigation and navigate to login/signup page
        Navigation navigation = new Navigation(driver);
        navigation.navigateToLoginSignup();
        LoginPage loginPage = new LoginPage(driver);
        Reporter.log("Navigated to login/signup page", true);

        // Create full name from first and last name
        String fullName = data.get("firstName") + " " + data.get("lastName");

        // Use email directly from Excel data without modification
        String email = data.get("email");
        Reporter.log("Using email: " + email, true);

        // Enter signup details and submit
        loginPage.enterSignupDetails(fullName, email);
        Reporter.log("Entered signup details successfully", true);

        // Fill in account details on the signup page
        SignupPage signupPage = new SignupPage(driver);
        signupPage.selectTitle(data.get("title"));
        signupPage.enterPassword(data.get("password"));
        signupPage.selectDateOfBirth(
                data.get("dayOfBirth"),
                data.get("monthOfBirth"),
                data.get("yearOfBirth")
        );
        Reporter.log("Filled personal information successfully", true);

        // Check newsletter and special offers
        signupPage.checkNewsletter();
        signupPage.checkSpecialOffers();
        Reporter.log("Selected newsletter and special offers", true);

        // Enter address information
        signupPage.enterAddressInfo(
                data.get("firstName"),
                data.get("lastName"),
                data.get("address"),
                data.get("state"),
                data.get("city"),
                data.get("zipCode"),
                data.get("mobileNumber")
        );
        Reporter.log("Entered address information successfully", true);

        // Select country and create account
        signupPage.selectCountry(data.get("country"));
        Reporter.log("Selected country: " + data.get("country"), true);

        signupPage.clickCreateAccount();
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        Reporter.log("Clicked create account button", true);

        // Verify account creation success
        boolean isAccountCreated = accountCreatedPage.isAccountCreatedSuccessfully();

        // Check if signup should succeed
        boolean shouldSucceed = true;
        if (data.containsKey("shouldSucceed")) {
            shouldSucceed = Boolean.parseBoolean(data.get("shouldSucceed"));
        }

        if (shouldSucceed) {
            Assert.assertTrue(isAccountCreated, "Account was not created successfully");

            // Continue to homepage after successful account creation
            accountCreatedPage.clickContinue();
            HomePage homePage = new HomePage(driver);
            Reporter.log("Navigated to homepage after successful account creation", true);
        } else {
            Assert.assertFalse(isAccountCreated, "Account should not have been created");
        }
    }
}