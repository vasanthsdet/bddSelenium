package com.bddselenium.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageUtils {

    static Config config = Config.getInstance();
    static int elementWaitTimeout = Integer.parseInt(config.getProperty("elementWaitTimeout"));

    // Method to wait for the page to load completely
    public static void waitForPageLoad(WebDriver driver, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        wait.until(pageLoadCondition);
    }

    // Method to wait for a specific WebElement to be visible
    public static WebElement waitForElementVisibility(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(elementWaitTimeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}
