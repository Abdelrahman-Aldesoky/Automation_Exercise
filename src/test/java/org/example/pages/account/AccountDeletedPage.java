package org.example.pages.account;

import org.example.pages.BasePage;
import org.example.pages.common.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountDeletedPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(AccountDeletedPage.class);

    @FindBy(xpath = "//h2[@data-qa='account-deleted']")
    private WebElement accountDeletedMessage;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    private WebElement continueButton;

    public AccountDeletedPage(WebDriver driver) {
        super(driver);
        logger.info("Account Deleted Page initialized");
    }

    public boolean isAccountDeletedSuccessfully() {
        boolean isDeleted = isElementDisplayed(accountDeletedMessage);
        logger.info("Account deletion verification: {}", isDeleted);
        return isDeleted;
    }

    public HomePage clickContinue() {
        logger.info("Clicking continue button after account deletion");
        click(continueButton);
        return new HomePage(driver);
    }
}