package pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SeleccionarTuViajePage extends BasePage {
    //Locators
    private By trainAvailable = By.cssSelector("div[id^='precio-viaje']:not(:has(div))");
    private By fareTrainBasic = By.xpath("(//div[@role='button' and @data-titulo-tarifa='Básico'])[1]");
    private By selectDayRightArrow = By.xpath("//button[contains(@class, 'move_to_tomorrow') and @aria-label='Ir a día siguiente']");
    private By seleccionaTuViajeLabel = By.xpath("//span[contains(text(), 'Selecciona tu viaje') and not(ancestor::select[@disabled])]");
    private By totalPriceLocator = By.xpath("//div[contains(@class, 'total-price')]");
    private By selectButtonLocator = By.xpath("//div[@class='select-more' and @id='btnSeleccionar' and @role='button' and @tabindex='0' and @title='Elegir el trayecto y pasar al siguiente paso']");
    private By noContinueLinkLocator = By.xpath("//button[contains(text(), 'No, quiero continuar con Básico')]");
    private By popUpLocator = By.xpath("//div[contains(@class, 'popup-content')]");
    private By travelerLocator = By.xpath("//span[@class='viajero-lista']");
    private By fareLocator = By.xpath("//div[@role='button' and @data-titulo-tarifa='Básico']");
    //Variables
    private BasePage basePage;

    //Constructor
    public SeleccionarTuViajePage(WebDriver webDriver) {
        super(webDriver); //Calls to the constructor from parent class and their variable
        this.webDriver = webDriver; //Current instance
    }

    //Methods

    /**
     * Checks if we are in the next Page "SeleccionarTuViajePage".
     */
    public void verifyYouAreInSelecionaTuViaje() {
        waitUntilElementIsDisplayed(seleccionaTuViajeLabel, timeout);
        WebElement element = webDriver.findElement(seleccionaTuViajeLabel);
        boolean labelDisplayed = element.isDisplayed();
        boolean labelEnabled = element.isEnabled();
        Assert.assertTrue("Selecciona tu viaje", labelDisplayed);
        Assert.assertTrue("Selecciona tu viaje", labelEnabled);
    }

    /**
    * Encuentra el primer tren disponible en el primer día posible
    */
    public void selectFirstTrainAvailable() {
        boolean control = true;

        while (control) {
            // Encuentra la lista de trenes disponibles
            List<WebElement> trainList = webDriver.findElements(trainAvailable);
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

            if (!trainList.isEmpty()) {
                // Haz clic en el primer tren disponible
                WebElement firstTrain = webDriver.findElement(trainAvailable);
                wait.until(ExpectedConditions.visibilityOf(firstTrain));
                scrollElementIntoViewElement(firstTrain);
                wait.until(ExpectedConditions.elementToBeClickable(firstTrain));
                Actions actions = new Actions(webDriver);
                actions.moveToElement(firstTrain).click().perform();
                control = false;
            } else {
                // Haz clic en el botón del siguiente día para buscar trenes disponibles
                WebElement nextDayButton = webDriver.findElement(selectDayRightArrow);
                nextDayButton.click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(trainAvailable));
            }
       }
       // Verify that the train list is not empty
       List<WebElement> finalTrainList = webDriver.findElements(trainAvailable);
       Assert.assertTrue("No trains available", !finalTrainList.isEmpty());
    }

    /**
     * Clicks the Basic fare button in the Results page.
     */
    public void clickFareApplied() {
        waitUntilElementIsDisplayed(fareTrainBasic, 5000);
        scrollElementIntoView(fareTrainBasic);
        clickElement(fareTrainBasic);
    }

    /**
     * Verifies the number of travelers for the trip
     * @return true if is 1 traveler or is false this one.
     */
    public boolean verifyNumberOfTravelers(boolean b) {
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
    public boolean verifyFareIsBasic(boolean b) {
        By fareLocator = By.xpath("//div[@role='button' and @data-titulo-tarifa='Básico']");
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement fareElement = wait.until(ExpectedConditions.visibilityOfElementLocated(fareLocator));
        String fareText = fareElement.getAttribute("data-titulo-tarifa");
        return fareText.equals("Básico");
    }

}