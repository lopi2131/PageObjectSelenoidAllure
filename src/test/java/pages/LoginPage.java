package pages;

import config.ServerConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseHooks;

import java.util.ArrayList;

public class LoginPage extends AbstractPage {
    private Logger logger = LogManager.getLogger(LoginPage.class);
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    private By authButton = By.xpath("//button[@class='header2__auth js-open-modal']");
    private By mail = By.xpath("//*[@type='text' and @name='email']");
    private By password = By.xpath("//*[@type='password']");
    private By submit = By.xpath("//button[@class='new-button new-button_full new-button_blue new-button_md']");
    private By menu = By.xpath("//div[@class='header2-menu__icon-img ic-blog-default-avatar']");
    private By profile = By.xpath("//div[@class='header2-menu__dropdown-text']");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get(cfg.url());
        logger.info("Открыта страница Отус");

        return this;
    }

    public LoginPage login() {
        driver.findElement(authButton).click();
        WebElement email = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.visibilityOfElementLocated(mail));
        email.sendKeys(cfg.email());

        logger.info("Введен email");
        driver.findElement(password).sendKeys(cfg.password());
        logger.info("Введен пароль");
        driver.findElement(submit).click();
        logger.info("Выполнен вход");

        return new LoginPage(driver);
    }

    public ProfilePage switchProfile() {
        WebElement menuElement = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.visibilityOfElementLocated(menu));
        driver.findElement(menu).click();
        driver.findElement(profile).click();
        logger.info("Выполнен переход в Мой профиль");

        return new ProfilePage(driver);
    }

    public LoginPage newBrowser() {
        BaseHooks newBrowser = new BaseHooks();
        newBrowser.cleanUp();

        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        return new LoginPage(driver);
    }

}
