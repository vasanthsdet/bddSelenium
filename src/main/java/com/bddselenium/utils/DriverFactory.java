package com.bddselenium.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverFactory {
    private static final Logger LOGGER = Logger.getLogger(DriverFactory.class.getName());
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            Config config = Config.getInstance();
            String browser = config.getProperty("browser", "chrome"); // Default to Chrome if not specified
            String version = null;
            if (!Boolean.getBoolean(config.getProperty("remoteRun"))) {
                version = config.getProperty(String.format("%s.browserVersion", browser));
            }
            switch (browser.toLowerCase()) {
                case "chrome":
                    if (version != null) {
                        System.out.println("******************" + version);
                        WebDriverManager.chromedriver().driverVersion(version).setup();
                    } else {
                        WebDriverManager.chromedriver().setup();
                    }
                    driver = new ChromeDriver(getChromeOptions());
                    break;
                case "firefox":
                    if (version != null) {
                        WebDriverManager.firefoxdriver().driverVersion(version).setup();
                    } else {
                        WebDriverManager.firefoxdriver().setup();
                    }
                    driver = new FirefoxDriver();
                    break;
                default:
                    LOGGER.log(Level.SEVERE, "Unsupported browser: " + browser);
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
          options.addArguments("--headless");
          options.addArguments("--disable-gpu");
          options.addArguments("--window-size=1920,1080");
          options.addArguments("--no-sandbox");
          options.addArguments("--remote-allow-origins=*");

        return options;
    }
}
