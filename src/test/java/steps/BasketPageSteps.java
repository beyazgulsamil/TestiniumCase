package steps;

import io.cucumber.java.en.Then;
import pages.BasketPage;

public class BasketPageSteps {
    BasketPage basketPage = new BasketPage();

    @Then("I verify product price")
    public void priceVerify() {
        basketPage.equalsElement(basketPage.productPriceToBasket, basketPage.productPriceGlobal);
    }

    @Then("I add {} product and verify")
    public void addItem(Integer rank) {
        basketPage.clickElement(basketPage.productNumberButton);
        basketPage.rankLoopClick(basketPage.productNumberList, rank);
        basketPage.verifyNumber(basketPage.productNumber, rank);
    }

    @Then("Empty the basket")
    public void emptyBasket() {
        basketPage.deleteProducts(basketPage.productListDeleteButton);
    }

    @Then("Verify basket")
    public void verifyBasket(){
        basketPage.verifyEmpty(basketPage.productList);
    }
}
