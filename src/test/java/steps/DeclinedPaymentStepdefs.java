package steps;

import com.codeborne.selenide.Configuration;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.Booking;
import pages.Home;
import pages.Payment;

import static com.codeborne.selenide.Selenide.open;

public class DeclinedPaymentStepdefs {
    String homeUrl = "https://www.ryanair.com/ie/en/";
    Home homePage;
    Booking bookingPage;
    Payment paymentPage;


//
//
//    @Parameterized.Parameters
//    public List<Object[]> data() {
//        return Arrays.asList(new Object[1][0]);
//    }
//
    @Before
    public void setUp() throws Exception {
//        System.setProperty("selenide.browser", "Chrome");
//        System.setProperty("chromeoptions.args", "--disable-infobars");
        Configuration.timeout = 6000;
        Configuration.reportsFolder = "target/surefire-reports";
        open(homeUrl);
    }

//    @Rule
//    public ScreenShooter makeScreenshotOnFailure = ScreenShooter.failedTests();


    @Given("^I make a booking from \"([^\"]*)\" to \"([^\"]*)\" on (.*)/(.*)/(.*) for 2 adults and 1 child$")
    public void iMakeABookingFromDublinToWroclawOnForAdultsAndChild(String flightFrom, String flightTo, String flightDay, String flightMonth, String flightYear) throws Throwable {
        homePage = new Home();
        bookingPage = new Booking();

        homePage.selectFlightFrom(flightFrom)
                .selectFlightTo(flightTo)
                .submitFlightConnection()
                .selectFlightOutDate(flightYear, flightMonth, flightDay)
                .selectPassengers()
                .submitFlightOptions();

        bookingPage.selectFlightConnectionTime()
                .selectFlightFlexiPlusFarePrice()
                .submitFlightFarePrice()
                .selectSeats()
                .submitSeats()
                .checkout();
    }

    @When("^I pay for booking with card details \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iPayForBookingWithCardDetails(String cardNumber, String validityDate, String CVV) throws Throwable {
        paymentPage = new Payment();
        paymentPage.login()
                .enterFirstAdultPassengerData()
                .enterSecondAdultPassengerData()
                .enterChildPassengerData()
                .enterCardDetails(cardNumber, validityDate, CVV)
                .enterAddressDetails()
                .pay();
    }

    @Then("^I should get payment declined message$")
    public void iShouldGetPaymentDeclinedMessage() throws Throwable {
        paymentPage = new Payment();
        paymentPage.checkMessageWithDeclinedPayment();
    }


}
