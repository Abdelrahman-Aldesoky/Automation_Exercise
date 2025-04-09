package org.example.pages.product;

import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage extends BasePage {

    @FindBy(xpath = "//div[@class='modal-content']//a[@href='/view_cart']")
    private WebElement viewCartButton;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToProductDetailsPage(int productId) {
        WebElement productLink = driver.findElement(
                By.xpath("//a[@href='/product_details/" + productId + "']"));
        click(productLink);
    }

    public void addToCartAfterHovering(int productId) {
        // First find the product container
        WebElement productContainer = driver.findElement(
                By.xpath("//div[contains(@class,'productinfo') and .//a[@data-product-id='" + productId + "']]"));

        // Hover over the product to make the overlay visible
        hoverOverElement(productContainer);

        // Then click the Add to Cart button in the overlay
        WebElement productAddToCartButton = driver.findElement(
                By.xpath("//div[@class='product-overlay']//a[@data-product-id='" + productId + "']"));
        click(productAddToCartButton);
    }

    public void addToCartDirectly(int productId) {
        WebElement productAddToCartButton = driver.findElement(
                By.xpath("//div[@class='productinfo text-center']//a[@data-product-id='" + productId + "']"));
        click(productAddToCartButton);
    }

    public void viewCart() {
        click(viewCartButton);
    }
}