package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Click method with explicit wait
    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    // Type text with explicit wait
    protected void type(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element)).clear();
        element.sendKeys(text);
    }

    // Get text from element with wait
    protected String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    // Check if element is displayed with wait
    protected boolean isElementDisplayed(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Select option from dropdown by visible text
    protected void selectByVisibleText(WebElement element, String text) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(element)));
        select.selectByVisibleText(text);
    }

    // Select option from dropdown by value
    protected void selectByValue(WebElement element, String value) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(element)));
        select.selectByValue(value);
    }

    // Select option from dropdown by index
    protected void selectByIndex(WebElement element, int index) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(element)));
        select.selectByIndex(index);
    }

    // Wait for element to be visible
    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for element to be clickable
    protected WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Move to an element to hover over it
    protected void hoverOverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
}