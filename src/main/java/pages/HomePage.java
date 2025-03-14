package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    // Locators
    private By originInput = By.xpath("//input[@id='origin']");
    private By destinationInput = By.xpath("//input[@id='destination']");
    private By dateDepartureInput = By.xpath("//input[@id='first-input']");
    private By onlyDepartureRadioButtonLabel = By.xpath("//label[@for='trip-go']");
    private By onlyDepartureRadioButtonInput = By.xpath("//input[@id='trip-go']");
    private By acceptButton = By.xpath("//button[contains(text(),'Aceptar')]");
    private By clickSearchTicketsButton= By.xpath("//button[normalize-space()='Buscar billete']");

    //Variables
    private WebDriver driver;

    // Methods
    /**
     * Choose the trip origin in the Home page
     * @param origin
     */
    public void enterOrigin(String origin) {
        waitUntilElementIsDisplayed(originInput, 1000);
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
        clickElement(dateDepartureInput);
    }

    /**
     * Marks the "only go trip" radio button as selected or unselected
     *
     * @param expectedSelected boolean with the expected selected state of the element
     */
    public void clickOnlyGoRadioButtonSelected(boolean expectedSelected) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(1));
        setElementSelected(onlyDepartureRadioButtonInput, onlyDepartureRadioButtonLabel, expectedSelected);
    }

    //Method to click the 'Accept' button on the calendar.
    public void clickAcceptButton() {
        //Wait for and find the 'Accept' button.
        List<WebElement> botones = driver.findElements(acceptButton);
        System.out.println("Number of buttons found: " + botones.size());

        if (!botones.isEmpty()) {
            WebElement acceptButtonElement = botones.get(0); // clicks the 1st button found it

            // Scrolls till the buton
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", acceptButtonElement);
            WebElement acceptButton = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(this.acceptButton));
            // Attempt to click with Selenium
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
     * Searches the selected ticket in the Home page
     */
    public void clickSearchTicketButton() {
        try {
            // Waits until the element is clickable.
            WebElement searchButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(clickSearchTicketsButton));
            searchButton.click();
        } catch (TimeoutException e) {
            System.out.println("The button was not clickable within the expected time. Trying with JavaScript...");
            WebElement searchButton = driver.findElement(clickSearchTicketsButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchButton);
        }
    }

}
