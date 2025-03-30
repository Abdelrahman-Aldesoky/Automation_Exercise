package org.example.pages.product;

import org.example.pages.BasePage;
import org.example.pages.checkout.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends BasePage {

    @FindBy(css = "button.btn.btn-default.cart")
    private WebElement addToCartButton;

    @FindBy(xpath = "//div[@class='modal-content']//a[@href='/view_cart']")
    private WebElement viewCartButton;

    @FindBy(xpath = "//button[@class='btn btn-success close-modal btn-block']")
    private WebElement continueShoppingButton;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart() {
        click(addToCartButton);
    }

    public CartPage viewCart() {
        click(viewCartButton);
        return new CartPage(driver);
    }

    public ProductDetailsPage continueShopping() {
        click(continueShoppingButton);
        return this;
    }
}