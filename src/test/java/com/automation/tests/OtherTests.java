package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.CartPage;
import com.automation.pages.CheckoutPage;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

// ─────────────────────────────────────────────────────
//  HomePageTest
// ─────────────────────────────────────────────────────
class HomePageTest extends BaseTest {

    private HomePage login() {
        return new LoginPage().loginAs("standard_user", "secret_sauce");
    }

    @Test(groups = "smoke",
          description = "Products page title should be correct")
    public void testPageTitle() {
        login();
        Assert.assertTrue(
                new LoginPage().getPageTitle().contains("Swag Labs"),
                "Browser title should contain 'Swag Labs'");
    }

    @Test(groups = "regression",
          description = "Products page should display 6 products")
    public void testProductCount() {
        HomePage home = login();
        Assert.assertEquals(home.getProductCount(), 6,
                "SauceDemo should display exactly 6 products");
    }

    @Test(groups = "regression",
          description = "Logout should redirect to login page")
    public void testLogout() {
        LoginPage loginPage = login().logout();
        Assert.assertTrue(loginPage.getCurrentUrl().contains("saucedemo.com"),
                "After logout, user should be on login page");
    }
}

// ─────────────────────────────────────────────────────
//  ProductTest
// ─────────────────────────────────────────────────────
class ProductTest extends BaseTest {

    private HomePage login() {
        return new LoginPage().loginAs("standard_user", "secret_sauce");
    }

    @Test(groups = "smoke",
          description = "Adding a product should update cart badge")
    public void testAddProductToCart() {
        HomePage home = login().addProductToCartByIndex(0);
        Assert.assertEquals(home.getCartBadgeCount(), "1",
                "Cart badge should show 1 after adding one product");
    }

    @Test(groups = "regression",
          description = "Sort by Price (low to high) should reorder products")
    public void testSortByPrice() {
        HomePage home = login().sortBy("Price (low to high)");
        Assert.assertEquals(home.getPageHeader(), "Products",
                "Page should still show Products header after sorting");
    }

    @Test(groups = "regression",
          description = "Cart should display added product")
    public void testCartContainsProduct() {
        CartPage cart = login()
                .addProductToCartByIndex(0)
                .goToCart();
        Assert.assertEquals(cart.getCartItemCount(), 1,
                "Cart should contain 1 item");
    }
}

// ─────────────────────────────────────────────────────
//  CheckoutTest
// ─────────────────────────────────────────────────────
class CheckoutTest extends BaseTest {

    private CartPage loginAndAddToCart() {
        return new LoginPage()
                .loginAs("standard_user", "secret_sauce")
                .addProductToCartByIndex(0)
                .goToCart();
    }

    @Test(groups = "smoke",
          description = "End-to-end checkout should show order confirmation")
    public void testFullCheckout() {
        CheckoutPage checkout = loginAndAddToCart()
                .proceedToCheckout()
                .fillDetails("Mahesh", "Kumar", "500001")
                .clickFinish();

        Assert.assertTrue(checkout.isOrderComplete(),
                "Order confirmation header should be displayed");
        Assert.assertEquals(checkout.getConfirmationMessage(),
                "Thank you for your order!");
    }

    @Test(groups = "regression",
          description = "Checkout with empty fields should show validation error")
    public void testCheckoutEmptyFields() {
        CheckoutPage checkout = loginAndAddToCart()
                .proceedToCheckout()
                .clickContinue();

        Assert.assertTrue(checkout.getErrorMessage().contains("First Name is required"),
                "Validation error should appear for empty first name");
    }
}
