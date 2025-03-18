package pages;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static io.opentelemetry.sdk.metrics.internal.data.EmptyExponentialHistogramBuckets.get;

public class HomePage extends BasePage {
    // Locators
    public By originInputLocator = By.xpath("//input[@id='origin']");
    public By destinationInputLocator = By.xpath("//input[@id='destination']");
    public By deleteOriginArrowLocator = By.xpath("//button[@aria-label='Eliminar origen']");
    public By deleteDestinationArrowLocator = By.xpath("//button[@aria-label='Eliminar destino']");
    private By dateDepartureInput = By.xpath("//input[@id='first-input']");
    private By onlyDepartureRadioButtonLabel = By.xpath("//label[@for='trip-go']");
    private By onlyDepartureRadioButtonInput = By.xpath("//input[@id='trip-go']");
    private By acceptButtonLocator = By.xpath("//button[contains(text(),'Aceptar')]");
    private By buscarBilleteLocator = By.xpath("//button[normalize-space()='Buscar billete']");

    //Variables
    private WebDriver webDriver;

    //Constructor
    public HomePage(WebDriver webDriver) {
        super(webDriver); //Calls to the constructor or methods from parent class
        this.webDriver = webDriver; //Current class instance
    }

    // Methods
    public void enterOrigin(String origin) {
        WebElement originInput = webDriver.findElement(originInputLocator);

        // Ingresar el destino
        originInput.click();
        originInput.sendKeys(origin);
        originInput.sendKeys(Keys.DOWN);
        originInput.sendKeys(Keys.ENTER);

        // Limpiar el campo de origen si es necesario
        WebElement deleteOriginArrow = webDriver.findElement(deleteOriginArrowLocator);
        deleteOriginArrow.click();

        originInput.sendKeys(origin);
        originInput.sendKeys(Keys.DOWN);
        originInput.sendKeys(Keys.ENTER);
    }

    public void enterDestination(String destination) {
        WebElement destinationInput = webDriver.findElement(destinationInputLocator);

        // Ingresar el destino
        destinationInput.click();
        destinationInput.sendKeys(destination);
        destinationInput.sendKeys(Keys.DOWN);
        destinationInput.sendKeys(Keys.ENTER);

        // Limpiar el campo de origen si es necesario
        WebElement deleteDestinationArrow = webDriver.findElement(deleteDestinationArrowLocator);
        deleteDestinationArrow.click();

        destinationInput.sendKeys(destination);
        destinationInput.sendKeys(Keys.DOWN);
        destinationInput.sendKeys(Keys.ENTER);
    }

    /**
     * clicks on the departure date calendar in the Home page
     */
    public void selectDepartureDate() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(dateDepartureInput));
        button.click();
    }

    /**
     * Marks the "only go trip" radio button as selected or unselected.
     *
     * @param expectedSelected boolean with the expected selected state of the element
     */
    public void clickOnlyGoRadioButtonSelected(boolean expectedSelected) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(1));
        setElementSelected(onlyDepartureRadioButtonInput, onlyDepartureRadioButtonLabel, expectedSelected);
    }

    /**
     * Method to click the 'Accept' button on the calendar in Home page.
     */
    public void clickAcceptButton() {
        //Clicks the Accept button.
        scrollElementIntoView(acceptButtonLocator);
        List<WebElement> botonAceptarCalendario = webDriver.findElements(acceptButtonLocator);
        botonAceptarCalendario.get(0);
    }

    /**
     * Searches the selected ticket in the Home page.
     */
    public void clickSearchTicketButton() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        // Esperar a que el botón sea visible y clickeable
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(buscarBilleteLocator));
        // Intentar hacer clic con Selenium
        searchButton.click();
    }
}