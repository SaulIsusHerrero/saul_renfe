package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class PasarelaPagoPage extends BasePage {
    //Locators
    private By trainAvailable = By.cssSelector("div[id^='precio-viaje']:not(:has(div))");
    private By seleccionaTuViajeLabel = By.xpath("//span[contains(text(), 'Selecciona tu viaje') and not(ancestor::select[@disabled])]");

    //Variables
    private BasePage basePage;
    WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

    //Constructor
    public PasarelaPagoPage(WebDriver webDriver) {
        super(webDriver); //Calls to the constructor from parent class and their variable
        this.webDriver = webDriver; //Current instance
    }

    //Methods
    /**
     * Assert que estoy en la Page y esta habilitada “Personaliza tu viaje”
     */
    public void verifyYouAreInPasarelaPagoPage() {
        //comprobar redsys getURL
        //Assert.assertTrue("Selecciona tu viaje", labelDisplayed);
        //Assert.assertTrue("Selecciona tu viaje", labelEnabled);
    }

    /**
     * Assert al importe
     */
    //mismo

    /**
     * Introducir numero tarjeta
     */
    //DEMOQA FORM

    /**
     * Introducir fecha caducidad
     */
    //DEMOQA FORM

    /**
     * Introducir CVV
     */
    //DEMO QA FORM

    /**
     * clicar en boton pagar
     */
    //click

    /**
     * Assert aparece el pop de error esperodo de tarjeta no soportada (RS18)
     */
    //assertTrue

}