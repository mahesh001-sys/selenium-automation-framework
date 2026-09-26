package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for the SauceDemo Cart page.
 *
 * Java 11 compatible.
 *
 * @author Banoth Mahesh Kumar
 */
public class CartPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageHeader;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public String getPageHeader() {
        return getText(pageHeader);
    }

    private By productLocator(String productName) {

        String productXpath =
                "//div[contains(@class,'cart_item')]"
                        + "[.//div[contains(@class,"
                        + "'inventory_item_name') and "
                        + "normalize-space()='"
                        + productName
                        + "']]";

        return By.xpath(productXpath);
    }

    public boolean isProductInCart(
            String productName) {

        try {

            return !driver.findElements(
                    productLocator(productName)
            ).isEmpty();

        } catch (Exception e) {

            return false;
        }
    }

    public CartPage removeProduct(
            String productName) {

        String removeButtonXpath =
                "//div[contains(@class,'cart_item')]"
                        + "[.//div[contains(@class,"
                        + "'inventory_item_name') and "
                        + "normalize-space()='"
                        + productName
                        + "']]"
                        + "//button[contains(@id,'remove')]";

        By removeButtonLocator =
                By.xpath(removeButtonXpath);

        WebElement removeButton =
                wait.waitForClickable(
                        wait.waitForPresence(
                                removeButtonLocator
                        )
                );

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        removeButton
                );

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        removeButton
                );

        waitForProductRemoval(productLocator(productName));

        return this;
    }

    private void waitForProductRemoval(By locator) {

        long endTime =
                System.currentTimeMillis() + 10000;

        while (System.currentTimeMillis() < endTime) {

            if (driver.findElements(locator).isEmpty()) {
                return;
            }

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        throw new RuntimeException(
                "Product was not removed from cart: "
                        + locator
        );
    }

    public CheckoutPage proceedToCheckout() {

        WebElement button =
                wait.waitForClickable(checkoutButton);

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

        wait.waitForUrl("checkout-step-one.html");

        return new CheckoutPage();
    }
}
