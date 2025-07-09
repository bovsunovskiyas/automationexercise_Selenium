package com.automationexercise.pages;

import com.automationexercise.components.MainMenu;
import com.automationexercise.helpers.Waiter;
import com.automationexercise.models.Gender;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SignupEnterAccountInfoPage extends BasePage{

    @Getter
    private final MainMenu mainMenu = new MainMenu();
    private final Waiter waiter = new Waiter(getDriver());

    // --- Locators ---
    private final By accountCreatedHeaderLocator = By.cssSelector("h2[data-qa='account-created']");
    private final By continueButtonLocator = By.cssSelector("a[data-qa='continue-button']");

    private final By enterAccountInfoHeaderLocator = By.xpath("//b[text()='Enter Account Information']");
    private final By titleMrRadioButtonLocator = By.id("id_gender1");
    private final By titleMrsRadioButtonLocator = By.id("id_gender2");
    private final By passwordInputLocator = By.cssSelector("input[data-qa='password']");
    private final By daysDropdownLocator = By.cssSelector("select[data-qa='days']");
    private final By monthsDropdownLocator = By.cssSelector("select[data-qa='months']");
    private final By yearsDropdownLocator = By.cssSelector("select[data-qa='years']");
    private final By newsletterCheckboxLocator = By.id("newsletter");
    private final By specialOffersCheckboxLocator = By.id("optin");
    private final By firstNameInputLocator = By.cssSelector("input[data-qa='first_name']");
    private final By lastNameInputLocator = By.cssSelector("input[data-qa='last_name']");
    private final By companyInputLocator = By.cssSelector("input[data-qa='company']");
    private final By address1InputLocator = By.cssSelector("input[data-qa='address']");
    private final By address2InputLocator = By.cssSelector("input[data-qa='address2']");
    private final By countryDropdownLocator = By.cssSelector("select[data-qa='country']");
    private final By stateInputLocator = By.cssSelector("input[data-qa='state']");
    private final By cityInputLocator = By.cssSelector("input[data-qa='city']");
    private final By zipcodeInputLocator = By.cssSelector("input[data-qa='zipcode']");
    private final By mobileNumberInputLocator = By.cssSelector("input[data-qa='mobile_number']");
    private final By createAccountButtonLocator = By.cssSelector("button[data-qa='create-account']");


    public SignupEnterAccountInfoPage assertEnterAccountInfoPageSuccessfullyLoaded(){
        waiter.waitUntilVisibleOfElementLocated(enterAccountInfoHeaderLocator);
        return this;
    }

    public boolean isEnterAccountInfoVisible() {
        return waiter.waitUntilVisibleOfElementLocated(enterAccountInfoHeaderLocator).isDisplayed();
    }

    public SignupEnterAccountInfoPage clickTitleRadioButton(Gender gender){
        if(gender.equals(Gender.MR)){
            waiter.waitUntilVisibleOfElementLocated(titleMrRadioButtonLocator).click();
        }
        else {
            waiter.waitUntilVisibleOfElementLocated(titleMrsRadioButtonLocator).click();
        }
        return this;
    }

    public SignupEnterAccountInfoPage fillPassword(String password){
        waiter.waitUntilVisibleOfElementLocated(passwordInputLocator).sendKeys(password);
        return this;
    }

    public SignupEnterAccountInfoPage selectDay(String day){
        waiter.waitUntilVisibleOfElementLocated(daysDropdownLocator);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", waiter.waitUntilVisibleOfElementLocated(daysDropdownLocator));

        new Select(waiter.waitUntilVisibleOfElementLocated(daysDropdownLocator)).selectByValue(day);
        return this;
    }

    public SignupEnterAccountInfoPage selectMonth(String month) {
        new Select(waiter.waitUntilVisibleOfElementLocated(monthsDropdownLocator)).selectByVisibleText(month);
        return this;
    }

    public SignupEnterAccountInfoPage selectYear(String year) {
        new Select(waiter.waitUntilVisibleOfElementLocated(yearsDropdownLocator)).selectByValue(year);
        return this;
    }

    public SignupEnterAccountInfoPage clickNewsletterCheckbox() {
        waiter.waitUntilClickable(newsletterCheckboxLocator).click();
        return this;
    }

    public SignupEnterAccountInfoPage clickSpecialOffersCheckbox() {
        waiter.waitUntilClickable(specialOffersCheckboxLocator).click();
        return this;
    }

    public SignupEnterAccountInfoPage fillFirstName(String firstName){
        waiter.waitUntilVisibleOfElementLocated(firstNameInputLocator).sendKeys(firstName);
        return this;
    }

    public SignupEnterAccountInfoPage fillLastName(String lastName){
        waiter.waitUntilVisibleOfElementLocated(lastNameInputLocator).sendKeys(lastName);
        return this;
    }

    public SignupEnterAccountInfoPage fillCompany(String company){
        waiter.waitUntilVisibleOfElementLocated(companyInputLocator).sendKeys(company);
        return this;
    }

    public SignupEnterAccountInfoPage fillAddress1(String address1){
        waiter.waitUntilVisibleOfElementLocated(address1InputLocator).sendKeys(address1);
        return this;
    }

    public SignupEnterAccountInfoPage fillAddress2(String address2){
        waiter.waitUntilVisibleOfElementLocated(address2InputLocator).sendKeys(address2);
        return this;
    }

    public SignupEnterAccountInfoPage selectCountry(String country) {
        new Select(waiter.waitUntilVisibleOfElementLocated(countryDropdownLocator)).selectByValue(country);
        return this;
    }

    public SignupEnterAccountInfoPage fillState(String state){
        waiter.waitUntilVisibleOfElementLocated(stateInputLocator).sendKeys(state);
        return this;
    }

    public SignupEnterAccountInfoPage fillCity(String city){
        waiter.waitUntilVisibleOfElementLocated(cityInputLocator).sendKeys(city);
        return this;
    }

    public SignupEnterAccountInfoPage fillZipcode(String zipcode){
        waiter.waitUntilVisibleOfElementLocated(zipcodeInputLocator).sendKeys(zipcode);
        return this;
    }

    public SignupEnterAccountInfoPage fillMobileNumber(String mobileNumber){
        waiter.waitUntilVisibleOfElementLocated(mobileNumberInputLocator).sendKeys(mobileNumber);
        return this;
    }

    public SignupEnterAccountInfoPage fillAccountInformation(String password, String day, String month, String year) {
        waiter.waitUntilClickable(titleMrRadioButtonLocator).click();
        getDriver().findElement(passwordInputLocator).sendKeys(password);

        WebElement dayElement = waiter.waitUntilVisibleOfElementLocated(daysDropdownLocator);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dayElement);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", dayElement);

        new Select(dayElement).selectByValue(day);
//        new Select(driver.findElement(monthsDropdownLocator)).selectByVisibleText(month);
//        new Select(driver.findElement(yearsDropdownLocator)).selectByValue(year);
        return this;
    }

    public AccountCreatedPage clickCreateAccountButton() {
        WebElement button = waiter.waitUntilClickable(createAccountButtonLocator);

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", button);
        button.click();
        return new AccountCreatedPage();
    }

    public String getAccountCreatedText() {
        return waiter.waitUntilVisibleOfElementLocated(accountCreatedHeaderLocator).getText();
    }

    public HomePage clickContinueButton() {
        waiter.waitUntilClickable(continueButtonLocator).click();
        return new HomePage();
    }
}

