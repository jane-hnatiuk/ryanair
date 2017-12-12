package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DeclinedPaymentStepdefs {

    @Given("^I make a booking from “Dublin” to “Wroclaw” on (\\d+)/(\\d+)/(\\d+) for (\\d+) adults and (\\d+) child$")
    public void iMakeABookingFromDublinToWroclawOnForAdultsAndChild(int arg0, int arg1, int arg2, int arg3, int arg4) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I pay for booking with card details “(\\d+) (\\d+) (\\d+) (\\d+)”, “(\\d+)/(\\d+)” and “(\\d+)”$")
    public void iPayForBookingWithCardDetailsAnd(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I should get payment declined message$")
    public void iShouldGetPaymentDeclinedMessage() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
