package org.example.pages.account;

import org.example.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    // Login section elements
    @FindBy(xpath = "//input[@data-qa='login-email']")
    private WebElement loginEmailField;

    @FindBy(xpath = "//input[@data-qa='login-password']")
    private WebElement loginPasswordField;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    private WebElement loginButton;

    // Signup section elements
    @FindBy(xpath = "//input[@data-qa='signup-name']")
    private WebElement signupNameField;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    private WebElement signupEmailField;

    @FindBy(xpath = "//button[@data-qa='signup-button']")
    private WebElement signupButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        type(loginEmailField, email);
        type(loginPasswordField, password);
        click(loginButton);
    }

    public void enterSignupDetails(String name, String email) {
        type(signupNameField, name);
        type(signupEmailField, email);
        click(signupButton);
    }
}