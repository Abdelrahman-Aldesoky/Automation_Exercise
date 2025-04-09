package org.example.pages.common;

import org.example.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[contains(text(), ' Logged in as')]")
    private WebElement loggedInElement;

    @FindBy(xpath = "//a[@href='/delete_account']")
    private WebElement deleteAccountButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoggedIn() {
        return isElementDisplayed(loggedInElement);
    }

    public void clickDeleteAccount() {
        click(deleteAccountButton);
    }
}