package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SeleccionarTuViajePage extends BasePage {
    //Locators
    //private By availableTrain = By.cssSelector("span[aria-label='Pulse espacio o enter para tener más detalle de las tarifas disponibles.']:nth-of-type(1)");
    //private By availableTrain = By.xpath("(//span[@aria-label='Pulse espacio o enter para tener más detalle de las tarifas disponibles.'])[1]");
    private By fareTrainBasic = By.xpath("(//div[@role='button' and @data-titulo-tarifa='Básico'])[1]");
    private By selectDayRightArrow = By.xpath("//button[contains(@class, 'move_to_tomorrow') and @aria-label='Ir a día siguiente']");
    private By seleccionaTuViajeLabel = By.xpath("//span[contains(text(), 'Selecciona tu viaje') and not(ancestor::select[@disabled])]");
    private By totalPriceLocator = By.xpath("//div[contains(@class, 'total-price')]");
    private By selectButtonLocator = By.xpath("//div[@class='select-more' and @id='btnSeleccionar' and @role='button' and @tabindex='0' and @title='Elegir el trayecto y pasar al siguiente paso']");
    private By noContinueLinkLocator = By.xpath("//button[contains(text(), 'No, quiero continuar con Básico')]");
    private By popUpLocator = By.xpath("//div[contains(@class, 'popup-content')]");
    //Variables
    private BasePage basePage;

    //Constructor
    public SeleccionarTuViajePage(WebDriver webDriver) {
        super(webDriver); //Calls to the constructor from parent class and their variable
        this.webDriver = webDriver; //Current instance
    }

    //Methods
    /**
     * Selects next days on the calendar until an available train is found.
     * @return true if there is an available train, false otherwise
     */
    public boolean selectNextDayUntilTrainsAvailable() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20)); // Aumenta el tiempo de espera
        WebElement train = null;

        try {
            train = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[aria-label=\"Pulse espacio o enter para tener más detalle de las tarifas disponibles\"]")));
        } catch (TimeoutException e) {
            System.out.println("Element not found within the timeout period.");
            return false;
        }

        if (train != null) {
            train.sendKeys(Keys.ENTER);
            WebElement nextDayButton = webDriver.findElement(selectDayRightArrow);
            do {
                if (train.isDisplayed()) {
                    train.click();
                } else {
                    nextDayButton.click();
                    try {
                        train = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[aria-label=\"Pulse espacio o enter para tener más detalle de las tarifas disponibles\"]")));
                    } catch (TimeoutException e) {
                        System.out.println("Element not found within the timeout period.");
                        return false;
                    }
                }
            } while (!train.isDisplayed());
        }
        return true;
    }

    /**
     * Clicks the Basic fare button in the Results page.
     */
    public void clickFareApplied() {
        waitUntilElementIsDisplayed(fareTrainBasic, 5000);
        clickElement(fareTrainBasic);
    }

    /**
     * Returns if we are in the "Seleccionar tu viaje" Page or not
     * @return "Seleccionar tu viaje" Page true or false
     */
    public boolean isSeleccionaTuViajeLabelEnabled() {
        WebElement seleccionaTuViaje = webDriver.findElement(seleccionaTuViajeLabel);
        return seleccionaTuViaje.isEnabled();
    }

    /**
     * Verifies the number of travelers for the trip
     * @return true if is 1 traveler or is false this one.
     */
    public boolean verifyNumberOfTravelers() {
        By travelerLocator = By.xpath("//span[@class='viajero-lista']");
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement travelerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(travelerLocator));
        String travelerText = travelerElement.getText();
        return travelerText.contains("Viajero 1");
    }

    /**
     * Verifies the fare chosen
     * @return true if is the Basic fare or not
     */
    public boolean verifyFareIsBasic() {
        By fareLocator = By.xpath("//div[@role='button' and @data-titulo-tarifa='Básico']");
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement fareElement = wait.until(ExpectedConditions.visibilityOfElementLocated(fareLocator));
        String fareText = fareElement.getAttribute("data-titulo-tarifa");
        return fareText.equals("Básico");
    }

    /**
     * Verifies if total price is equal to the fare basic price
     * @return true if the total price is the same that the fare price
     */
    public boolean verifyTotalPriceEqualsFarePrice() {
        // Click on the fare
        clickFareApplied();

        // Get the fare price
        WebElement fareElement = webDriver.findElement(fareTrainBasic);
        String farePriceText = fareElement.getText();
        double farePrice = Double.parseDouble(farePriceText.replace("€", "").replace(",", ".").trim());

        // Get the total price
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement totalPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(totalPriceLocator));
        String totalPriceText = totalPriceElement.getText();
        double totalPrice = Double.parseDouble(totalPriceText.replace("€", "").replace(",", ".").trim());

        return totalPrice == farePrice;
    }

    /**
     * Clicks on select button to finnish the search for the fi
     */
    public void clickSelectButton(){
        WebElement selectButton = webDriver.findElement(selectButtonLocator);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        selectButton.isEnabled();
        selectButton.click();
    }

    /**
     * Checks if "No, continue with the Basic fare" appears
     * @return true if the link appears or false
     */
    public boolean verifyNoContinueButtonAppears() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement noContinueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(noContinueLinkLocator));
        return noContinueButton.isDisplayed();
    }

    /**
     * Checks if the Pop-up to change the fare appears
     * @return true if the pop-up appears or false
     */
    public boolean verifyPopUpAppears() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement popUpElement = wait.until(ExpectedConditions.visibilityOfElementLocated(popUpLocator));
        return popUpElement.isDisplayed();
    }

    /**
     * Clicks continue button with the purchase
     * @return true if continue button appears or false
     */
    public boolean clickContinueButton() {
        WebElement continueButton = webDriver.findElement(selectButtonLocator);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        continueButton.click();
        return continueButton.isDisplayed();
    }

}