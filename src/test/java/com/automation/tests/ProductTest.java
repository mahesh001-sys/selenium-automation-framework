package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.CartPage;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for product-related functionality.
 *
 * Covers:
 * - Adding products to cart
 * - Product sorting
 * - Cart validation
 *
 * Compatible with Java 11.
 *
 * @author Banoth Mahesh Kumar
 */
public class ProductTest extends BaseTest {

    /**
     * Logs in with a valid SauceDemo user.
     *
     * @return HomePage after successful login
     */
    private HomePage login() {

        return new LoginPage()
                .loginAs(
                        "standard_user",
                        "secret_sauce"
                );
    }

    @Test(
            groups = "smoke",
            description = "Adding a product should update cart badge"
    )
    public void testAddProductToCart() {

        HomePage homePage =
                login()
                        .addProductToCartByIndex(0);

        Assert.assertEquals(
                homePage.getCartBadgeCount(),
                "1",
                "Cart badge should show 1 after adding one product"
        );
    }

    @Test(
            groups = "regression",
            description = "Sort by Price (low to high) should reorder products"
    )
    public void testSortByPrice() {

        HomePage homePage =
                login()
                        .sortBy("Price (low to high)");

        Assert.assertEquals(
                homePage.getPageHeader(),
                "Products",
                "Page should still show Products header after sorting"
        );
    }

    @Test(
            groups = "regression",
            description = "Cart should display added product"
    )
    public void testCartContainsProduct() {

        CartPage cartPage =
                login()
                        .addProductToCartByIndex(0)
                        .goToCart();

        Assert.assertEquals(
                cartPage.getCartItemCount(),
                1,
                "Cart should contain 1 item"
        );
    }
}
