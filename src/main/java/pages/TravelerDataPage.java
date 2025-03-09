package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TravelerDataPage extends BasePage {

    public TravelerDataPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By nameInput = By.xpath("//input[@id='nombre0']");
    private By firstSurnameInput = By.xpath("//input[@id='apellido10']");
    private By secondSurnameInput = By.xpath("//input[@id='apellido20']");
    private By dniInput = By.xpath("//input[@id='documento0']");
    private By emailInput = By.xpath("//input[@id='email0']");
    private By phoneInput = By.xpath("//input[@id='telefono0']");
    private By personalizeTravelButton = By.xpath("//button[normalize-space()='Personalizar viaje']");
    private By followPurchaseButton = By.xpath("//button[normalize-space()='Continúa con la compra']");
    private By emailPaymentInput = By.xpath("//input[@id='inputEmail']");
    private By phonePaymentInput = By.xpath("//input[@id='telefonoComprador']");
    private By cardRadioButtonLabel = By.xpath("//div[@id='headingOne']");
    private By cardRadioButtonInput = By.xpath("//input[@id='datosPago_cdgoFormaPago_tarjetaRedSys']");
    private By newCardButton = By.xpath("//span[contains(text(), 'Nueva Tarjeta')]");
    private By privacyPolicyCheckBoxLabel = By.xpath("//label[@for='checkbox-1']");
    private By privacyPolicyCheckBoxInput = By.xpath("//input[@id='aceptarCondiciones']");
    private By finnishPurchaseButton = By.xpath("//button[normalize-space()='Finaliza tu compra']");
    private By dataCompleted = By.xpath("//div[@id='check-circle-green-v10']");

    //Variables
    private ConfirmPurchasePage confirmPurchasePage;

    // Methods
    /**
     * Types the first surnamne in the Traveler Data page
     * @param name String with the traveler´s name
     */
    public void typeName(String name){
        confirmPurchasePage.waitUntilElementIsDisplayed(nameInput, 1000);
        setElementText(nameInput, name);
    }

    /**
     * Types the first surname in the Traveler Data page
     * @param firstSurname String with the traveler´s first surname
     */
    public void typeFirstSurnamne(String firstSurname){
        setElementText(firstSurnameInput, firstSurname);
    }

    /**
     * Types the second surname in the Traveler Data page
     * @param secondSurname String with the traveler´s second surname
     */
    public void typeSecondSurnamne(String secondSurname){
        setElementText(secondSurnameInput, secondSurname);
    }

    /**
     * Types the DNI in the Traveler Data page
     * @param dni String with the traveler´s dni
     */
    public void typeDni(String dni){
        setElementText(dniInput, dni);
    }

    /**
     * Types the e-mail in the Traveler Data page
     * @param email String with the traveler´s e-mail
     */
    public void typeEmail(String email){
        setElementText(emailInput, email);
    }

    /**
     * Types the phone number in the Traveler Data page
     * @param phone String with the traveler´s phone number
     */
    public void typePhone(String phone){
        setElementText(phoneInput, phone);
    }

    /**
     * Clicks the "Personalize my trip" button in the Traveler Data page
     */
    public void clickPersonalizeTravelButton() {
        confirmPurchasePage.waitUntilElementIsDisplayed(personalizeTravelButton, 5000);
        scrollElementIntoView(personalizeTravelButton);
        clickElement(personalizeTravelButton);
    }

    /**
     * Clicks the "Follow with the purchase" in the Traveler Data page
     */
    public void clickFollowPurchaseButton() {
        confirmPurchasePage.waitUntilElementIsDisplayed(followPurchaseButton, 5000);
        scrollElementIntoView(followPurchaseButton);
        clickElement(followPurchaseButton);
    }

    /**
     * Types the e-mail for the payment in the Traveler Data page
     * @param emailPayment String with the traveler´s phone number
     */
    public void typeEmailPayment(String emailPayment){
        setElementText(emailPaymentInput, emailPayment);
    }

    /**
     * Types the phone number in the Traveler Data page
     * @param phonePayment String with the traveler´s phone number
     */
    public void typePhonePayment(String phonePayment){
        setElementText(phonePaymentInput, phonePayment);
    }

    /**
     * Marks the Bank card radio button as selected or unselected
     * @param expectedSelected boolean with the expected selected state of the element
     */
    public void cardRadioButtonSelected(boolean expectedSelected) {
        confirmPurchasePage.waitUntilElementIsDisplayed(cardRadioButtonLabel, 5000);
        setElementSelected(cardRadioButtonInput, cardRadioButtonLabel, expectedSelected);
    }

    /**
     * Clicks new card bank in the Traveler Data page
     */
    public void clickNewCard() {
        confirmPurchasePage.waitUntilElementIsDisplayed(newCardButton, 5000);
        scrollElementIntoView(newCardButton);
        clickElement(newCardButton);
    }

    /**
     * Marks the Privacy Policy checkbox as selected or unselected in the Traveler Data page
     *
     * @param expectedSelected boolean with the expected selected state of the element, for example "true" or "false"
     */
    public void setCheckboxSelectedPP(boolean expectedSelected) {
        confirmPurchasePage.waitUntilElementIsDisplayed(privacyPolicyCheckBoxLabel, 5000);
        setElementSelected(privacyPolicyCheckBoxInput, privacyPolicyCheckBoxLabel, expectedSelected);
    }

    /**
     * Clicks the Complete your purchase button in the Traveler Data page
     */
    public void clickCompletePurchaseButton() {
        confirmPurchasePage.waitUntilElementIsDisplayed(finnishPurchaseButton, 5000);
        scrollElementIntoView(finnishPurchaseButton);
        clickElement(finnishPurchaseButton);
    }

    /**
     * Returns "true" or "false" depending on if the Data page checkbox is currently in green that
     * means all the mandatory data is completed
     */
    public boolean isDataCompletedCheckboxGreen() {
        confirmPurchasePage.waitUntilElementIsDisplayed(dataCompleted, 5000);
        return isElementSelected(dataCompleted);
    }

}

