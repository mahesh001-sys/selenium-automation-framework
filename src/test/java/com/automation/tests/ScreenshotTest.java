package com.automation.tests;

import com.automation.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ScreenshotTest extends BaseTest {

    @Test
    public void testScreenshotOnFailure() {

        System.out.println("===== Starting screenshot failure test =====");

        Assert.fail("Temporary failure to test screenshot capture");

    }
}
