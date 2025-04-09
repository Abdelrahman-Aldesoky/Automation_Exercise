package org.example.pages.common;

import org.example.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Navigation extends BasePage {

    @FindBy(xpath = "//a[@href='/']")
    private WebElement homeLink;

    @FindBy(xpath = "//a[@href='/products']")
    private WebElement productsLink;

    @FindBy(xpath = "//a[@href='/view_cart']")
    private WebElement cartLink;

    @FindBy(xpath = "//a[@href='/login']")
    private WebElement loginLink;

    @FindBy(xpath = "//a[@href='/test_cases']")
    private WebElement testCasesLink;

    @FindBy(xpath = "//a[@href='/api_list']")
    private WebElement apiTestingLink;

    @FindBy(xpath = "//a[@href='https://www.youtube.com/c/AutomationExercise']")
    private WebElement videoTutorialsLink;

    @FindBy(xpath = "//a[@href='/contact_us']")
    private WebElement contactUsLink;

    public Navigation(WebDriver driver) {
        super(driver);
    }

    public void navigateToHome() {
        click(homeLink);
    }

    public void navigateToProducts() {
        click(productsLink);
    }

    public void navigateToCart() {
        click(cartLink);
    }

    public void navigateToLoginSignup() {
        click(loginLink);
    }

    public void navigateToTestCases() {
        click(testCasesLink);
    }

    public void navigateToApiTesting() {
        click(apiTestingLink);
    }

    public void navigateToVideoTutorials() {
        click(videoTutorialsLink);
    }

    public void navigateToContactUs() {
        click(contactUsLink);
    }
}