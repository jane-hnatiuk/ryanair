package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Booking {
    Condition clickable = and("can be clicked", visible, enabled);

    String bookingUrl = "https://www.ryanair.com/ie/en/booking/home";
    private SelenideElement flightTitle = $("#booking-selection");
    private SelenideElement flightBanner = $("div.flight-banner");
    private SelenideElement flightTable = $(By.name("flightList"));

    private SelenideElement btnFirstAvailableFlightPrice = $$x(".//div/div[2]/flights-table-price/div/div").first();
    private SelenideElement flexiPlusFarePrice = $$("span.flights-table-fares__fare-radio").get(2);
    private SelenideElement iconSelected = $("core-icon.icon-40.fill-ryanair-green");
    private SelenideElement btnContinue = $("button.core-btn-primary.core-btn-block.core-btn-medium");

    String bookingSeatsUrl = "https://www.ryanair.com/ie/en/booking/extras/seats";
    private SelenideElement seatMap = $("div.seat-map-plane");

    private SelenideElement chooseFirstAdultStandardSeat = $$("span.seat-click").get(15);
    private SelenideElement chooseSecondAdultStandardSeat = $$("span.seat-click").get(16);
    private SelenideElement chooseChildStandardSeat = $$("span.seat-click").get(17);

    private SelenideElement tblComfirmSeats = $("confirm-seats");
    private SelenideElement btnSubmitSeats = $("button.core-btn-primary.dialog-overlay-footer__ok-button");

    private SelenideElement popupReservedSeatsChildrenUnder12 = $("seat-map-prompt-content");
    private SelenideElement btnOkIgotIt = $("button.core-btn-primary.same-seats.ng-click-active");

    String bookingExtrasUrl = "https://www.ryanair.com/ie/en/booking/extras";
    private SelenideElement btnCheckout = $("button.core-btn-primary.core-btn-block.core-btn-medium.btn-text");


    public Booking selectFlightConnectionTime(){
        flightTitle.shouldBe(visible);
        flightBanner.shouldBe(visible);
        flightTable.shouldBe(visible);
        btnFirstAvailableFlightPrice.shouldBe(visible).click();
        return this;
    }

    public Booking selectFlightFlexiPlusFarePrice(){
        flexiPlusFarePrice.shouldBe(visible).scrollTo().shouldBe(clickable).hover().click();
        return this;
    }

    public Booking submitFlightFarePrice() {
        iconSelected.shouldBe(visible);
        btnContinue.shouldBe(visible).click();
        return this;
    }

    public Booking selectSeats(){
        if (popupReservedSeatsChildrenUnder12.isDisplayed()) {
            btnOkIgotIt.click();
        }
        chooseFirstAdultStandardSeat.shouldBe(clickable).hover().click();
        chooseSecondAdultStandardSeat.shouldBe(clickable).hover().click();
        chooseChildStandardSeat.shouldBe(clickable).hover().click();
        btnSubmitSeats.shouldBe(visible).click();
        return this;
    }

    public Booking submitSeats() {
        tblComfirmSeats.shouldBe(visible);
        btnSubmitSeats.shouldBe(visible).click();
        return this;
    }

    public Booking checkout() {
        btnCheckout.shouldBe(visible).click();
        return this;
    }

}