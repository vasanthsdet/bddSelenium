package com.bddselenium.hooks;

import com.bddselenium.utils.DriverFactory;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;

public class Hooks {

    WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get("https://mail.google.com/");
    }

    @After
    public void tearDown() {
       driver.quit();
    }
}
