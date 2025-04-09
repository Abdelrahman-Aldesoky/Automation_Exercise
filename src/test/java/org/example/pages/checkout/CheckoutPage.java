package org.example.pages.checkout;

import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(xpath = "//a[@href='/payment']")
    private WebElement placeOrderButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void placeOrder() {
        click(placeOrderButton);
    }

    public boolean isProductInCheckout(int productId) {
        try {
            // Check if product with specific ID is present in checkout
            WebElement productElement = driver.findElement(
                    By.xpath("//a[@href='/product_details/" + productId + "']"));
            return productElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}