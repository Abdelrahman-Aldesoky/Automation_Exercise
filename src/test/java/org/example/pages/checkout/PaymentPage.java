package org.example.pages.checkout;

import org.example.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentPage extends BasePage {

    @FindBy(xpath = "//h2[@class='heading']")
    private WebElement pageHeading;

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnPaymentPage() {
        return isElementDisplayed(pageHeading);
    }
}