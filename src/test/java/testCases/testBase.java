package testCases;

import common.MyScreenRecorder;
import drivers.DriverFactory;
import org.testng.annotations.*;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import static Utils.Utility.openBrowserNetworkTab;
import static drivers.DriverHolder.getDriver;
import static drivers.DriverHolder.setDriver;
import static pages.Page_Base.quitBrowser;

public class testBase {


    @BeforeSuite
    public void beforeSuite() throws Exception {
        MyScreenRecorder.startRecording("SwagLabs-TestCases");
    }


    @Parameters("browsername")
    @BeforeTest
    public void setupDriver(@Optional String browsername) throws AWTException {
        setDriver(DriverFactory.getNewInstance(browsername));
        getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        getDriver().get("https://www.saucedemo.com/v1/index.html");
        openBrowserNetworkTab();
    }

    @AfterTest
    public void tearDown() {
        quitBrowser(getDriver());
    }

    @AfterSuite
    public void afterSuite() throws Exception {
        MyScreenRecorder.stopRecording();
    }
}