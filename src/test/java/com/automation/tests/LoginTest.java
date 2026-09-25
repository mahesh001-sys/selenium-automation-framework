package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.utils.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test class for Login page.
 *
 * Covers:
 * - Valid login
 * - Invalid login
 * - Empty credentials
 * - Locked-out user
 * - Data-driven login scenarios
 *
 * Compatible with Java 11.
 *
 * @author Banoth Mahesh Kumar
 */
public class LoginTest extends BaseTest {

    @Test(
            groups = {"smoke", "regression"},
            description = "Valid credentials should land on Products page"
    )
    public void testValidLogin() {

        HomePage homePage =
                new LoginPage()
                        .loginAs(
                                "standard_user",
                                "secret_sauce"
                        );

        Assert.assertEquals(
                homePage.getPageHeader(),
                "Products",
                "Page header should be 'Products' after login"
        );
    }

    @Test(
            groups = "regression",
            description = "Invalid credentials should show error message"
    )
    public void testInvalidLogin() {

        LoginPage loginPage =
                new LoginPage()
                        .enterUsername("invalid_user")
                        .enterPassword("wrong_pass")
                        .clickLoginExpectingFailure();

        Assert.assertTrue(
                loginPage.isErrorDisplayed(),
                "Error message should be displayed for invalid credentials"
        );

        Assert.assertTrue(
                loginPage.getErrorMessage()
                        .contains("Username and password do not match"),
                "Expected invalid credentials error message"
        );
    }

    @Test(
            groups = "regression",
            description = "Empty credentials should show error message"
    )
    public void testEmptyCredentials() {

        LoginPage loginPage =
                new LoginPage()
                        .enterUsername("")
                        .enterPassword("")
                        .clickLoginExpectingFailure();

        Assert.assertTrue(
                loginPage.isErrorDisplayed(),
                "Error message should be displayed"
        );

        Assert.assertTrue(
                loginPage.getErrorMessage()
                        .contains("Username is required"),
                "Expected username required error message"
        );
    }

    @Test(
            groups = "regression",
            description = "Locked-out user should see locked error"
    )
    public void testLockedOutUser() {

        LoginPage loginPage =
                new LoginPage()
                        .enterUsername("locked_out_user")
                        .enterPassword("secret_sauce")
                        .clickLoginExpectingFailure();

        Assert.assertTrue(
                loginPage.isErrorDisplayed(),
                "Error message should be displayed"
        );

        Assert.assertTrue(
                loginPage.getErrorMessage()
                        .contains(
                                "Sorry, this user has been locked out"
                        ),
                "Expected locked-out user error message"
        );
    }

    @Test(
            groups = "regression",
            dataProvider = "loginData",
            description = "Data-driven login test using Excel"
    )
    public void testLoginDataDriven(
            String username,
            String password,
            String expected) {

        LoginPage loginPage =
                new LoginPage()
                        .enterUsername(username)
                        .enterPassword(password);

        if ("pass".equalsIgnoreCase(expected)) {

            HomePage home =
                    loginPage.clickLogin();

            Assert.assertEquals(
                    home.getPageHeader(),
                    "Products",
                    "Successful login should navigate to Products page"
            );

        } else {

            loginPage.clickLoginExpectingFailure();

            Assert.assertTrue(
                    loginPage.isErrorDisplayed(),
                    "Expected login failure for user: " + username
            );
        }
    }

    /**
     * Reads login test data from Excel.
     *
     * @return login test data
     */
    @DataProvider(name = "loginData")
    public Object[][] loginData() {

        return ExcelUtils.readSheet(
                "test-data/TestData.xlsx",
                "LoginData"
        );
    }
}
