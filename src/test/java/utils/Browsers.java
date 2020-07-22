package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

public enum Browsers {
    CHROME {
        public RemoteWebDriver create() throws MalformedURLException {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");
            capabilities.setVersion("84.0");
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", false);
            return new RemoteWebDriver(
                    URI.create("http://0.0.0.0:4444/wd/hub").toURL(),
                    capabilities
            );
        }
    },
    IE {
        public RemoteWebDriver create(){
            return new InternetExplorerDriver();
        }
    },
    FIREFOX {
        public RemoteWebDriver create() {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }
    };


    public RemoteWebDriver create() throws MalformedURLException {
        return null;
    }
}
