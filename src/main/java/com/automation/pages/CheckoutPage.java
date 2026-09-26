package com.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for the SauceDemo Checkout flow.
 *
 * Java 11 compatible.
 *
 * @author Banoth Mahesh Kumar
 */
public class CheckoutPage extends BasePage {

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(className = "complete-header")
    private WebElement confirmationMessage;


    public CheckoutPage enterFirstName(
            String firstName) {

        type(firstNameField, firstName);

        return this;
    }


    public CheckoutPage enterLastName(
            String lastName) {

        type(lastNameField, lastName);

        return this;
    }


    public CheckoutPage enterPostalCode(
            String postalCode) {

        type(postalCodeField, postalCode);

        return this;
    }


    public CheckoutPage continueCheckout() {

        WebElement button =
                wait.waitForClickable(continueButton);

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        button
                );

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        button
                );

        wait.waitForUrl("checkout-step-two.html");

        return this;
    }


    public CheckoutPage finishCheckout() {

        WebElement button =
                wait.waitForClickable(finishButton);

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        button
                );

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        button
                );

        wait.waitForUrl("checkout-complete.html");

        return this;
    }


    public CheckoutPage completeCheckout(
            String firstName,
            String lastName,
            String postalCode) {

        return enterFirstName(firstName)
                .enterLastName(lastName)
                .enterPostalCode(postalCode)
                .continueCheckout()
                .finishCheckout();
    }


    public String getConfirmationMessage() {

        return getText(confirmationMessage);
    }
}
