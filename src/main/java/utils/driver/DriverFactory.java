package utils.driver;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public static RemoteWebDriver driverInit(String browserName){
        RemoteWebDriver driver;
        Properties properties = new Properties();
        try {// I used try catch in case of FileNotFoundException from file reader
            properties.load(new FileReader("src/test/resources/test.properties"));
            DesiredCapabilities capabilities;
            switch (browserName){
                case "Chrome":
                    capabilities = DesiredCapabilities.chrome();
                    ChromeOptions options = new ChromeOptions();
                    options.setExperimentalOption("useAutomationExtension", false);
                    options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                    options.addArguments("disable-notifications");
                    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                    break;
                case "Firefox":
                    capabilities = DesiredCapabilities.firefox();
                    break;
                default:
                    Assert.fail("No such driver was defined");
                    return null;
            }
            driver = new RemoteWebDriver(new URL(properties.getProperty("hub.url")),capabilities);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            return driver;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
