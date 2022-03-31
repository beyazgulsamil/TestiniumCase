package utils.driver;

import io.cucumber.core.api.Scenario;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Driver {
    public static RemoteWebDriver driver;

    public void initDriver() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/test/resources/test.properties"));
            driver = DriverFactory.driverInit(properties.getProperty("browser"));
            assert driver != null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void teardown(Scenario scenario){
        if (scenario.isFailed())
            captureScreen(scenario.getName(),driver);
        driver.quit();
    }
    public void captureScreen(String specName, RemoteWebDriver driver) {
        try {
            System.out.println("Capturing page");

            String name = specName+"#"+Math.random()*10000+".jpg";
            File sourceFile = new File("Screenshots");
            File fileDestination  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(fileDestination, new File(sourceFile, name));

            System.out.println("Screenshot saved as; "+name+" at the \"Screenshots\" file.");
        }
        catch (Exception gamma){
            Assert.fail("Could not capture screen\n\t"+gamma);
            driver.quit();
        }
    }
}
