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
    private By selectDayRightArrow = By.cssSelector("button.move_to_tomorrow");
    private By seleccionaTuViajeLabel = By.xpath("//span[contains(text(), 'Selecciona tu viaje') and not(ancestor::select[@disabled])]");
    private By travelerLocator = By.xpath("(//div[@class='rowitem1 viajerosSelected' and contains(., 'Total') and contains(., '(1) viajeros')])[1]");
    private By basicFareLocator = By.xpath("//div[@id='tarifai']//span[contains(text(), 'Básico')]");
    // TO DO : No usar precios porque varian entre ejecuciones diferentes.
    private By basicPriceLocator = By.xpath("//span[@class='viajero-lista' and @id='viajero_i_1' and text()='43,85€']");
    private By totalPriceLocator = By.xpath("//div[@class='rowitem1 d-none d-lg-inline d-xl-inline d-md-inline' and contains(., 'Precio Total') and contains(., '43,85€')]");
    private By linkContinueSameFare = By.cssSelector("p#aceptarConfirmacionFareUpgrade.link-fareUpg");
    private By btnSeleccionar = By.cssSelector("div.select-more[id='btnSeleccionar'][role='button'][title='Elegir el trayecto y pasar al siguiente paso']");
    private By closeConfirmacionFareUpgrade = By.cssSelector("button#closeConfirmacionFareUpgrade.close.modalClose-promoUp");

    //Variables
    private BasePage basePage;
    WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

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
        waitUntilElementIsDisplayed(seleccionaTuViajeLabel, Duration.ofSeconds(5));
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
                wait.until(ExpectedConditions.elementToBeClickable(firstTrain));
                Actions actions = new Actions(webDriver);
                scrollElementIntoViewElement(firstTrain);
                actions.moveToElement(firstTrain).click();
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
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        clickElement(fareTrainBasic);
    }

    /**
     * Verifies the number of travelers for the trip
     */
    public void verifyNumberOfTravelers() {
        waitUntilElementIsDisplayed(travelerLocator, Duration.ofSeconds(10));
        // Verificar que el locator contiene el número "1"
        String locatorString = travelerLocator.toString();
        Assert.assertTrue(locatorString.contains("1"));
    }

    public void verifyFareIsBasic(){
        waitUntilElementIsDisplayed(basicFareLocator, Duration.ofSeconds(5));
        WebElement basicFareElement = webDriver.findElement(basicFareLocator);
        boolean labelDisplayed = basicFareElement.isDisplayed();
        Assert.assertTrue(labelDisplayed);
    }

    public boolean verifyFarePrice(){
        waitUntilElementIsDisplayed(basicPriceLocator, Duration.ofSeconds(5));
        WebElement basicFarePriceElement = webDriver.findElement(basicPriceLocator);
        boolean labelDisplayedFarePrice = basicFarePriceElement.isDisplayed();
        Assert.assertTrue(labelDisplayedFarePrice);
        return labelDisplayedFarePrice;
    }

    public boolean verifyTotalPriceSelect(){
        waitUntilElementIsDisplayed(totalPriceLocator, Duration.ofSeconds(5));
        WebElement totalPriceElement = webDriver.findElement(totalPriceLocator);
        boolean labelDisplayedTotalPrice = totalPriceElement.isDisplayed();
        Assert.assertTrue(labelDisplayedTotalPrice);
        return labelDisplayedTotalPrice;
    }

    public void verifyFareAndTotalPricesAreEquals(){
        Assert.assertEquals(verifyFarePrice(),verifyTotalPriceSelect());
    }

    public void clickSelectButton(){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        WebElement clickSeleccionar = webDriver.findElement(btnSeleccionar);
        wait.until(ExpectedConditions.visibilityOf(clickSeleccionar));
        wait.until(ExpectedConditions.elementToBeClickable(clickSeleccionar));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(clickSeleccionar).click().perform();
    }

    public boolean popUpFareAppears(){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        WebElement popUpChangeFare = webDriver.findElement(closeConfirmacionFareUpgrade);
        boolean popUpChangeFareAppears = popUpChangeFare.isDisplayed();
        return popUpChangeFareAppears;
    }

    public void verifyPopUpFareAppears(){
        Assert.assertTrue(popUpFareAppears());
    }

    public boolean linkContinueSameFareAppears(){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        WebElement linkContinueSameFareElement = webDriver.findElement(linkContinueSameFare);
        boolean linkContinueSameFareAppears = linkContinueSameFareElement.isDisplayed();
        return linkContinueSameFareAppears;
    }

    public void verifyLinkContinueSameFare(){
        Assert.assertTrue(linkContinueSameFareAppears());
    }

    public void clickLinkContinueSameFare(){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        WebElement clickContinueSameFare = webDriver.findElement(linkContinueSameFare);
        wait.until(ExpectedConditions.visibilityOf(clickContinueSameFare));
        wait.until(ExpectedConditions.elementToBeClickable(linkContinueSameFare));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(clickContinueSameFare).click().perform();
    }

}