package org.example.pages.common;

import org.example.pages.BasePage;
import org.example.pages.account.AccountDeletedPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);

    @FindBy(xpath = "//a[contains(text(), ' Logged in as')]")
    private WebElement loggedInElement;

    @FindBy(xpath = "//a[@href='/delete_account']")
    private WebElement deleteAccountButton;

    public HomePage(WebDriver driver) {
        super(driver);
        logger.info("Home Page initialized");
    }

    public boolean isLoggedIn() {
        boolean loggedIn = isElementDisplayed(loggedInElement);
        logger.info("User logged in status: {}", loggedIn);
        return loggedIn;
    }

    public AccountDeletedPage clickDeleteAccount() {
        logger.info("Clicking Delete Account button");
        click(deleteAccountButton);
        return new AccountDeletedPage(driver);
    }
}