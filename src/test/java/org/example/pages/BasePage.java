package org.example.pages;

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

    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void type(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element)).clear();
        element.sendKeys(text);
    }

    protected String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void selectByVisibleText(WebElement element, String text) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(element)));
        select.selectByVisibleText(text);
    }

    protected void selectByValue(WebElement element, String value) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(element)));
        select.selectByValue(value);
    }

    protected void selectByIndex(WebElement element, int index) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(element)));
        select.selectByIndex(index);
    }

    protected WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForClickability(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void hoverOverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
}