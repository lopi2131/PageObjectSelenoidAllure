package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.AbstractPage;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    private Logger logger = LogManager.getLogger(BaseTest.class);
    protected ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void setUp() throws Exception {
        driver.set(Browsers.valueOf(System.getProperty("browser").toUpperCase()).create());
        logger.info("Драйвер поднят");

        if (driver != null) {
            getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            getDriver().manage().window().maximize();
        }
    }

    public RemoteWebDriver getDriver(){
        return driver.get();
    }

    @AfterMethod
    public void tearDown(Object[] params, ITestResult result) {
        if (!result.isSuccess()) {
            AbstractPage.attachScreenShot(getDriver());
        }
        logger.info("Закрытие драйвера");
        getDriver().quit();
    }

    @AfterClass
    void terminate () {
        //Remove the ThreadLocalMap element
        driver.remove();
    }
}
