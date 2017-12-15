package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Home {
   String homeUrl = "https://www.ryanair.com/ie/en/";
   private SelenideElement flightSearch = $("#search-container");
   private ElementsCollection banners = $$(By.cssSelector("div.homepagehero-outer-container>a>span"));
   private SelenideElement oneWayFlightSearch = $("#lbl-flight-search-type-one-way");
   private SelenideElement btnContinue = $(By.cssSelector("button[aria-hidden='false']"));

   private SelenideElement inputFlightFrom = $(By.cssSelector("input[aria-labelledby='label-airport-selector-from']"));
   private SelenideElement inputFlightTo = $(By.cssSelector("input[aria-labelledby='label-airport-selector-to']"));

   private SelenideElement inputFlightOutYear = $(By.cssSelector("input.yyyy[aria-label='Fly out: - YYYY']"));
   private SelenideElement inputFlightOutMonth = $(By.cssSelector("input.mm[aria-label='Fly out: - MM']"));
   private SelenideElement inputFlightOutDay = $(By.cssSelector("input.dd[aria-label='Fly out: - DD']"));

   private SelenideElement dropDown = $(By.cssSelector("div.dropdown-handle"));
   private SelenideElement passengersPopup = $(By.cssSelector("div.popup-passenger-input.opened"));
   private SelenideElement plusOneAdultPassengerToOneExisting = $(By.cssSelector("div.popup-passenger-input.opened > div > div > div.content > popup-content > div:nth-child(6) > div > div.details-controls > core-inc-dec > button.core-btn.inc.core-btn-wrap"));

   private SelenideElement plusOneChild = $(By.cssSelector("div.popup-passenger-input.opened > div > div > div.content > popup-content > div:nth-child(8) > div > div.details-controls > core-inc-dec > button.core-btn.inc.core-btn-wrap"));

   private SelenideElement btnSearchFlights = $(By.cssSelector("button[ng-click='searchFlights()']"));

    public Home selectOneWayTicket() {

        return this;
    }

    public Home selectFlightFrom(String flightFrom) {
        url().equals(homeUrl);
        this.flightSearch.waitUntil(visible, 6000);
        this.banners.first().shouldBe(visible);
        this.flightSearch.scrollTo();
        this.inputFlightFrom.clear();
        this.inputFlightFrom.sendKeys(flightFrom);
        this.inputFlightFrom.pressEnter();
        return this;
    }

    public Home selectFlightTo(String flightTo) {
        this.inputFlightTo.clear();
        this.inputFlightTo.sendKeys(flightTo);
        this.inputFlightTo.pressEnter();
        return this;
    }

    public Home submitFlightConnection() {
        this.btnContinue.waitUntil(visible, 6000);
        this.btnContinue.click();
        return this;
    }

    public Home selectFlightOutDate(String flightYear, String flightMonth, String flightDay) {
        this.inputFlightOutYear.waitUntil(visible, 6000);
        this.inputFlightOutYear.click();
        this.inputFlightOutYear.clear();
        this.inputFlightOutYear.sendKeys(flightYear);
        this.inputFlightOutDay.click();
        this.inputFlightOutDay.clear();
        this.inputFlightOutDay.sendKeys(flightDay);
        this.inputFlightOutMonth.click();
        this.inputFlightOutMonth.clear();
        this.inputFlightOutMonth.sendKeys(flightMonth);
        this.oneWayFlightSearch.click();
        return this;
    }

    public Home selectPassengers() {
        this.dropDown.waitUntil(visible, 6000);
        this.dropDown.click();
        this.passengersPopup.waitUntil(visible, 6000);
        this.plusOneAdultPassengerToOneExisting.waitUntil(visible, 6000);
        this.plusOneAdultPassengerToOneExisting.click();
        this.plusOneChild.click();
        return this;
    }

    public Home submitFlightOptions() {
        this.btnSearchFlights.click();
        return this;
    }

}
