package com.bddselenium.hooks;

import com.bddselenium.utils.Config;
import com.bddselenium.utils.DriverFactory;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Hooks {

    WebDriver driver;

    @Before
    public void setUp() throws InterruptedException {
        try {
            driver = DriverFactory.getDriver();

            // Define global wait time (e.g., 20 seconds)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            driver.get("https://accounts.google.com/");

            // Wait for the page to load completely
            wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
        } catch (Exception e) {
            System.err.println("Error during setup: " + e.getMessage());
            throw e; // rethrow to fail the test early
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            // Capture a screenshot if the scenario fails
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            }

            if (Boolean.parseBoolean(Config.getInstance().getProperty("closeBrowser"))) {
                driver.quit();
            }
        } catch (Exception e) {
            System.err.println("Error during teardown: " + e.getMessage());
        }
    }
}
