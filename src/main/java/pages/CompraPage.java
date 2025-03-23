package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class CompraPage extends BasePage {
    //Locators
    private By trainAvailable = By.cssSelector("div[id^='precio-viaje']:not(:has(div))");
    private By seleccionaTuViajeLabel = By.xpath("//span[contains(text(), 'Selecciona tu viaje') and not(ancestor::select[@disabled])]");

    //Variables
    private BasePage basePage;
    WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

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
        waitUntilElementIsDisplayed(seleccionaTuViajeLabel, Duration.ofSeconds(5));
        WebElement element = webDriver.findElement(seleccionaTuViajeLabel);
        boolean labelDisplayed = element.isDisplayed();
        boolean labelEnabled = element.isEnabled();
        Assert.assertTrue("Selecciona tu viaje", labelDisplayed);
        Assert.assertTrue("Selecciona tu viaje", labelEnabled);
    }

    /**
     * Rellena email
     */
    //DEMOQA FORM

    /**
     * Rellena telefono
     */
    //DEMOQA FORM

    /**
     * Radiobutton pago con tarjeta bancaria
     */
    //DEMOQA FORM

    /**
     * clicar en nueva tarjeta
     */
    //iGUAL a metodo clic en fare

    /**
     * clicar en checkbox condiciones de venta
     */
    //DEMOQA FORM

    /**
     * Comprobar el precio del billete
     */
    //assertequals al metodo seleccionarTuViajePage.verifyTotalPrice();

    /**
     * clic en button continuar con la compra
     */
    //demo qa click button

}