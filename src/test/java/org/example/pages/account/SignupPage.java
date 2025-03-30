package org.example.pages.account;

import org.example.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignupPage extends BasePage {

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "first_name")
    private WebElement firstNameField;

    @FindBy(id = "last_name")
    private WebElement lastNameField;

    @FindBy(id = "address1")
    private WebElement addressField;

    @FindBy(id = "state")
    private WebElement stateField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "zipcode")
    private WebElement zipCodeField;

    @FindBy(id = "mobile_number")
    private WebElement mobileNumberField;

    @FindBy(xpath = "//input[@value='Mr']")
    private WebElement titleMrRadio;

    @FindBy(xpath = "//input[@value='Mrs']")
    private WebElement titleMrsRadio;

    @FindBy(id = "newsletter")
    private WebElement newsletterCheckbox;

    @FindBy(id = "optin")
    private WebElement specialOffersCheckbox;

    @FindBy(name = "days")
    private WebElement daysDropdown;

    @FindBy(name = "months")
    private WebElement monthsDropdown;

    @FindBy(name = "years")
    private WebElement yearsDropdown;

    @FindBy(id = "country")
    private WebElement countryDropdown;

    @FindBy(xpath = "//button[@data-qa='create-account']")
    private WebElement createAccountButton;

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    public void selectTitle(String title) {
        if (title.equalsIgnoreCase("Mr")) {
            click(titleMrRadio);
        } else if (title.equalsIgnoreCase("Mrs")) {
            click(titleMrsRadio);
        }
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void selectDateOfBirth(String day, String month, String year) {
        selectByValue(daysDropdown, day);
        selectByValue(monthsDropdown, month);
        selectByValue(yearsDropdown, year);
    }

    public void checkNewsletter() {
            click(newsletterCheckbox);
    }

    public void checkSpecialOffers() {
            click(specialOffersCheckbox);
    }

    public void enterAddressInfo(String firstName, String lastName, String address,
                                 String state, String city, String zipCode, String mobileNumber) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(addressField, address);
        type(stateField, state);
        type(cityField, city);
        type(zipCodeField, zipCode);
        type(mobileNumberField, mobileNumber);
    }

    public void selectCountry(String countryName) {
        selectByVisibleText(countryDropdown, countryName);
    }

    public AccountCreatedPage clickCreateAccount() {
        click(createAccountButton);
        return new AccountCreatedPage(driver);
    }
}