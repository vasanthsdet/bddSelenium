package com.bddselenium.pages;

import com.bddselenium.utils.PageUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "identifierId")
    WebElement emailField;

    @FindBy(xpath = "//button[contains(., 'Next')]")
    WebElement nextButton;

    @FindBy(xpath = "//input[@name='Passwd']")
    WebElement passwordField;

    @FindBy(xpath = "//div[@aria-live='polite']//span[contains(., 'Wrong password')]")
    WebElement wrongPasswordMessageText;

    public void login(String email, String password) {
        PageUtils.waitForElementVisibility(driver, emailField).sendKeys(email);
        nextButton.click();
        PageUtils.waitForElementVisibility(driver, passwordField).sendKeys(password);
        PageUtils.waitForElementVisibility(driver, nextButton).click();
    }

    public boolean validateWrongPasswordMessage() {
        try {
            Thread.sleep(5000);
            return wrongPasswordMessageText.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
