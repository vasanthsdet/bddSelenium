package com.bddselenium.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.bddselenium.steps", "com.bddselenium.hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber-reports.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" // Include Allure plugin if using Allure reports
        },
        monochrome = true,
        //  tags = "@SmokeTest", // Optional: use if you want to filter specific tags
        publish = true // Optional: enable this if you want to publish results to Cucumber's report service
)
public class TestRunner {
}
