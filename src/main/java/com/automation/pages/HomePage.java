package com.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page Object for the SauceDemo Products (Home) page.
 *
 * @author Banoth Mahesh Kumar
 */
public class HomePage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageHeader;

    @FindBy(className = "inventory_item")
    private List<WebElement> productItems;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    public String getPageHeader() {
        return getText(pageHeader);
    }

    public int getProductCount() {
        return productItems.size();
    }

    public CartPage goToCart() {
        click(cartIcon);
        return new CartPage();
    }

    public HomePage addProductToCartByIndex(int index) {
        WebElement addBtn = productItems.get(index)
                .findElement(org.openqa.selenium.By.tagName("button"));
        click(addBtn);
        return this;
    }

    public String getCartBadgeCount() {
        try {
            WebElement badge = driver.findElement(
                    org.openqa.selenium.By.className("shopping_cart_badge"));
            return badge.getText();
        } catch (Exception e) {
            return "0";
        }
    }

    public LoginPage logout() {
        click(menuButton);
        wait.waitForClickable(logoutLink);
        click(logoutLink);
        return new LoginPage();
    }

    public HomePage sortBy(String visibleText) {
        new org.openqa.selenium.support.ui.Select(sortDropdown)
                .selectByVisibleText(visibleText);
        return this;
    }
}
