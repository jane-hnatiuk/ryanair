package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.qameta.allure.Attachment;
import pages.Booking;
import pages.Home;
import pages.Payment;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

public class DeclinedPaymentStepdefs {
    String homeUrl = "https://www.ryanair.com/ie/en/";
    Home homePage;
    Booking bookingPage;
    Payment paymentPage;

    @Before
    public void setUp() throws Exception {
        Configuration.timeout = 6000;
        Configuration.reportsFolder = "allure-report/data/attachments";
        open(homeUrl);
    }

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

//    @After
//    private void makeScreenshot(Scenario scenario) {
//        if (scenario.isFailed()) {
//            byte[] screenshot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
//            scenario.embed(screenshot, "image/png");
//        }
//    }
//
//    @Attachment(type = "image/png")
//    public byte[] makeScreenshotOnFailure() {
//        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
//    }

//    @Rule
//    public ScreenShooter makeScreenshotOnFailure = ScreenShooter.failedTests().succeededTests();


//    @Rule
//    public TestWatcher screenshotOnFailure = new TestWatcher() {
//        @Override
//        protected void failed(Throwable e, Description description) {
//            makeScreenshotOnFailure();
//        }
//



//    @After
//    private void addScreenshotToErrorTest() throws IOException {
//    File lastSelenideScreenshot = Screenshots.getLastScreenshot();
//    if (lastSelenideScreenshot != null) {
//            screenshot(Files.toByteArray(lastSelenideScreenshot));
//        }
//    }
//
//    @Attachment(type = "image/png")
//    public static byte[] screenshot(byte[] dataForScreenshot) throws IOException {
//        return dataForScreenshot;
//    }

    @After
    public void tearDown() throws IOException {
        screenshot();
    }

    @Attachment(type = "image/png")
    public byte[] screenshot() throws IOException {
        File screenshot = Screenshots.takeScreenShotAsFile();
        return Files.toByteArray(screenshot);
    }


}
