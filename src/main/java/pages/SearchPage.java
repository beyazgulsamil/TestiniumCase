package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utilities;

import java.util.List;

public class SearchPage extends Utilities {

    @FindBy(css = "[data-testid=\"pagination-list-item\"] span")
    public List<WebElement> searchPageList;

    @FindBy(css = "[data-cy=\"product-card-item\"]")
    public List<WebElement> productList;

}
