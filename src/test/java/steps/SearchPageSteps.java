package steps;

import io.cucumber.java.en.Then;
import pages.SearchPage;

public class SearchPageSteps {
    SearchPage searchPage = new SearchPage();

    @Then("I click to {} rank page")
    public void clickRankPage(Integer rank) {
        searchPage.rankLoopClick(searchPage.searchPageList, rank);
    }

    @Then("I click random product")
    public void clickRandomProduct(){
        searchPage.randomLoopClick(searchPage.productList);
    }
}
