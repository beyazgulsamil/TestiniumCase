package utils;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.driver.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public abstract class Utilities extends Driver { //TODO: Write a method which creates a unique css selector for elements


    public Utilities() {
        PageFactory.initElements(driver, this);
    }

    public static String productPriceGlobal;

    public void click(WebElement element) {
        waitUntilClickable(scroll(element), System.currentTimeMillis()).click();
    }

    public void fillInput(WebElement inputBox, String inputText) {
        clear(scroll(waitUntilVisible(inputBox, System.currentTimeMillis()))).sendKeys(inputText);
    }

    public WebElement clear(WebElement inputBox) {
        inputBox.clear();
        for (int i = 0; i < inputBox.getAttribute("value").length(); i++) {
            inputBox.sendKeys(Keys.BACK_SPACE);
        }
        return inputBox;
    }

    public WebElement scroll(WebElement element) {
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
        pause(0.5);
        return element;
    }

    public void pause(double duration) {
        try {
            Thread.sleep((long) (duration * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public WebElement waitUntilVisible(WebElement element, long initialTime) {
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        if (System.currentTimeMillis() - initialTime > 15000) {
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            return null;
        }
        try {
            if (!element.isDisplayed()) {
                waitUntilVisible(element, initialTime);
            }
        } catch (StaleElementReferenceException | NoSuchElementException | TimeoutException exception) {
            waitUntilVisible(element, initialTime);
        }
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return element;
    }

    public WebElement waitUntilClickable(WebElement element, long initialTime) {
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        if (System.currentTimeMillis() - initialTime > 15000) {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            return null;
        }
        try {
            if (!element.isEnabled()) {
                waitUntilClickable(element, initialTime);
            }
        } catch (StaleElementReferenceException | NoSuchElementException | TimeoutException exception) {
            waitUntilClickable(element, initialTime);
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return element;
    }

    public void equalsElement(WebElement element, String equalsInput) {
            Assert.assertEquals(element.getText(),equalsInput);
        }

    public void equalsText(String input, String equalsInput) {
            Assert.assertEquals(input,equalsInput);
        }

    public void rankLoopClick(List<WebElement> list, Integer rank) {
        int i = 0;
        for (WebElement item : list) {
            int x = i + 1;
            i++;
            if (Objects.equals(x, rank)) {
                clickElement(item);
                break;
            }
        }
    }

    public void verifyNumber(WebElement element,Integer rank){
        String basketNumber = element.getAttribute("data-value").toString();
        String rankString = Integer.toString(rank);
        equalsText(basketNumber,rankString);
    }

    public void randomLoopClick(List<WebElement> list) {
        int randomNumber = (int) (Math.random() * list.size());
        int i = 0;
        for (WebElement item : list) {
            int x = i + 1;
            i++;
            if (Objects.equals(x - 1, randomNumber)) {
                clickElement(item);
                break;
            }
        }
    }

    public void pressButtonWithText(String buttonText) {
        String locator = "//*[contains(text(), '" + buttonText + "')]";
        click(driver.findElement(By.xpath(locator)));
    }

    public void windowMaximize() {
        driver.manage().window().maximize();
    }

    public void refreshPage() {
        String url = driver.getCurrentUrl();
        driver.get(url);
    }

    public void newTab() {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public void clickElement(WebElement element) {
        try {
            centerElement(waitUntilElementIsClickable(element, System.currentTimeMillis())).click();
        } catch (ElementNotFoundException e) {
            Assert.fail(e.getMessage());
        }
    }

    public WebElement hoverOver(WebElement element, Long initialTime) {
        if (System.currentTimeMillis() - initialTime > 10000)
            return null;
        centerElement(element);
        Actions actions = new Actions(driver);
        try {
            actions.moveToElement(element).build().perform();
        } catch (StaleElementReferenceException ignored) {
            hoverOver(element, initialTime);
        }
        return element;
    }

    public String switchWindowHandle(String handle) {
        String parentWindowHandle = driver.getWindowHandle();
        if (handle == null)
            for (String windowHandle : driver.getWindowHandles()) {
                if (!windowHandle.equalsIgnoreCase(parentWindowHandle))
                    driver = (RemoteWebDriver) driver.switchTo().window((windowHandle));
            }
        else
            driver = (RemoteWebDriver) driver.switchTo().window(handle);
        return parentWindowHandle;
    }

    //This method makes the thread wait for a certain while
    public void waitFor(double seconds) {
        if (seconds > 1)
            try {
                Thread.sleep((long) (seconds * 1000L));
            } catch (InterruptedException exception) {
                Assert.fail(exception.getLocalizedMessage());
            }
    }

    //This method scrolls an element to the center of the view
    public WebElement centerElement(WebElement element) {

        String scrollScript = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        ((JavascriptExecutor) driver).executeScript(scrollScript, element);

        waitFor(0.5);
        return element;
    }

    public WebElement waitUntilElementIsClickable(WebElement element, long initialTime) {
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        if (System.currentTimeMillis() - initialTime > 15000) {
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            return null;
        }
        try {
            if (!element.isEnabled()) {
                waitUntilElementIsClickable(element, initialTime);
            }
        } catch (StaleElementReferenceException | NoSuchElementException | TimeoutException exception) {
            waitUntilElementIsClickable(element, initialTime);
        }
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return element;
    }


}
