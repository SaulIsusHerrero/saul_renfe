package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage extends BasePage {
    //Constructor
    public HomePage(WebDriver driver) {
        super(driver); //Calls to the constructor or methods from parent class
        this.webDriver = webDriver; //Current class instance
        PageFactory.initElements(webDriver, this); //Initialize the elements from a page in Page Object Model (POM)
    }

    // Locators
    private By originInput = By.xpath("//input[@id='origin']");
    private By destinationInput = By.xpath("//input[@id='destination']");
    private By dateDepartureInput = By.xpath("//input[@id='first-input']");
    private By onlyDepartureRadioButtonLabel = By.xpath("//label[@for='trip-go']");
    private By onlyDepartureRadioButtonInput = By.xpath("//input[@id='trip-go']");
    private By acceptButton = By.xpath("//button[contains(text(),'Aceptar')]");



    //Variables
    private WebDriver driver;

    // Methods
    /**
     * Chooses the trip origin in the Home page.
     * @param origin
     */
    public void enterOrigin(String origin) {
        //waitUntilElementIsDisplayed(originInput, 1000);
        setElementText(originInput, origin);
    }
    /**
     * Types the trip destination in the Home page.
     * @param destination String with the destination
     */
    public void enterDestination(String destination){
        //waitUntilElementIsDisplayed(destinationInput, 1000);
        setElementText(destinationInput, destination);
    }

    /**
     * clicks on the departure date calendar in the Home page
     */
    public void selectDepartureDate() {
        //waitUntilElementIsDisplayed(, 1000);
        clickElement(dateDepartureInput);
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
        //Waits and finds the 'Accept' button.
        List<WebElement> botones = webDriver.findElements(acceptButton);
        System.out.println("Number of buttons found: " + botones.size());

        if (!botones.isEmpty()) {
            WebElement acceptButtonElement = botones.get(0); // clicks the 1st button found it.

            // Scrolls till the button.
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", acceptButtonElement);
            WebElement acceptButton = new WebDriverWait(webDriver, Duration.ofSeconds(10)).
                    until(ExpectedConditions.elementToBeClickable(this.acceptButton));
            // Attempt to click with Selenium.
            try {
                acceptButton.click();
            } catch (Exception e) {
                System.out.println("Could not click with Selenium, trying with JavaScript...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", acceptButton);
            }

            System.out.println("Accept' button on the calendar clicked successfully.");
        } else {
            System.out.println("The 'Accept' button was not found. Verify the XPath.");
        }
    }

    /**
     * Searches the selected ticket in the Home page.
     */
    public void clickSearchTicketButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Localizador del botón "Buscar billete"
        By searchButtonLocator = By.xpath("//button[normalize-space()='Buscar billete']");

        try {
            // Esperar a que el botón sea visible y clickeable
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(searchButtonLocator));

            // Intentar hacer clic con Selenium
            searchButton.click();
            System.out.println("Clic realizado en 'Buscar billete' con Selenium.");

        } catch (ElementClickInterceptedException e) {
            System.out.println("El botón 'Buscar billete' está bloqueado. Intentando con JavaScript...");

            // Si el clic es interceptado, usar JavaScriptExecutor
            WebElement searchButton = driver.findElement(searchButtonLocator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", searchButton);
            System.out.println("Clic realizado en 'Buscar billete' con JavaScript.");

        } catch (TimeoutException e) {
            System.out.println("El botón 'Buscar billete' no fue encontrado en el tiempo esperado.");
        }

        // Esperar un momento y verificar si la URL cambia
        try {
            Thread.sleep(3000); // Espera breve antes de verificar la URL
            System.out.println("URL actual después del clic: " + driver.getCurrentUrl());

            // Si la URL no cambia, intentar enviar el formulario manualmente
            if (driver.getCurrentUrl().contains("renfe.com/es/es")) {
                System.out.println("La URL no cambió. Intentando enviar el formulario con JavaScript...");
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("document.querySelector('form').submit();");
            }

            // Esperar a que la nueva URL se cargue
            wait.until(ExpectedConditions.urlContains("buscarTrenEnlaces.do"));
            System.out.println("Página redirigida correctamente a la búsqueda de trenes.");

        } catch (TimeoutException e) {
            System.out.println("La URL no cambió a la página de resultados en el tiempo esperado.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
