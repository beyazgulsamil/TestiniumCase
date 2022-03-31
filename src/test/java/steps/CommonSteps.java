package steps;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utils.Utilities;

public class CommonSteps extends Utilities {

    @Before
    public void start(){initDriver();}

    @After
    public void kill(Scenario scenario) {teardown(scenario);}

    @Given("I navigate to {}")
    public void navigate(String url){driver.get("https://"+url);}

    @Then("I wait for {} seconds")
    public void waiting(double duration){
        pause(duration);}

    @Then("Window Maximize")
    public void maxWindow(){windowMaximize();}

    @Then("Switch use window")
    public void switchWindow(){switchWindowHandle(null);}

    @Then("I click to text {}")
    public void textClick(String text){pressButtonWithText(text);}

    @Then("Create new tab")
    public void createTab(){newTab();}

    @Then("Refresh page")
    public void refresh(){refreshPage();}


}
