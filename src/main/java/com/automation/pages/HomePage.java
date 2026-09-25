package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for the SauceDemo Products page.
 *
 * Java 11 compatible.
 *
 * @author Banoth Mahesh Kumar
 */
public class HomePage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageHeader;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    public String getPageHeader() {

        return getText(pageHeader);
    }

    public HomePage openMenu() {

        click(menuButton);

        return this;
    }

    public LoginPage logout() {

        openMenu();

        click(logoutLink);

        return new LoginPage();
    }

    public CartPage openCart() {

        wait.waitForClickable(cartLink).click();

        wait.waitForUrl("cart.html");

        return new CartPage();
    }

    public HomePage addProductToCart(
            String productName) {

        String productXpath =
                "//div[contains(@class,'inventory_item')]"
                        + "[.//div[contains(@class,"
                        + "'inventory_item_name') and "
                        + "normalize-space()='"
                        + productName
                        + "']]"
                        + "//button[contains(@id,'add-to-cart')]";

        WebElement addToCartButton =
                wait.waitForPresence(
                        By.xpath(productXpath)
                );

        wait.waitForClickable(addToCartButton).click();

        return this;
    }

    public boolean isProductDisplayed(
            String productName) {

        String productXpath =
                "//div[contains(@class,'inventory_item_name')"
                        + " and normalize-space()='"
                        + productName
                        + "']";

        try {

            WebElement product =
                    wait.waitForPresence(
                            By.xpath(productXpath)
                    );

            return product.isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }
}
