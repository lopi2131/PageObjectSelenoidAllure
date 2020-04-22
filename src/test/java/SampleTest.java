import config.ServerConfig;
import factory.Browsers;
import listeners.ExecutionListener;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


@Listeners(ExecutionListener.class)
public class SampleTest {

    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(SampleTest.class);
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);


    @BeforeClass
    public void setUp() {
        driver = Browsers.valueOf(System.getProperty("browser").toUpperCase()).create();
        logger.info("Драйвер поднят");
    }

    @Test
    public void openPage() {
        driver.get(cfg.url());
        logger.info("Открыта страница отус");
        Assert.assertEquals(cfg.title(), driver.getTitle());
    }

    @AfterClass
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
