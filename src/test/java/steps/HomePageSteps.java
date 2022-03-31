package steps;

import io.cucumber.java.en.Then;
import org.openqa.selenium.Keys;
import pages.HomePage;

public class HomePageSteps {
    HomePage homePage = new HomePage();

    @Then("I search {}")
    public void search(String product) {
        homePage.fillInput(homePage.searchBox,product);
        homePage.click(homePage.searchButton);
    }


}
