package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test cases for SauceDemo Home/Products page.
 *
 * @author Banoth Mahesh Kumar
 */
public class HomePageTest extends BaseTest {

    @Test(
            groups = {"smoke", "regression"},
            description = "Products page should be displayed after successful login"
    )
    public void testProductsPageDisplayed() {

        HomePage homePage =
                new LoginPage()
                        .loginAs(
                                "standard_user",
                                "secret_sauce"
                        );

        Assert.assertEquals(
                homePage.getPageHeader(),
                "Products",
                "Products page should be displayed"
        );
    }


    @Test(
            groups = "regression",
            description = "Product should be displayed on Products page"
    )
    public void testProductDisplayed() {

        HomePage homePage =
                new LoginPage()
                        .loginAs(
                                "standard_user",
                                "secret_sauce"
                        );

        Assert.assertTrue(
                homePage.isProductDisplayed(
                        "Sauce Labs Backpack"
                ),
                "Sauce Labs Backpack should be displayed"
        );
    }


    @Test(
            groups = "regression",
            description = "User should be able to logout successfully"
    )
    public void testLogout() {

        HomePage homePage =
                new LoginPage()
                        .loginAs(
                                "standard_user",
                                "secret_sauce"
                        );

        LoginPage loginPage =
                homePage.logout();

        Assert.assertTrue(
                loginPage.getCurrentUrl()
                        .contains("saucedemo.com"),
                "User should be returned to the login page"
        );
    }
}
