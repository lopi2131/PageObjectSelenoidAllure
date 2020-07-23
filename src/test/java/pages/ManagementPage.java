package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ManagementPage extends AbstractPage {
    private Logger logger = LogManager.getLogger(ManagementPage.class);

    private By articles = By.xpath(".//h3[contains(text(),'Articles')]");
    private By signUpBtn = By.xpath("//a[@class='btn btn_medium btn_navbar_registration']");

    @Step("Переход на страницу регистрации")
    public SignUpPage moveToReg(){
        driver.findElement(signUpBtn).click();
        logger.info("Переход на страницу регистрации");

        return new SignUpPage(driver);
    }

    @Step("Проверка языка вкладки")
    public String checkTabLang(){
        logger.info("Проверка языка вкладки");

        return driver.findElement(articles).getText().toLowerCase();
    }


    public ManagementPage(RemoteWebDriver driver) {
        super(driver);
    }
}
