package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Home {
    Condition clickable = and("can be clicked", visible, enabled);

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

    public Home selectFlightFrom(String flightFrom) {
        url().equals(homeUrl);
        flightSearch.waitUntil(visible, 6000);
        banners.first().shouldBe(visible);
        flightSearch.scrollTo();
        inputFlightFrom.clear();
        inputFlightFrom.sendKeys(flightFrom);
        inputFlightFrom.pressEnter();
        return this;
    }

    public Home selectFlightTo(String flightTo) {
        inputFlightTo.clear();
        inputFlightTo.sendKeys(flightTo);
        inputFlightTo.pressEnter();
        return this;
    }

    public Home submitFlightConnection() {
        btnContinue.waitUntil(visible, 6000).click();
        return this;
    }

    public Home selectFlightOutDate(String flightYear, String flightMonth, String flightDay) {
        inputFlightOutYear.waitUntil(visible, 6000).click();
        inputFlightOutYear.clear();
        inputFlightOutYear.sendKeys(flightYear);
        inputFlightOutDay.click();
        inputFlightOutDay.clear();
        inputFlightOutDay.sendKeys(flightDay);
        inputFlightOutMonth.click();
        inputFlightOutMonth.clear();
        inputFlightOutMonth.sendKeys(flightMonth);
        oneWayFlightSearch.click();
        return this;
    }

    public Home selectPassengers() {
        dropDown.waitUntil(visible, 6000).click();
        passengersPopup.waitUntil(visible, 6000);
        plusOneAdultPassengerToOneExisting.shouldBe(clickable).click();
        plusOneChild.shouldBe(clickable).click();
        return this;
    }

    public Home submitFlightOptions() {
        btnSearchFlights.shouldBe(clickable).click();
        return this;
    }

}
