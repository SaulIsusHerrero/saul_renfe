package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By originInput = By.xpath("//input[@id='origin']");
    private By destinationInput = By.xpath("//input[@id='destination']");
    private By dateDepatureInput = By.xpath("//input[@id='first-input']");
    private By onlyDepartureRadioButtonLabel = By.xpath("//label[@for='trip-go']");
    private By onlyDepatureRadioButtonInput = By.xpath("//input[@id='trip-go']");
    private By departureDayInput = By.xpath("//input[@id='first-input']");
    private By clickSearchTicketsButton= By.xpath("//button[normalize-space()='Buscar billete']");

    //Variables
    private ConfirmPurchasePage confirmPurchasePage;

    // Methods
    /**
     * Choose the trip origin in the Home page
     * @param origin
     */
    public void enterOrigin(String origin) {
        confirmPurchasePage.waitUntilElementIsDisplayed(originInput, 1000);
        setElementText(originInput, origin);
    }
    /**
     * Type the trip destination in the Home page
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
        clickElement(dateDepatureInput);
    }

    /**
     * Marks the "only go trip" radio button as selected or unselected
     *
     * @param expectedSelected boolean with the expected selected state of the element
     */
    public void clickOnlyGoRadioButtonSelected(boolean expectedSelected) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(1));
        setElementSelected(onlyDepatureRadioButtonInput, onlyDepartureRadioButtonLabel, expectedSelected);
    }

    /**
     * Clicks the exact departure day
     * @param departureDay as String
     */
    public void selectDate(String departureDay){
        scrollElementIntoView(departureDayInput);
        clickElement(departureDayInput);
        webDriver.findElement(departureDayInput).clear();
        setElementText(departureDayInput, departureDay);
        Actions actions = new Actions(webDriver);
        actions.moveByOffset(0, 0).click().perform();
    }
    /**
     * Searches the selected ticket in the Home page
     */
    public void clickSearchTicketButton() {
        WebElement acceptButton = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(clickSearchTicketsButton));
        acceptButton.click();
    }

}
