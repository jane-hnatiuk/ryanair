package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.url;
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

    private SelenideElement checkboxAcceptPolicy = $(By.cssSelector("#checkout > div > form > div.main-area > div.core-card.available-step.after-pax-validation-step > div.body > div.cta > div > label > span"));
    private SelenideElement btnPayNow = $(By.cssSelector("button[translate='common.components.payment_forms.pay_now']"));

    private SelenideElement messagePaymentDeclinedError = $(By.cssSelector("prompt.error.prompt-text[text='common.components.payment_forms.error_explain_declined']"));
    private SelenideElement titleErrorMessage = $(By.cssSelector("div.info-title"));
    String textMessagePaymentDeclinedError = "As your payment was not authorised we could not complete your reservation. Please ensure that the information was correct or use a new payment to try again";
    String textTitleErrorMessage = "Oh. There was a problem";



    public Payment login() {
        url().equals(paymentURL);
        blockLoginRegister.waitUntil(visible, 6000);
        btnLogin.waitUntil(visible, 6000).click();
        popupSignupLogin.waitUntil(visible, 6000);
        inputEmail.waitUntil(clickable, 6000).click();
        inputEmail.clear();
        inputEmail.sendKeys(email);
        inputPassword.waitUntil(clickable, 6000).click();
        inputPassword.clear();
        inputPassword.sendKeys(password);
        btnSubmitLogin.waitUntil(clickable, 6000);
        btnSubmitLogin.shouldBe(clickable);
        btnSubmitLogin.click();
        return this;
    }

    public Payment enterFirstAdultPassengerData() {
        formPassengersDetails.waitUntil(visible, 6000);
        inputFirstAdultPassengerName.waitUntil(visible, 6000);
        inputFirstAdultPassengerName.click();
        inputFirstAdultPassengerName.sendKeys(Keys.ARROW_DOWN);
        inputFirstAdultPassengerName.pressEnter();
        return this;
    }

    public Payment enterSecondAdultPassengerData() {
        inputSecondAdultPassengerName.waitUntil(clickable, 6000);
        inputSecondAdultPassengerName.click();
        inputSecondAdultPassengerName.pressEnter();
        return this;
    }

    public Payment enterChildPassengerData() {
        inputChildPassengerName.waitUntil(clickable, 6000);
        inputChildPassengerName.click();
        inputChildPassengerName.pressEnter();
        return this;

    }

    public Payment enterCardDetails(String cardNumber, String validityDate, String CVV) {
        titlePaymentMethod.waitUntil(visible, 6000).scrollTo();

        inputCardNumber.waitUntil(clickable, 6000).click();
        inputCardNumber.clear();
        inputCardNumber.sendKeys(trim(cardNumber.substring(0, cardNumber.length() - 15)));
        inputCardNumber.sendKeys(trim(cardNumber.substring(5, cardNumber.length() - 10)));
        inputCardNumber.sendKeys(trim(cardNumber.substring(10, cardNumber.length() - 5)));
        inputCardNumber.sendKeys(trim(cardNumber.substring(cardNumber.length() - 5)));

        selectCardType.waitUntil(clickable, 6000).click();
        selectCardType.selectOptionContainingText(cardType);

        selectExpiryMonth.waitUntil(clickable, 6000).click();
        selectExpiryMonth.selectOptionContainingText(validityDate.substring(0, validityDate.length() - 3));

        selectExpiryYear.waitUntil(clickable, 6000).click();
        selectExpiryYear.selectOptionContainingText("20"+validityDate.substring(3));

        inputSecurityCode.waitUntil(clickable, 6000).click();
        inputSecurityCode.sendKeys(CVV);

        inputCardHolderName.waitUntil(clickable, 6000).click();
        inputCardHolderName.sendKeys(cardHolderName);
        return this;

    }

    public Payment enterAddressDetails() {
        formBillingAddress.waitUntil(visible, 6000).scrollTo();

        inputAddressLine1.waitUntil(clickable, 6000).click();
        inputAddressLine1.clear();
        inputAddressLine1.sendKeys(billingAddressLine1);

        inputAddressLine2.waitUntil(clickable, 6000).click();
        inputAddressLine2.clear();
        inputAddressLine2.sendKeys(billingAddressLine2);

        inputCity.waitUntil(clickable, 6000).click();
        inputCity.clear();
        inputCity.sendKeys(billingCity);

        inputPostCode.waitUntil(clickable, 6000).click();
        inputPostCode.click();
        inputPostCode.sendKeys(billingPostcode);
        return this;

    }

    public Payment pay() {
        checkboxAcceptPolicy.waitUntil(clickable, 6000).click();
        btnPayNow.waitUntil(clickable, 6000).click();
        return this;

    }

    public Payment checkMessageWithDeclinedPayment() {
        titlePaymentMethod.waitUntil(visible, 6000).scrollTo();
        titleErrorMessage.waitUntil(visible, 6000).shouldHave(text(textTitleErrorMessage));
        messagePaymentDeclinedError.waitUntil(visible, 6000).shouldHave(text(textMessagePaymentDeclinedError));
        return this;
    }

}
