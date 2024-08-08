package com.bddselenium.pages;

import com.bddselenium.utils.PageUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.NoSuchElementException;

public class LoginPage {

    private final WebDriver driver;

    @FindBy(id = "identifierId")
    private WebElement emailField;

    @FindBy(xpath = "//button[contains(., 'Next')]")
    private WebElement nextButton;

    @FindBy(xpath = "//input[@name='Passwd']")
    private WebElement passwordField;

    @FindBy(xpath = "//div[@aria-live='polite']//span[contains(., 'Wrong password')]")
    private WebElement wrongPasswordMessageText;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String email, String password) {
        PageUtils.waitForElementVisibility(driver, emailField).sendKeys(email);
        nextButton.click();
        PageUtils.waitForElementVisibility(driver, passwordField).sendKeys(password);
        PageUtils.waitForElementVisibility(driver, nextButton).click();
    }

    public boolean validateWrongPasswordMessage() {
        try {
            return PageUtils.waitForElementVisibility(driver, wrongPasswordMessageText).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
