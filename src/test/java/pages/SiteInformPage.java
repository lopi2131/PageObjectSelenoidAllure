package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SiteInformPage extends AbstractPage {
    private Logger logger = LogManager.getLogger(MainPage.class);

    private By title = By.xpath(".//span[contains(text(),\"Информация\")]|.//span[contains(text(),'Info')]");
    private By searchButton = By.xpath("//button[@id='search-form-btn']");
    private By searchField = By.xpath("//input[@id='search-form-field']");

    @Step("Проверка заголовка")
    public String checkTitle(){
        logger.info("Проверка заголовка");

        return driver.findElement(title).getText();
    }

    @Step("Поиск записей OTUS'а")
    public SearchPage searchOtusNotes(){
        WebElement searchBtn = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.visibilityOfElementLocated(searchButton));
        searchBtn.click();
        driver.findElement(searchField).sendKeys("otus", Keys.ENTER);
        logger.info("Поиск записей OTUS'а");

        return new SearchPage(driver);
    }

    public SiteInformPage(RemoteWebDriver driver) {
        super(driver);
    }
}
