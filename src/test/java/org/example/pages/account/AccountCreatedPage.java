package org.example.pages.account;

import org.example.pages.BasePage;
import org.example.pages.common.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountCreatedPage extends BasePage {

    @FindBy(xpath = "//h2[@data-qa='account-created']/b")
    private WebElement successMessage;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    private WebElement continueButton;

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAccountCreatedSuccessfully() {
        return isElementDisplayed(successMessage) &&
                getText(successMessage).contains("ACCOUNT CREATED");
    }

    public HomePage clickContinue() {
        click(continueButton);
        return new HomePage(driver);
    }
}