package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmPurchasePage extends BasePage{
    // Constructor
    public ConfirmPurchasePage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    //Locator
    private By popUpPaymentError = By.xpath("//div[@id='myModalBody']//li[contains(text(), 'Tarjeta no soportada (RS18)')]");

    //Driver initialization
    private WebDriver driver;

    //Methods
    /**
     * Types the bank card in the confirmation purchase page.
     * @param cardNumber as a String
     */
    public void typeBankCard(String cardNumber) {
        webDriver.findElement(By.id("card-number")).sendKeys(cardNumber);
    }

    /**
     * Types the expiration date MM/YY in the confirmation purchase page.
     * @param expirationDate as a String
     */
    public void typeExpirationDate(String expirationDate) {
        webDriver.findElement(By.id("expiration-date")).sendKeys(expirationDate);
    }

    /**
     * Types the CVV security number in the confirmation purchase page.
     * @param cvv as a String
     */
    public void typeCvv(String cvv) {
        webDriver.findElement(By.id("cvv")).sendKeys(cvv);
    }

    /**
     * Clicks the confirmation of purchase button in the confirmation purchase page.
     */
    public void clickConfirmPurchaseButton() {
        webDriver.findElement(By.id("confirm-purchase")).click();
    }
}
