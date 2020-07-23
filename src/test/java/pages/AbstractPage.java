package pages;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;


public abstract class AbstractPage {

    protected RemoteWebDriver driver;

    public AbstractPage(RemoteWebDriver driver) { this.driver = driver; }

    @Attachment
    protected byte[] attachScreenShot(){ return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);}

    @Attachment
    public static byte[] attachScreenShot(RemoteWebDriver driver) { return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);}
}
