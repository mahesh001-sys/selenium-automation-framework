package com.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for SauceDemo Checkout Information page.
 *
 * Java 11 compatible.
 *
 * @author Banoth Mahesh Kumar
 */
public class CheckoutPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageHeader;

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public String getPageHeader() {
        return getText(pageHeader);
    }

    private void enterCheckoutField(
            WebElement field,
            String value) {

        WebElement visibleField =
                wait.waitForVisible(field);

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "const element = arguments[0];" +
                        "const value = arguments[1];" +
                        "const setter = Object.getOwnPropertyDescriptor(" +
                        "window.HTMLInputElement.prototype, 'value').set;" +
                        "setter.call(element, value);" +
                        "element.dispatchEvent(new Event('input', { bubbles: true }));" +
                        "element.dispatchEvent(new Event('change', { bubbles: true }));",
                        visibleField,
                        value
                );
    }

    public CheckoutPage enterFirstName(String firstName) {
        enterCheckoutField(firstNameField, firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        enterCheckoutField(lastNameField, lastName);
        return this;
    }

    public CheckoutPage enterPostalCode(String postalCode) {
        enterCheckoutField(postalCodeField, postalCode);
        return this;
    }

    public CheckoutOverviewPage clickContinue() {

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

        return new CheckoutOverviewPage();
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}
