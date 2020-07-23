package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SignUpPage extends AbstractPage{
    private Logger logger = LogManager.getLogger(SignUpPage.class);

    private By title = By.xpath("//div[@class='shadow-box__title']");
    private By email = By.xpath("//input[@id='email_field']");
    private By userName = By.xpath("//input[@id='nickname_field']");
    private By password = By.xpath("//input[@id='password_field']");
    private By regBtn = By.xpath("//button[@id='registration_button']");
    private By logIn = By.xpath(".//a[contains(text(),'Log in')]");

    @Step("Переход к экрану авторизации")
    public LogInPage moveToLogIn(){
        driver.findElement(logIn).click();
        logger.info("Переход к экрану авторизации");

        return new LogInPage(driver);
    }

    @Step("Проверка заголовка")
    public String checkTitle(){
        logger.info("Проверка заголовка");

        return driver.findElement(title).getText();
    }

    @Step("Проверка, что кнопка неаквтина, если не заполнены все поля")
    public Boolean checkSignUpBtn(){
        driver.findElement(email).sendKeys("test@gmail.com");
        driver.findElement(userName).sendKeys("test");
        driver.findElement(password).sendKeys("test");
        logger.info("Проверка, что кнопка неаквтина, если не заполнены все поля");

        return driver.findElement(regBtn).isEnabled();
    }




    public SignUpPage(RemoteWebDriver driver) {
        super(driver);
    }
}
