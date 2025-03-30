package org.example.tests.account;

import org.example.base.BaseTest;
import org.example.pages.account.AccountCreatedPage;
import org.example.pages.account.LoginPage;
import org.example.pages.account.SignupPage;
import org.example.pages.common.HomePage;
import org.example.pages.common.Navigation;
import org.example.utils.TestData;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class SignupTest extends BaseTest {

    @Test(description = "Test user signup functionality")
    public void testUserSignup() {
        // Log the start of the test
        Reporter.log("Starting user signup test", true);

        // Initialize navigation and navigate to login/signup page
        Navigation navigation = new Navigation(driver);
        LoginPage loginPage = navigation.navigateToLoginSignup();
        Reporter.log("Navigated to login/signup page", true);

        // Create a unique email with timestamp to avoid duplicate registration issues
        //String uniqueEmail = "abdelrahman.test" + System.currentTimeMillis() + "@example.com";
        //Reporter.log("Using email: " + uniqueEmail, true);

        String email = TestData.getEmail();
        Reporter.log("Using email: " + email, true);

        // Enter signup details and submit
        loginPage.enterSignupDetails(TestData.getFullName(), email);
        Reporter.log("Entered signup details successfully", true);

        // Fill in account details on the signup page
        SignupPage signupPage = new SignupPage(driver);
        signupPage.selectTitle(TestData.getTitle());
        signupPage.enterPassword(TestData.getPassword());
        signupPage.selectDateOfBirth(
                TestData.getDayOfBirth(),
                TestData.getMonthOfBirth(),
                TestData.getYearOfBirth()
        );
        Reporter.log("Filled personal information successfully", true);

        // Check newsletter and special offers
        signupPage.checkNewsletter();
        signupPage.checkSpecialOffers();
        Reporter.log("Selected newsletter and special offers", true);

        // Enter address information
        signupPage.enterAddressInfo(
                TestData.getFirstName(),
                TestData.getLastName(),
                TestData.getAddress(),
                TestData.getState(),
                TestData.getCity(),
                TestData.getZipCode(),
                TestData.getMobileNumber()
        );
        Reporter.log("Entered address information successfully", true);

        // Select country and create account
        signupPage.selectCountry(TestData.getCountry());
        Reporter.log("Selected country: " + TestData.getCountry(), true);

        AccountCreatedPage accountCreatedPage = signupPage.clickCreateAccount();
        Reporter.log("Clicked create account button", true);

        // Verify account creation success
        boolean isAccountCreated = accountCreatedPage.isAccountCreatedSuccessfully();
        Assert.assertTrue(isAccountCreated, "Account was not created successfully");

        // Continue to homepage after successful account creation
        HomePage homePage = accountCreatedPage.clickContinue();
        Reporter.log("Navigated to homepage after successful account creation", true);
    }
}