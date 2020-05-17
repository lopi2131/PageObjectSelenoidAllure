import config.ServerConfig;
import factory.Browsers;
import listeners.ExecutionListener;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.concurrent.TimeUnit;


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
    public void openPage()  {
        driver.get(cfg.url());
        logger.info("Открыта страница Яндекс.Маркет");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Cookie cookies = new Cookie.Builder("Otus", cfg.cookie()).domain("market.yandex.ru").build();
        driver.manage().addCookie(cookies);

        Actions builder = new Actions(driver);

        driver.manage().window().fullscreen();
        WebElement electron = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Электроника']")));
        electron.click();

        logger.info("Открыта страница Электроника");

        WebElement smart = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(@class,'_2qvOOvezty _2x2zBaVN-3')])[2]")));
        smart.click();
        logger.info("Открыта страница Смартфоны");

        WebElement showAll = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[text()='Показать всё'])[1]")));
        builder.moveToElement(showAll).click().build().perform();
        logger.info("Нажата кнопка 'Показать все'");

        WebElement searchRealme = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//label[text()='Поле поиска']/following::input)[1]")));
        searchRealme.sendKeys("realme");
        WebElement realme = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='realme']")));
        realme.click();
        searchRealme.clear();
        logger.info("Установлен фильтр Realme");


        WebElement searchXiaomi = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//label[text()='Поле поиска']/following::input)[1]")));
        searchXiaomi.sendKeys("xiaomi");
        WebElement xiaomi = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Xiaomi']")));
        xiaomi.click();
        searchXiaomi.clear();
        logger.info("Установлен фильтр Xiaomi");


        WebElement price = (new WebDriverWait(driver, 5 ))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(@class,'link link_theme_major')])[2]")));
        builder.moveToElement(price).click().build().perform();
        logger.info("Установлен фильтр по цене(от меньшей к большей)");

        WebElement firstXiaomi = search("//img[contains(@title, 'Xiaomi')]/../../..//div[@data-zone-name=\"ComparisonTumbler\"]", "//a[contains(@title, 'Xiaomi')]/..//div[contains(@data-bem, 'compare')]");
        builder.moveToElement(firstXiaomi).click().build().perform();

        compare("Xiaomi");

        WebElement firstRealme = search("//img[contains(@title, 'realme')]/../../..//div[@data-zone-name=\"ComparisonTumbler\"]", "//a[contains(@title, 'realme')]/..//div[contains(@data-bem, 'compare')]");
        builder.moveToElement(firstRealme).click().build().perform();

        compare("Realme");

        WebElement sectionCompare = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='_24DZ8VtEeO _3Vm6nmYXxJ']")));
        builder.moveToElement(sectionCompare).click().build().perform();
        logger.info("Переход в раздел 'Сравнение'");

        List<WebElement> elements = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@class='n-compare-head__name link']")));
        Assert.assertEquals(2, elements.size());
        logger.info("В списке товаров 2 позиции");

        WebElement spec = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='link n-compare-show-controls__all']//span[@class='link__inner']")));
        builder.moveToElement(spec).click().build().perform();
        logger.info("Выбрана опция 'Все характеристики'");

        WebElement operSys = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=\"Операционная система\"]")));
        Assert.assertTrue(operSys.getText().equalsIgnoreCase("Операционная система"));
        logger.info("Присутствует позиция 'Операционная система'");

        WebElement anotherSpec = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=\"различающиеся характеристики\"]")));
        builder.moveToElement(anotherSpec).click().build().perform();
        logger.info("Выбрана опция 'Различающиеся характеристики'");

        Assert.assertFalse(operSys.isDisplayed());
        logger.info("Отсутствует позиция 'Операционная система'");
    }

    public WebElement search(String locat1, String locat2) {
        try {
            return driver.findElement(By.xpath(locat1));
        } catch (Exception e) {
            return driver.findElement(By.xpath(locat2));
        }
    }

    public void compare(String name) {
        WebElement addCompare = (new WebDriverWait(driver, 15))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='popup-informer__title']")));
        Assert.assertTrue(addCompare.getText().contains("добавлен к сравнению"));
        logger.info(name + " добавлен к сравнению");
    }

    @AfterClass
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
