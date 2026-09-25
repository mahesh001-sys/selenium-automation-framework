package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.CartPage;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test cases for SauceDemo product functionality.
 *
 * Covers adding products to the shopping cart.
 *
 * @author Banoth Mahesh Kumar
 */
public class ProductTest extends BaseTest {

    @Test(
            groups = {"smoke", "regression"},
            description = "User should be able to add a product to the cart"
    )
    public void testAddProductToCart() {

        HomePage homePage =
                new LoginPage()
                        .loginAs(
                                "standard_user",
                                "secret_sauce"
                        );

        homePage.addProductToCart(
                "Sauce Labs Backpack"
        );

        CartPage cartPage =
                homePage.openCart();

        Assert.assertEquals(
                cartPage.getPageHeader(),
                "Your Cart",
                "Cart page should be displayed"
        );

        Assert.assertTrue(
                cartPage.isProductInCart(
                        "Sauce Labs Backpack"
                ),
                "Sauce Labs Backpack should be present in the cart"
        );
    }


    @Test(
            groups = "regression",
            description = "User should be able to remove a product from the cart"
    )
    public void testRemoveProductFromCart() {

        HomePage homePage =
                new LoginPage()
                        .loginAs(
                                "standard_user",
                                "secret_sauce"
                        );

        homePage.addProductToCart(
                "Sauce Labs Backpack"
        );

        CartPage cartPage =
                homePage.openCart();

        Assert.assertTrue(
                cartPage.isProductInCart(
                        "Sauce Labs Backpack"
                ),
                "Product should be present before removal"
        );

        cartPage.removeProduct(
                "Sauce Labs Backpack"
        );

        Assert.assertFalse(
                cartPage.isProductInCart(
                        "Sauce Labs Backpack"
                ),
                "Product should not be present after removal"
        );
    }
}
