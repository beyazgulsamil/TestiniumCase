package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utilities;

import java.util.List;

public class BasketPage extends Utilities {

    @FindBy(css = ".total-price strong")
    public WebElement productPriceToBasket;

    @FindBy(css = ".amount option")
    public List<WebElement> productNumberList;

    @FindBy(css = ".pull-right-m .gg-input-select")
    public WebElement productNumberButton;

    @FindBy(css = ".pull-right-m .gg-input-select select")
    public WebElement productNumber;

    @FindBy(css = ".product-item-box-container")
    public List<WebElement> productList;

    @FindBy(css = ".product-item-box-container .btn-delete .gg-icon")
    public List<WebElement> productListDeleteButton;

    public void deleteProducts(List<WebElement> list) {
        for (WebElement item : list) {
            clickElement(item);
            return;
        }
        Assert.fail("No such element was found...");
    }

    public void verifyEmpty(List<WebElement> list) {
        Assert.assertNotNull("Basket not Empty",list);
    }
}
