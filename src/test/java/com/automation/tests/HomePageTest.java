package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for Home/Products page.
 *
 * Covers:
 * - Page title validation
 * - Product count validation
 * - Logout functionality
 *
 * Compatible with Java 11.
 *
 * @author Banoth Mahesh Kumar
 */
public class HomePageTest extends BaseTest {

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
            description = "Products page title should be correct"
    )
    public void testPageTitle() {

        HomePage homePage = login();

        Assert.assertTrue(
                homePage.getPageTitle().contains("Swag Labs"),
                "Browser title should contain 'Swag Labs'"
        );
    }

    @Test(
            groups = "regression",
            description = "Products page should display 6 products"
    )
    public void testProductCount() {

        HomePage homePage = login();

        Assert.assertEquals(
                homePage.getProductCount(),
                6,
                "SauceDemo should display exactly 6 products"
        );
    }

    @Test(
            groups = "regression",
            description = "Logout should redirect to login page"
    )
    public void testLogout() {

        LoginPage loginPage =
                login().logout();

        Assert.assertTrue(
                loginPage.getCurrentUrl().contains("saucedemo.com"),
                "After logout, user should be on login page"
        );
    }
}
