package org.example.pages.checkout;

import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    @FindBy(xpath = "//a[@class='btn btn-default check_out']")
    private WebElement proceedToCheckoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage proceedToCheckout() {
        click(proceedToCheckoutButton);
        return new CheckoutPage(driver);
    }

    public boolean isProductInCart(int productId) {
        try {
            // Check if product with specific ID is present in cart
            WebElement productElement = driver.findElement(
                    By.xpath("//a[@href='/product_details/" + productId + "']"));
            return productElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}