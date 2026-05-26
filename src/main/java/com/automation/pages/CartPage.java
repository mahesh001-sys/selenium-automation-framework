package com.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page Object for the SauceDemo Cart page.
 *
 * @author Banoth Mahesh Kumar
 */
public class CartPage extends BasePage {

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(className = "cart_item_label")
    private List<WebElement> itemLabels;

    public int getCartItemCount() {
        return cartItems.size();
    }

    public boolean isCartEmpty() {
        return cartItems.isEmpty();
    }

    public CheckoutPage proceedToCheckout() {
        click(checkoutButton);
        return new CheckoutPage();
    }

    public HomePage continueShopping() {
        click(continueShoppingButton);
        return new HomePage();
    }

    public String getFirstItemName() {
        if (itemLabels.isEmpty()) return "";
        return getText(itemLabels.get(0)
                .findElement(org.openqa.selenium.By.className("inventory_item_name")));
    }
}
