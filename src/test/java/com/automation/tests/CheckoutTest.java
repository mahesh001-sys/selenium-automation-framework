package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.CartPage;
import com.automation.pages.CheckoutPage;
import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for checkout functionality.
 *
 * Covers:
 * - Complete end-to-end checkout
 * - Checkout validation
 *
 * Compatible with Java 11.
 *
 * @author Banoth Mahesh Kumar
 */
public class CheckoutTest extends BaseTest {

    /**
     * Logs in and adds a product to the cart.
     *
     * @return CartPage containing the selected product
     */
    private CartPage loginAndAddToCart() {

        return new LoginPage()
                .loginAs(
                        "standard_user",
                        "secret_sauce"
                )
                .addProductToCartByIndex(0)
                .goToCart();
    }

    @Test(
            groups = "smoke",
            description = "End-to-end checkout should show order confirmation"
    )
    public void testFullCheckout() {

        CheckoutPage checkoutPage =
                loginAndAddToCart()
                        .proceedToCheckout()
                        .fillDetails(
                                "Mahesh",
                                "Kumar",
                                "500001"
                        )
                        .clickFinish();

        Assert.assertTrue(
                checkoutPage.isOrderComplete(),
                "Order confirmation header should be displayed"
        );

        Assert.assertEquals(
                checkoutPage.getConfirmationMessage(),
                "Thank you for your order!",
                "Order confirmation message should be displayed"
        );
    }

    @Test(
            groups = "regression",
            description = "Checkout with empty fields should show validation error"
    )
    public void testCheckoutEmptyFields() {

        CheckoutPage checkoutPage =
                loginAndAddToCart()
                        .proceedToCheckout()
                        .clickContinue();

        Assert.assertTrue(
                checkoutPage.getErrorMessage()
                        .contains("First Name is required"),
                "Validation error should appear for empty first name"
        );
    }
}
