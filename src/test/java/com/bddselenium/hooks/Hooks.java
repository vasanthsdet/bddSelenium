package com.bddselenium.hooks;

import com.bddselenium.utils.DriverFactory;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Hooks {

    WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();

        // Define global wait time (e.g., 20 seconds)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://accounts.google.com/");

        // Wait for the page to load completely
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
    }

    @After
    public void tearDown() {
      // driver.quit();
    }
}
