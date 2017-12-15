package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Booking {
    Condition clickable = and("can be clicked", visible, enabled);

    String bookingUrl = "https://www.ryanair.com/ie/en/booking/home";
    private SelenideElement flightTitle = $("#booking-selection");
    private SelenideElement flightBanner = $(By.cssSelector("div.flight-banner"));
    private SelenideElement flightTable = $(By.name("flightList"));

    private SelenideElement btnFirstAvailableFlightPrice = $$(By.xpath(".//div/div[2]/flights-table-price/div/div")).first();
    private SelenideElement flexiPlusFare = $$(By.cssSelector("span.flights-table-fares__fare-radio")).get(2);
    private SelenideElement iconSelected = $(By.cssSelector("core-icon.icon-40.fill-ryanair-green"));
    private SelenideElement btnContinue = $(By.cssSelector("button.core-btn-primary.core-btn-block.core-btn-medium"));

    String bookingSeatsUrl = "https://www.ryanair.com/ie/en/booking/extras/seats";
    private SelenideElement seatMap = $(By.cssSelector("div.seat-map-plane"));

    private SelenideElement chooseFirstAdultStandardSeat = $$(By.cssSelector("span.seat-click[ng-click='$ctrl.addSeat(seat)']")).get(15);
    private SelenideElement chooseSecondAdultStandardSeat = $$(By.cssSelector("span.seat-click[ng-click='$ctrl.addSeat(seat)']")).get(16);
    private SelenideElement chooseChildStandardSeat = $$(By.cssSelector("span.seat-click[ng-click='$ctrl.addSeat(seat)']")).get(17);

    private SelenideElement tblComfirmSeats = $(By.cssSelector("confirm-seats"));
    private SelenideElement btnSubmitSeats = $(By.cssSelector("button.core-btn-primary.dialog-overlay-footer__ok-button"));

    private SelenideElement popupReservedSeatsChildrenUnder12 = $(By.cssSelector("seat-map-prompt-content"));
    private SelenideElement btnOkIgotIt = $(By.cssSelector("button.core-btn-primary.same-seats.ng-click-active"));

    String bookingExtrasUrl = "https://www.ryanair.com/ie/en/booking/extras";
    private SelenideElement btnCheckout = $(By.cssSelector("button.core-btn-primary.core-btn-block.core-btn-medium.btn-text"));


    public Booking selectFlightConnectionTime() throws InterruptedException {
        url().equals(bookingUrl);
        this.flightTitle.waitUntil(visible, 6000);
        this.flightBanner.waitUntil(visible, 6000);
        this.flightTable.waitUntil(visible, 6000);
        this.btnFirstAvailableFlightPrice.waitUntil(visible, 6000);
        this.btnFirstAvailableFlightPrice.click();
        return this;
    }

    public Booking selectFlightFlexiPlusFarePrice() {
        this.flexiPlusFare.waitUntil(visible, 6000);
        this.flexiPlusFare.scrollTo();
        this.flexiPlusFare.shouldBe(clickable);
        this.flexiPlusFare.click();
        return this;
    }

    public Booking submitFlightFarePrice() {
        this.iconSelected.waitUntil(visible, 6000);
        this.btnContinue.waitUntil(visible, 6000);
        this.btnContinue.click();
        return this;
    }

    public Booking selectSeats() throws InterruptedException {
        url().equals(bookingSeatsUrl);
        if (this.popupReservedSeatsChildrenUnder12.isDisplayed()) {
            this.btnOkIgotIt.click();
        }
        this.seatMap.waitUntil(visible, 6000);
        this.chooseFirstAdultStandardSeat.waitUntil(visible, 6000);
        this.chooseFirstAdultStandardSeat.click();
        this.chooseFirstAdultStandardSeat.isSelected();
        this.chooseSecondAdultStandardSeat.waitUntil(visible, 6000);
        this.chooseSecondAdultStandardSeat.click();
        this.chooseSecondAdultStandardSeat.isSelected();
        this.chooseChildStandardSeat.waitUntil(visible, 6000);
        this.chooseChildStandardSeat.click();
        this.chooseChildStandardSeat.isSelected();
        this.btnSubmitSeats.waitUntil(visible, 6000);
        this.btnSubmitSeats.click();
        return this;
    }

    public Booking submitSeats() {
        this.tblComfirmSeats.waitUntil(visible, 6000);
        this.btnSubmitSeats.waitUntil(visible, 6000);
        this.btnSubmitSeats.click();
        return this;
    }

    public Booking checkout() {
        url().equals(bookingExtrasUrl);
        this.btnCheckout.waitUntil(visible, 6000);
        this.btnCheckout.click();
        return this;
    }

}