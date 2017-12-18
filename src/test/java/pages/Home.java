package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Home {
    Condition clickable = and("can be clicked", visible, enabled);

   private SelenideElement flightSearch = $("#search-container");
   private SelenideElement firstBanner = $$("div.homepagehero-outer-container>a>span").first();
   private SelenideElement oneWayFlightSearch = $("#lbl-flight-search-type-one-way");
   private SelenideElement btnContinue = $("button[aria-hidden='false']");

   private SelenideElement inputFlightFrom = $("input[aria-labelledby='label-airport-selector-from']");
   private SelenideElement inputFlightTo = $("input[aria-labelledby='label-airport-selector-to']");

   private SelenideElement inputFlightOutYear = $("input.yyyy[aria-label='Fly out: - YYYY']");
   private SelenideElement inputFlightOutMonth = $("input.mm[aria-label='Fly out: - MM']");
   private SelenideElement inputFlightOutDay = $("input.dd[aria-label='Fly out: - DD']");

   private SelenideElement dropDown = $("div.dropdown-handle");
   private SelenideElement passengersPopup = $("div.popup-passenger-input.opened");
   private SelenideElement plusOneAdultPassengerToOneExisting = $$("button.core-btn.inc.core-btn-wrap").first();

   private SelenideElement plusOneChild = $$("button.core-btn.inc.core-btn-wrap").get(2);

   private SelenideElement btnSearchFlights = $("button[ng-click='searchFlights()']");

    public Home selectFlightFrom(String flightFrom) {
        flightSearch.shouldBe(visible);
        firstBanner.shouldBe(visible);
        flightSearch.scrollTo();
        inputFlightFrom.setValue(flightFrom);
        inputFlightFrom.pressEnter();
        return this;
    }

    public Home selectFlightTo(String flightTo) {
        inputFlightTo.setValue(flightTo);
        inputFlightTo.pressEnter();
        return this;
    }

    public Home submitFlightConnection() {
        btnContinue.shouldBe(visible).click();
        return this;
    }

    public Home selectFlightOutDate(String flightYear, String flightMonth, String flightDay) {
        inputFlightOutYear.shouldBe(visible).click();
        inputFlightOutYear.setValue(flightYear);
        inputFlightOutDay.setValue(flightDay);
        inputFlightOutMonth.setValue(flightMonth);
        oneWayFlightSearch.click();
        return this;
    }

    public Home selectPassengers() {
        dropDown.shouldBe(clickable).click();
        passengersPopup.shouldBe(visible);
        plusOneAdultPassengerToOneExisting.shouldBe(clickable).click();
        plusOneChild.shouldBe(clickable).click();
        return this;
    }

    public Home submitFlightOptions() {
        btnSearchFlights.shouldBe(clickable).doubleClick();
        return this;
    }

}
