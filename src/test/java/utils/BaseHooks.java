package utils;

import cases.SampleTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class BaseHooks {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(SampleTest.class);

    @BeforeClass
    public  void setUp(){
        driver = Browsers.valueOf(System.getProperty("browser").toUpperCase()).create();
        logger.info("Драйвер поднят");

        if (driver != null){
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
    }

    @AfterClass
    public static void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterMethod
    public void cleanUp() { driver.manage().deleteAllCookies(); }
}
