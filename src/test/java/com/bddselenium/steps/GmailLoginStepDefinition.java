package com.bddselenium.steps;

import com.bddselenium.pages.LoginPage;
import com.bddselenium.utils.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class GmailLoginStepDefinition {

    LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        loginPage.login("veda.dama2019", "tet");
    }

    @Then("Validate wrong password error text")
    public void validateWrongPasswordErrorText() {
        Assert.assertTrue(loginPage.validateWrongPasswordMessage());
    }
}
