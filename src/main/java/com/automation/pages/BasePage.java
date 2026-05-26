package com.automation.pages;

import com.automation.utils.DriverManager;
import com.automation.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Base class for all Page Objects — initialises PageFactory and exposes
 * shared reusable Selenium actions.
 *
 * @author Banoth Mahesh Kumar
 */
public abstract class BasePage {

    protected WebDriver driver;
    protected WaitUtils wait;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait   = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    protected void click(WebElement element) {
        wait.waitForClickable(element).click();
    }

    protected void type(WebElement element, String text) {
        wait.waitForVisible(element).clear();
        element.sendKeys(text);
    }

    protected String getText(WebElement element) {
        return wait.waitForVisible(element).getText().trim();
    }

    protected boolean isDisplayed(WebElement element) {
        try { return element.isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
