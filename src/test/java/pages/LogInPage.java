package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LogInPage extends AbstractPage{
    private Logger logger = LogManager.getLogger(LogInPage.class);

    private By title = By.xpath("//div[@class='shadow-box__title']");

    public String checkTitle(){
        logger.info("Првоерка заголовка");

        return driver.findElement(title).getText();
    }

    public LogInPage(RemoteWebDriver driver) {
        super(driver);
    }
}
