package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class IntroduceTusDatosPage extends BasePage {
    //Locators
    private By introduceTusDatosLabel = By.xpath("//span[contains(text(), 'Introduce tus datos') and not(ancestor::select[@disabled])]");
    private By firstNameField = By.xpath("//input[@id='nombre0']");
    private By firstSurnameField = By.xpath("//input[@id='apellido10']");
    private By secondSurnameField = By.xpath("//input[@id='apellido20']");
    private By dniField = By.xpath("//input[@id='documento0']");
    private By telefonoField = By.xpath("//input[@id='telefono0']");
    private By emailField = By.xpath("//input[@id='email0']");
    private By totalPriceDataLocator = By.xpath("//span[@id='totalTrayecto' and normalize-space()='43,85 €']");
    private By btnPersonalizar = By.cssSelector("#btn-responsive > #submitpersonaliza");

    //Variables
    private BasePage basePage;
    private SeleccionarTuViajePage seleccionarTuViajePage;
    WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

    //Constructor
    public IntroduceTusDatosPage(WebDriver webDriver) {
        super(webDriver); //Calls to the constructor from parent class and their variable
        this.webDriver = webDriver; //Current instance
    }

    //Methods
    /**
     * Assert que estoy en la Page y esta habilitada “introduce tus datos”
     */
    public void verifyYouAreInIntroduceYourDataPage() {
        waitUntilElementIsDisplayed(introduceTusDatosLabel, Duration.ofSeconds(5));
        WebElement element = webDriver.findElement(introduceTusDatosLabel);
        boolean labelDisplayed = element.isDisplayed();
        boolean labelEnabled = element.isEnabled();
        Assert.assertTrue("Introduce tus datos", labelDisplayed);
        Assert.assertTrue("Introduce tus datos", labelEnabled);
    }

    /**
     * type the name in the textbox on the "Introduce tus datos" page.
     *
     * @param firstName as a string
     */
    public void writeFirstNameField(String firstName) {
        waitUntilElementIsDisplayed(firstNameField, Duration.ofSeconds(5));
        setElementText(firstNameField, firstName);
    }

    /**
     * type the first surname in the textbox on the "Introduce tus datos" page.
     *
     * @param primerApellido as a string
     */
    public void writeFirstSurnameField(String primerApellido) {
        waitUntilElementIsDisplayed(firstSurnameField, Duration.ofSeconds(5));
        setElementText(firstSurnameField, primerApellido);
    }

    /**
     * type the second surname in the textbox on the "Introduce tus datos" page.
     *
     * @param segundoApellido as a string
     */
    public void writeSecondSurnameField(String segundoApellido) {
        waitUntilElementIsDisplayed(secondSurnameField, Duration.ofSeconds(5));
        setElementText(secondSurnameField, segundoApellido);
    }

    /**
     * type the DNI in the textbox on the "Introduce tus datos" page.
     *
     * @param dni as a string
     */
    public void writeDNIField(String dni) {
        waitUntilElementIsDisplayed(dniField, Duration.ofSeconds(5));
        setElementText(dniField, dni);
    }

    /**
     * type the E-mail in the textbox on the "Introduce tus datos" page.
     *
     * @param email as a string
     */
    public void writeEmailField(String email) {
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
     * Obtener el precio de total del billete en "Introduce tus datos" Page
     */
    public boolean verifyTotalPriceData(){
        waitUntilElementIsDisplayed(totalPriceDataLocator, Duration.ofSeconds(5));
        WebElement totalPriceElementData = webDriver.findElement(totalPriceDataLocator);
        boolean labelDisplayedTotalPriceData = totalPriceElementData.isDisplayed();
        Assert.assertTrue(labelDisplayedTotalPriceData);
        return labelDisplayedTotalPriceData;
    }

    /**
    * Comprobar el precio del billete
    */
    public boolean verifyTotalPriceBetweenPages() {
        boolean priceSelect = true;
        if (priceSelect) {
            Assert.assertTrue(verifyTotalPriceData());
            return true;
        } else {
            System.out.println("prices between select trip and data traveler are not equals");
            return false;
        }
    }

    /**
     * Clic "Personalizar viaje" button
     */
    public void clicPersonalizeTrip() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        WebElement clickPersonalizar = webDriver.findElement(btnPersonalizar);
        wait.until(ExpectedConditions.visibilityOf(clickPersonalizar));
        wait.until(ExpectedConditions.elementToBeClickable(clickPersonalizar));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(clickPersonalizar).click().perform();
    }

}