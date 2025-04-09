package org.example.tests.account;

import org.example.tests.BaseTest;
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

    @Test(dataProvider = "signupData", dataProviderClass = TestDataProvider.class,
            description = "Test user signup functionality with different data")
    public void testUserSignup(Map<String, String> data) {
        Reporter.log("Starting user signup test with data ID: " + data.get("id"), true);
        Reporter.log("Test data: " + data, true);

        Navigation navigation = new Navigation(driver);
        Reporter.log("Navigation object created", true);

        navigation.navigateToLoginSignup();
        Reporter.log("Navigated to login/signup page", true);

        LoginPage loginPage = new LoginPage(driver);
        Reporter.log("Login page object created", true);

        String fullName = data.get("firstName") + " " + data.get("lastName");
        String email = data.get("email");
        Reporter.log("Preparing to sign up with name: " + fullName + " and email: " + email, true);

        loginPage.enterSignupDetails(fullName, email);
        Reporter.log("Entered signup details and submitted form", true);

        SignupPage signupPage = new SignupPage(driver);
        Reporter.log("Signup page object created", true);

        Reporter.log("Selecting title: " + data.get("title"), true);
        signupPage.selectTitle(data.get("title"));

        Reporter.log("Entering password: [MASKED]", true);
        signupPage.enterPassword(data.get("password"));

        Reporter.log("Selecting date of birth: " + data.get("dayOfBirth") + "/" +
                data.get("monthOfBirth") + "/" + data.get("yearOfBirth"), true);
        signupPage.selectDateOfBirth(
                data.get("dayOfBirth"),
                data.get("monthOfBirth"),
                data.get("yearOfBirth")
        );

        Reporter.log("Checking newsletter subscription", true);
        signupPage.checkNewsletter();

        Reporter.log("Checking special offers", true);
        signupPage.checkSpecialOffers();

        Reporter.log("Entering address information", true);
        signupPage.enterAddressInfo(
                data.get("firstName"),
                data.get("lastName"),
                data.get("address"),
                data.get("state"),
                data.get("city"),
                data.get("zipCode"),
                data.get("mobileNumber")
        );

        Reporter.log("Selecting country: " + data.get("country"), true);
        signupPage.selectCountry(data.get("country"));

        Reporter.log("Clicking create account button", true);
        signupPage.clickCreateAccount();

        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        Reporter.log("Account created page object initialized", true);

        boolean isAccountCreated = accountCreatedPage.isAccountCreatedSuccessfully();
        Reporter.log("Account creation status: " + isAccountCreated, true);

        boolean shouldSucceed = true;
        if (data.containsKey("shouldSucceed")) {
            shouldSucceed = Boolean.parseBoolean(data.get("shouldSucceed"));
        }
        Reporter.log("Expected account creation result - shouldSucceed: " + shouldSucceed, true);

        if (shouldSucceed) {
            Assert.assertTrue(isAccountCreated, "Account was not created successfully");
            Reporter.log("Verified account was created successfully", true);

            Reporter.log("Clicking continue button", true);
            accountCreatedPage.clickContinue();

            HomePage homePage = new HomePage(driver);
            Reporter.log("Navigated to homepage after successful account creation", true);

            Assert.assertTrue(homePage.isLoggedIn(), "User should be logged in after account creation");
            Reporter.log("Verified user is logged in after account creation", true);
        } else {
            Assert.assertFalse(isAccountCreated, "Account should not have been created");
            Reporter.log("Verified account was not created as expected", true);
        }

        Reporter.log("Signup test completed for: " + data.get("id"), true);
    }
}