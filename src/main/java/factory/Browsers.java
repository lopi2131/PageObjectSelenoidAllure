package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

public enum Browsers {
    CHROME {
        public WebDriver create(){
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(System.getProperty("options"));
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(chromeOptions);
        }
    },
    IE {
        public WebDriver create(){
            return new InternetExplorerDriver();
        }
    },
    FIREFOX {
        public WebDriver create() {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments(System.getProperty("options"));
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver(firefoxOptions);
        }
    };

    public WebDriver create(){
        return null;
    }
}
