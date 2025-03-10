package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmPurchasePage {
    // Constructor
    public ConfirmPurchasePage(WebDriver driver) {
        this.driver = driver;
    }

    //Variables
    private WebDriver driver;

    //Methods

    /**
     * type the bank card in the confirmation purchase page
     * @param cardNumber as a String
     */
    public void typeBankCard(String cardNumber) {
        driver.findElement(By.id("card-number")).sendKeys(cardNumber);
    }

    /**
     * type the expiration date MM/YY in the confirmation purchase page
     * @param expirationDate as a String
     */
    public void typeExpirationDate(String expirationDate) {
        driver.findElement(By.id("expiration-date")).sendKeys(expirationDate);
    }

    /**
     * type the CVV security number in the confirmation purchase page
     * @param cvv as a String
     */
    public void typeCvv(String cvv) {
        driver.findElement(By.id("cvv")).sendKeys(cvv);
    }

    /**
     * Click the confirmation of purchase button in the confirmation purchase page
     */
    public void clickConfirmPurchaseButton() {
        driver.findElement(By.id("confirm-purchase")).click();
    }
}
