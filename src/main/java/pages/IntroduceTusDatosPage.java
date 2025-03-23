package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;


public class IntroduceTusDatosPage extends BasePage {
    //Locators
    private By trainAvailable = By.cssSelector("div[id^='precio-viaje']:not(:has(div))");
    private By seleccionaTuViajeLabel = By.xpath("//span[contains(text(), 'Selecciona tu viaje') and not(ancestor::select[@disabled])]");

    //Variables
    private BasePage basePage;
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
        waitUntilElementIsDisplayed(seleccionaTuViajeLabel, Duration.ofSeconds(5));
        WebElement element = webDriver.findElement(seleccionaTuViajeLabel);
        boolean labelDisplayed = element.isDisplayed();
        boolean labelEnabled = element.isEnabled();
        Assert.assertTrue("Selecciona tu viaje", labelDisplayed);
        Assert.assertTrue("Selecciona tu viaje", labelEnabled);
    }

    /**
     * Rellenar datos personales
     */
    //IGUAL FORM DEMOQA

    /**
     * Comprobar el precio del billete
     */
    //assertequals al metodo seleccionarTuViajePage.verifyTotalPrice();

}