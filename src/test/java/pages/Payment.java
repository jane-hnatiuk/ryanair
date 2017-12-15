package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.url;
import static jdk.nashorn.internal.objects.NativeString.substring;
import static jdk.nashorn.internal.objects.NativeString.trim;

public class Payment {
    Condition clickable = and("can be clicked", visible, enabled);


    String paymentURL = "https://www.ryanair.com/ie/en/booking/payment";

    String email = "ryanairbugs@protonmail.com";
    String password = "P@ssw0rd";

    String cardType = "MasterCard";
    String cardHolderName = "Ryanair Bug";
    String billingAddressLine1 = "Address 1";
    String billingAddressLine2 = "Address 2";
    String billingCity = "Dublin";
    String billingPostcode = "12345";


    private SelenideElement blockLoginRegister = $(By.cssSelector("div.login-register"));
    private SelenideElement btnLogin = $(By.cssSelector("button.core-btn-secondary"));
    private SelenideElement popupSignupLogin = $(By.cssSelector("signup-home-form"));
    private SelenideElement inputEmail = $(By.cssSelector("input[type='email']"));
    private SelenideElement inputPassword = $(By.cssSelector("input[type='password']"));
    private SelenideElement btnSubmitLogin = $(By.cssSelector("button-spinner > button.core-btn-primary"));
    private SelenideElement formPassengersDetails = $(By.cssSelector("div.passengers-form"));
    private SelenideElement inputFirstAdultPassengerName = $$(By.cssSelector("input[placeholder='e.g. John']")).first();
    private SelenideElement inputSecondAdultPassengerName = $$(By.cssSelector("input[placeholder='e.g. John']")).get(1);
    private SelenideElement inputChildPassengerName = $$(By.cssSelector("input[placeholder='e.g. John']")).last();

    private SelenideElement titlePaymentMethod = $(By.cssSelector("h4.payment-methods-title"));
    private SelenideElement inputDebitCreditCard = $("CC");

    private SelenideElement inputCardNumber = $(By.name("cardNumber"));

    private SelenideElement selectCardType = $(By.name("cardType"));
    private SelenideElement selectExpiryMonth = $(By.name("expiryMonth"));
    private SelenideElement selectExpiryYear = $(By.name("expiryYear"));
    private SelenideElement inputSecurityCode = $(By.name("securityCode"));
    private SelenideElement inputCardHolderName = $(By.name("cardHolderName"));

    private SelenideElement formBillingAddress = $(By.cssSelector("address-form"));
    private SelenideElement inputAddressLine1 = $("#billingAddressAddressLine1");
    private SelenideElement inputAddressLine2 = $("#billingAddressAddressLine2");
    private SelenideElement inputCity = $("#billingAddressCity");
    private SelenideElement inputPostCode = $("#billingAddressPostcode");

    private SelenideElement checkboxAcceptPolicy = $(By.cssSelector("input[name='acceptPolicy']"));
    private SelenideElement btnPayNow = $(By.cssSelector("button.core-btn-primary.core-btn-medium"));

    private SelenideElement messagePaymentDeclinedError = $(By.cssSelector("prompt.error.prompt-text[text='common.components.payment_forms.error_explain_declined']"));



    public Payment login() {
        url().equals(paymentURL);
        this.blockLoginRegister.waitUntil(visible, 6000);
        this.btnLogin.waitUntil(visible, 6000);
        this.btnLogin.click();
        this.popupSignupLogin.waitUntil(visible, 6000);
        this.inputEmail.waitUntil(clickable, 6000);
        this.inputEmail.click();
        this.inputEmail.clear();
        this.inputEmail.sendKeys(email);
        this.inputPassword.click();
        this.inputPassword.clear();
        this.inputPassword.sendKeys(password);
        this.btnSubmitLogin.waitUntil(clickable, 6000);
        this.btnSubmitLogin.shouldBe(clickable);
        this.btnSubmitLogin.click();
        return this;
    }

    public Payment enterFirstAdultPassengerData() {
        this.formPassengersDetails.waitUntil(visible, 6000);
        this.inputFirstAdultPassengerName.waitUntil(visible, 6000);
        this.inputFirstAdultPassengerName.click();
        this.inputFirstAdultPassengerName.sendKeys(Keys.ARROW_DOWN);
        this.inputFirstAdultPassengerName.pressEnter();
        return this;
    }

    public Payment enterSecondAdultPassengerData() {
        this.inputSecondAdultPassengerName.waitUntil(clickable, 6000);
        this.inputSecondAdultPassengerName.click();
        this.inputSecondAdultPassengerName.pressEnter();
        return this;
    }

    public Payment enterChildPassengerData() {
        this.inputChildPassengerName.waitUntil(clickable, 6000);
        this.inputChildPassengerName.click();
        this.inputChildPassengerName.pressEnter();
        return this;

    }

    public Payment enterCardDetails(String cardNumber, String validityDate, String CVV) {
        this.titlePaymentMethod.isDisplayed();
        this.titlePaymentMethod.scrollTo();

        this.inputCardNumber.isDisplayed();
        this.inputCardNumber.click();
        this.inputCardNumber.clear();
        this.inputCardNumber.sendKeys(trim(cardNumber.substring(0, cardNumber.length() - 15)));
        this.inputCardNumber.sendKeys(trim(cardNumber.substring(5, cardNumber.length() - 10)));
        this.inputCardNumber.sendKeys(trim(cardNumber.substring(10, cardNumber.length() - 5)));
        this.inputCardNumber.sendKeys(trim(cardNumber.substring(cardNumber.length() - 5)));

        this.selectCardType.isDisplayed();
        this.selectCardType.click();
        this.selectCardType.selectOptionContainingText(cardType);

        this.selectExpiryMonth.isDisplayed();
        this.selectExpiryMonth.click();
        this.selectExpiryMonth.selectOptionContainingText(validityDate.substring(0, validityDate.length() - 3));

        this.selectExpiryYear.isDisplayed();
        this.selectExpiryYear.click();
        this.selectExpiryYear.selectOptionContainingText("20"+validityDate.substring(3));

        this.inputSecurityCode.isDisplayed();
        this.inputSecurityCode.click();
        this.inputSecurityCode.sendKeys(CVV);

        this.inputCardHolderName.isDisplayed();
        this.inputCardHolderName.sendKeys(cardHolderName);
        return this;

    }

    public Payment enterAddressDetails() {
        this.formBillingAddress.isDisplayed();
        this.formBillingAddress.scrollTo();

        this.inputAddressLine1.isDisplayed();
        this.inputAddressLine1.click();
        this.inputAddressLine1.sendKeys(billingAddressLine1);

        this.inputAddressLine2.isDisplayed();
        this.inputAddressLine2.click();
        this.inputAddressLine2.sendKeys(billingAddressLine2);

        this.inputCity.isDisplayed();
        this.inputCity.click();
        this.inputCity.sendKeys(billingCity);

        this.inputPostCode.isDisplayed();
        this.inputPostCode.click();
        this.inputPostCode.sendKeys(billingPostcode);
        return this;

    }

    public Payment pay() {
        this.checkboxAcceptPolicy.exists();
        this.checkboxAcceptPolicy.click();
        this.btnPayNow.isDisplayed();
        this.btnPayNow.click();
        return this;

    }

    public Payment checkMessageWithDeclinedPayment() {
        this.titlePaymentMethod.isDisplayed();
        this.titlePaymentMethod.scrollTo();
        this.messagePaymentDeclinedError.waitUntil(visible, 6000);
        this.messagePaymentDeclinedError.isDisplayed();
        return this;
    }

}
