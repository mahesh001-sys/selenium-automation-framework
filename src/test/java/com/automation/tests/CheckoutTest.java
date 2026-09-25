package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.CartPage;
import com.automation.pages.CheckoutPage;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test cases for SauceDemo checkout functionality.
 *
 * Covers completing a complete purchase flow.
 *
 * @author Banoth Mahesh Kumar
 */
public class CheckoutTest extends BaseTest {

    @Test(
            groups = {"smoke", "regression"},
            description = "User should be able to complete checkout successfully"
    )
    public void testCompleteCheckout() {

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
                "Product should be present in the cart"
        );

        CheckoutPage checkoutPage =
                cartPage.proceedToCheckout();

        checkoutPage.completeCheckout(
                "Mahesh",
                "Kumar",
                "500001"
        );

        Assert.assertEquals(
                checkoutPage.getConfirmationMessage(),
                "Thank you for your order!",
                "Order confirmation message should be displayed"
        );
    }
}
