package com.bddselenium.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageUtils {

    static Config config = Config.getInstance();
    static int elementWaitTimeout = Integer.parseInt(config.getProperty("elementWaitTimeout", "20"));

    // Method to wait for the page to load completely
    public static void waitForPageLoad(WebDriver driver, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(d -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    // Method to wait for a specific WebElement to be visible
    public static WebElement waitForElementVisibility(WebDriver driver, WebElement element) {
        if (driver == null || element == null) {
            throw new IllegalArgumentException("Driver and element must not be null.");
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(elementWaitTimeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}
