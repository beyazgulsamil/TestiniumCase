package steps;

import io.cucumber.java.en.Then;
import pages.ProductPage;

public class ProductPageSteps {
    ProductPage productPage = new ProductPage();

    @Then("Save product information")
    public void saveProduct(){productPage.saveProduct();}

    @Then("Add to basket")
    public void addBasket(){productPage.clickElement(productPage.addBasketButton);}

    @Then("I hover to basket button")
    public void hoverBasket(){productPage.hoverOver(productPage.addBasketButton,System.currentTimeMillis());}
}
