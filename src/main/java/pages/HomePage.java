package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utilities;

public class HomePage extends Utilities {

    @FindBy(css = "[placeholder=\"Keşfetmeye Bak\"]")
    public WebElement searchBox;

    @FindBy(css = "[type=\"submit\"]")
    public WebElement searchButton;
}
