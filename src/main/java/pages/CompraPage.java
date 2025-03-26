package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class CompraPage extends BasePage {
    //Locators
    private By compraLabel = By.xpath("//span[contains(text(), 'Compra') and not(ancestor::select[@disabled])]");
    private By emailField = By.xpath("//input[@id='inputEmail']");
    private By telefonoField = By.xpath("//input[@id='telefonoComprador']");
    private By cardRadioInput = By.xpath("//input[@id='datosPago_cdgoFormaPago_tarjetaRedSys']");
    private By cardRadioLabel = By.xpath("//span[@for='radiobutton']");
    private By newBankCard = By.cssSelector("button.target-puntos-renfe.selecTarjeta");

    //Constructor
    public CompraPage(WebDriver webDriver) {
        super(webDriver); //Calls to the constructor from parent class and their variable
        this.webDriver = webDriver; //Current instance
    }

    //Methods
    /**
     * Assert que estoy en la Page y esta habilitada “Personaliza tu viaje”
     */
    public void verifyYouAreInCompraPage() {
        waitUntilElementIsDisplayed(compraLabel, Duration.ofSeconds(5));
        WebElement element = webDriver.findElement(compraLabel);
        boolean labelDisplayed = element.isDisplayed();
        boolean labelEnabled = element.isEnabled();
        Assert.assertTrue("Compra", labelDisplayed);
        Assert.assertTrue("Compra", labelEnabled);
    }

    /**
     * type the E-mail in the textbox on the "Compra" page.
     *
     * @param email as a string
     */
    public void typeEmail(String email){
        waitUntilElementIsDisplayed(emailField, Duration.ofSeconds(5));
        setElementText(emailField, email);
    }

    /**
     * type the Phone in the textbox on the "Introduce tus datos" page.
     *
     * @param phone as a string
     */
    public void writePhoneField(String phone) {
        waitUntilElementIsDisplayed(telefonoField, Duration.ofSeconds(5));
        setElementText(telefonoField, phone);
    }

    /**
     * Marks the "Bank card" radio button as selected or unselected in the "Compra" page
     *
     * @param expectedSelected boolean with the expected selected state of the element
     */
    public void setCardRadioButtonSelected(boolean expectedSelected) {
        waitUntilElementIsDisplayed(cardRadioLabel, Duration.ofSeconds(5));
        setElementSelected(cardRadioInput, cardRadioLabel, expectedSelected);
    }

    /**
     * Clicks in new card in the Compra page.
     */
    public void clickNewBankCard() {
        waitUntilElementIsDisplayed(newBankCard, Duration.ofSeconds(5));
        scrollElementIntoView(newBankCard);
        clickElement(newBankCard);
    }

    ///**
    // * clicar en checkbox condiciones de venta
    // */
    //DEMOQA FORM

    ///**
    // * Comprobar el precio del billete
    // */
    //assertequals al metodo seleccionarTuViajePage.verifyTotalPrice();

    ///**
    // * clic en button continuar con la compra
    // */
    //demo qa click button

}