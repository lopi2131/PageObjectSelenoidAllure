package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends AbstractPage {
    private Logger logger = LogManager.getLogger(LoginPage.class);

    private By name = By.xpath("//input[@id='id_fname']");
    private By surname = By.xpath("//input[@id='id_lname']");
    private By addContact = By.xpath("//button[@class='lk-cv-block__action lk-cv-block__action_md-no-spacing js-formset-add js-lk-cv-custom-select-add' and @type='button']");
    private By firstContactList = By.xpath("//div[@data-num='1']//div[@class='select lk-cv-block__input lk-cv-block__input_3 lk-cv-block__input_md-4 js-lk-cv-custom-select']");
    private By secondContactList = By.xpath("//div[@data-num='2']//div[@class='select lk-cv-block__input lk-cv-block__input_3 lk-cv-block__input_md-4 js-lk-cv-custom-select']");
    private By whatsApp = By.xpath("//div[@data-num='1']//button[@data-value='whatsapp']");
    private By faceBook = By.xpath("//div[@data-num='2']//button[@data-value='facebook']");
    private By wpDescription = By.xpath("//input[@id='id_contact-1-value']");
    private By saveButton = By.xpath("//button[@title='Сохранить и заполнить позже']");
    private By fbDescription = By.xpath("//input[@id='id_contact-2-value']");
    private By checkFaceBook = By.xpath("//input[@id='id_contact-0-value']");
    private By checkWhatsApp = By.xpath("//input[@id='id_contact-2-value']");

    Actions builder = new Actions(driver);

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public ProfilePage addInform(String firstName, String secondName, String firstContact, String secondContact) {
        driver.findElement(name).clear();
        driver.findElement(name).sendKeys(firstName);
        logger.info("Заполнено поле Имя");
        driver.findElement(surname).clear();
        driver.findElement(surname).sendKeys(secondName);
        logger.info("Заполнено поле Фамилия");

        WebElement firstContactButton = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.visibilityOfElementLocated(addContact));
        firstContactButton.click();

        driver.findElement(firstContactList).click();
        WebElement wapp = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(whatsApp));
        wapp.click();
        driver.findElement(wpDescription).sendKeys(firstContact);
        logger.info("Добавлен контакт WhatsApp");

        WebElement secondContactButton = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.visibilityOfElementLocated(addContact));
        secondContactButton.click();

        driver.findElement(secondContactList).click();
        WebElement fb = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(faceBook));
        fb.click();
        driver.findElement(fbDescription).sendKeys(secondContact);
        logger.info("Добавлен контакт Facebook");

        driver.findElement(saveButton).click();
        logger.info("Изменения сохранены");

        return new ProfilePage(driver);
    }

    public String getName() {
        return driver.findElement(name).getAttribute("value");
    }

    public String getSurname() {
        return driver.findElement(surname).getAttribute("value");
    }

    public String getCheckFaceBook() {
        return driver.findElement(checkFaceBook).getAttribute("value");
    }

    public String getCheckWhatsApp() {
        return driver.findElement(checkWhatsApp).getAttribute("value");
    }
}
