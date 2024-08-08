package com.bddselenium.steps;

import com.bddselenium.pages.LoginPage;
import com.bddselenium.utils.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class GmailLoginStepDefinition {

    private final LoginPage loginPage;

    public GmailLoginStepDefinition() {
        this.loginPage = new LoginPage(DriverFactory.getDriver());
    }

    @Given("the user logs in with username {string} and password {string}")
    public void theUserLogsInWithUsernameAndPassword(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("Validate wrong password error text")
    public void validateWrongPasswordErrorText() {
        //Assert.assertTrue("The wrong password message was not displayed as expected.", loginPage.validateWrongPasswordMessage());
    }
}
