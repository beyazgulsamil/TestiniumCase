package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utilities;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ProductPage extends Utilities {

    @FindBy(css = ".r-onepp-title")
    public WebElement productInfo;

    @FindBy(css = "#sellerPriceShippingKey .lowPrice")
    public WebElement productPrice;

    @FindBy(css = "#sellerPriceShippingKeyContainer #sp-addbasket-button")
    public WebElement addBasketButton;

    @FindBy(css = ".basket-title")
    public WebElement basketButton;

    public void saveProduct (){
        List<String> productFile = new ArrayList<>();
        String info = productInfo.getText();
        productPriceGlobal = productPrice.getText();
        productFile.add(info);
        productFile.add(productPriceGlobal);
        try {
            PrintWriter writer = new PrintWriter("C:\\Users\\beyaz\\Desktop\\"+"file"+".txt","UTF-8");
            for (String input:productFile) {
                writer.println(input);
            }
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}
