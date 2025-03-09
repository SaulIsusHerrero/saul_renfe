package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConfirmPurchasePage extends BasePage {

    public ConfirmPurchasePage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By cardInput = By.xpath("//input[@id='card-number']");
    private By expirationDateInput = By.xpath("//input[@id='card-expiration']");
    private By cvvInput = By.xpath("//input[@id='card-cvv']");
    private By purchaseButton = By.xpath("");

    // Methods
    /**
     * Waits a given element locator to appear before the timeout duration
     *
     * @param locator By with the locator of the element
     * @param timeout long with the wait duration in milliseconds, for example "5000" is equal to 5 seconds
     * @return
     */
    public boolean waitUntilElementIsDisplayed(By locator, long timeout) {
        new WebDriverWait(webDriver, Duration.ofMillis(timeout)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return false;
    }

    /**
     * Types the bank card used in the Confirm Purchase page
     * @param card String with the traveler´s bank card
     */
    public void typeBankCard(String card){
        waitUntilElementIsDisplayed(cardInput, 1000);
        setElementText(cardInput, card);
    }

    /**
     * Types the bank card expiration MM/YY in the Confirm Purchase page
     * @param expiration String with the traveler´s expiration date of the bank card
     */
    public void typeExpirationDate(String expiration){
        waitUntilElementIsDisplayed(expirationDateInput, 1000);
        setElementText(expirationDateInput, expiration);
    }

    /**
     * Types the CVV from the bank card in the Confirm Purchase page
     * @param cvv String with the traveler´s cvv from the bank card
     */
    public void typeCvv(String cvv){
        waitUntilElementIsDisplayed(cvvInput, 1000);
        setElementText(cvvInput, cvv);
    }

    /**
     * Clicks the Confirm purchase button in the Confirm purchase page
     */
    public void clickConfirmPurchaseButton() {
        waitUntilElementIsDisplayed(purchaseButton, 5000);
        scrollElementIntoView(purchaseButton);
        clickElement(purchaseButton);
    }

}

