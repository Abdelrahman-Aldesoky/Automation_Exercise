package org.example.pages.product;

import org.example.pages.BasePage;
import org.example.pages.checkout.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage extends BasePage {

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public ProductDetailsPage navigateToProductDetailsPage(int productId) {
        WebElement productLink = waitForClickability(
                By.xpath("//a[@href='/product_details/" + productId + "']"));
        click(productLink);
        return new ProductDetailsPage(driver);
    }

    public void addToCartAfterHovering(int productId) {
        // First find the product container
        WebElement productContainer = driver.findElement(
                By.xpath("//div[contains(@class,'productinfo') and .//a[@data-product-id='" + productId + "']]"));

        // Hover over the product to make the overlay visible
        hoverOverElement(productContainer);

        // Then click the Add to Cart button in the overlay
        WebElement productAddToCartButton = waitForClickability(
                By.xpath("//div[@class='product-overlay']//a[@data-product-id='" + productId + "']"));
        click(productAddToCartButton);
    }

    public void addToCartDirectly(int productId) {
        WebElement productAddToCartButton = waitForClickability(
                By.xpath("//div[@class='productinfo text-center']//a[@data-product-id='" + productId + "']"));
        click(productAddToCartButton);
    }

    public CartPage viewCart() {
        WebElement viewCartButton = waitForClickability(
                By.xpath("//div[@class='modal-content']//a[@href='/view_cart']"));
        click(viewCartButton);
        return new CartPage(driver);
    }
}