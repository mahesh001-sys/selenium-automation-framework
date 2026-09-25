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


    public boolean isProductInCart(
            String productName) {

        String productXpath =
                "//div[contains(@class,'inventory_item_name')"
                        + " and normalize-space()='"
                        + productName
                        + "']";

        try {

            return driver.findElement(
                    By.xpath(productXpath)
            ).isDisplayed();

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

        WebElement removeButton =
                driver.findElement(
                        By.xpath(removeButtonXpath)
                );

        click(removeButton);

        return this;
    }


    public CheckoutPage proceedToCheckout() {

        click(checkoutButton);

        return new CheckoutPage();
    }
}
