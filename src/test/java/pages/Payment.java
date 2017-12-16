package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static jdk.nashorn.internal.objects.NativeString.trim;

public class Payment {
    Condition clickable = and("can be clicked", visible, enabled);

    String email = "ryanairbugs@protonmail.com";
    String password = "P@ssw0rd";

    String cardType = "MasterCard";
    String cardHolderName = "Ryanair Bug";
    String billingAddressLine1 = "Address 1";
    String billingAddressLine2 = "Address 2";
    String billingCity = "Dublin";
    String billingPostcode = "12345";


    private SelenideElement blockLoginRegister = $("div.login-register");
    private SelenideElement btnLogin = $("button.core-btn-secondary");
    private SelenideElement popupSignupLogin = $("signup-home-form");
    private SelenideElement inputEmail = $("input[type='email']");
    private SelenideElement inputPassword = $("input[type='password']");
    private SelenideElement btnSubmitLogin = $("button-spinner > button.core-btn-primary");
    private SelenideElement formPassengersDetails = $("div.passengers-form");
    private SelenideElement inputFirstAdultPassengerName = $$("input[placeholder='e.g. John']").first();
    private SelenideElement inputSecondAdultPassengerName = $$("input[placeholder='e.g. John']").get(1);
    private SelenideElement inputChildPassengerName = $$("input[placeholder='e.g. John']").get(2);

    private SelenideElement titlePaymentMethod = $("h4.payment-methods-title");

    private SelenideElement inputCardNumber = $(By.name("cardNumber"));

    private SelenideElement selectCardType = $(By.name("cardType"));
    private SelenideElement selectExpiryMonth = $(By.name("expiryMonth"));
    private SelenideElement selectExpiryYear = $(By.name("expiryYear"));
    private SelenideElement inputSecurityCode = $(By.name("securityCode"));
    private SelenideElement inputCardHolderName = $(By.name("cardHolderName"));

    private SelenideElement formBillingAddress = $("address-form");
    private SelenideElement inputAddressLine1 = $("#billingAddressAddressLine1");
    private SelenideElement inputAddressLine2 = $("#billingAddressAddressLine2");
    private SelenideElement inputCity = $("#billingAddressCity");
    private SelenideElement inputPostCode = $("#billingAddressPostcode");

    private SelenideElement checkboxAcceptPolicy = $("#checkout > div > form > div.main-area > div.core-card.available-step.after-pax-validation-step > div.body > div.cta > div > label > span");
    private SelenideElement btnPayNow = $("button[translate='common.components.payment_forms.pay_now']");

    private SelenideElement messagePaymentDeclinedError = $("prompt.error.prompt-text[text='common.components.payment_forms.error_explain_declined']");
    private SelenideElement titleErrorMessage = $("div.info-title");
    String textMessagePaymentDeclinedError = "As your payment was not authorised we could not complete your reservation. Please ensure that the information was correct or use a new payment to try again";
    String textTitleErrorMessage = "Oh. There was a problem";



    public Payment login() {
        blockLoginRegister.shouldBe(visible);
        btnLogin.shouldBe(visible).click();
        popupSignupLogin.shouldBe(visible);
        inputEmail.shouldBe(clickable).click();
        inputEmail.setValue(email);
        inputPassword.shouldBe(clickable).click();
        inputPassword.setValue(password);
        btnSubmitLogin.shouldBe(clickable);
        btnSubmitLogin.shouldBe(clickable);
        btnSubmitLogin.click();
        return this;
    }

    public Payment enterFirstAdultPassengerData() {
        formPassengersDetails.shouldBe(visible);
        inputFirstAdultPassengerName.shouldBe(visible).click();
        inputFirstAdultPassengerName.sendKeys(Keys.ARROW_DOWN);
        inputFirstAdultPassengerName.pressEnter();
        return this;
    }

    public Payment enterSecondAdultPassengerData() {
        inputSecondAdultPassengerName.shouldBe(clickable).click();
        inputSecondAdultPassengerName.pressEnter();
        return this;
    }

    public Payment enterChildPassengerData() {
        inputChildPassengerName.shouldBe(clickable).click();
        inputChildPassengerName.pressEnter();
        return this;
    }

    public Payment enterCardDetails(String cardNumber, String validityDate, String CVV) {

        String first4DigitsCardNumber = trim(cardNumber.substring(0, cardNumber.length() - 15));
        String second4DigitsCardNumber = trim(cardNumber.substring(5, cardNumber.length() - 10));
        String third4DigitsCardNumber = trim(cardNumber.substring(10, cardNumber.length() - 5));
        String fourth4DigitsCardNumber = (trim(cardNumber.substring(cardNumber.length() - 5)));

        titlePaymentMethod.shouldBe(visible).scrollTo();

        inputCardNumber.shouldBe(clickable).click();
        inputCardNumber.clear();
        inputCardNumber.sendKeys(first4DigitsCardNumber);
        inputCardNumber.sendKeys(second4DigitsCardNumber);
        inputCardNumber.sendKeys(third4DigitsCardNumber);
        inputCardNumber.sendKeys(fourth4DigitsCardNumber);

        selectCardType.shouldBe(clickable).click();
        selectCardType.selectOptionContainingText(cardType);

        selectExpiryMonth.shouldBe(clickable).click();
        selectExpiryMonth.selectOptionContainingText(validityDate.substring(0, validityDate.length() - 3));

        selectExpiryYear.shouldBe(clickable).click();
        selectExpiryYear.selectOptionContainingText("20"+validityDate.substring(3));

        inputSecurityCode.shouldBe(clickable).click();
        inputSecurityCode.setValue(CVV);

        inputCardHolderName.shouldBe(clickable).click();
        inputCardHolderName.setValue(cardHolderName);
        return this;

    }

    public Payment enterAddressDetails() {
        formBillingAddress.shouldBe(visible).scrollTo();

        inputAddressLine1.shouldBe(clickable).click();
        inputAddressLine1.setValue(billingAddressLine1);

        inputAddressLine2.shouldBe(clickable).click();
        inputAddressLine2.setValue(billingAddressLine2);

        inputCity.shouldBe(clickable).click();
        inputCity.setValue(billingCity);

        inputPostCode.shouldBe(clickable).click();
        inputPostCode.setValue(billingPostcode);
        return this;

    }

    public Payment pay() {
        checkboxAcceptPolicy.shouldBe(clickable).click();
        btnPayNow.shouldBe(clickable).click();
        return this;

    }

    public Payment checkMessageWithDeclinedPayment() {
        titlePaymentMethod.shouldBe(visible).scrollTo();
        titleErrorMessage.shouldBe(visible).shouldHave(text(textTitleErrorMessage));
        messagePaymentDeclinedError.shouldBe(visible).shouldHave(text(textMessagePaymentDeclinedError));
        return this;
    }

}
