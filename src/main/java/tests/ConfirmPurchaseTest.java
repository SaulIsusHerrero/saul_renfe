package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import pages.ConfirmPurchasePage;

public class ConfirmPurchaseTest {
    //Locators
    private By assertionConfirmDataPage = By.xpath("//div[@class='datosDeLaOperacion']");
    private By popUpPaymentError = By.xpath("//div[@id='myModalBody']//li[contains(text(), 'Tarjeta no soportada (RS18)')]");

    //Variables
    private WebDriver webDriver;
    private ConfirmPurchasePage confirmPurchasePage;

    @Before
    public void setup() {
        // Configuration Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        // Initialization of the ChromeDriver with the configured options
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        webDriver.get("https://sis.redsys.es/sis/realizarPago"); // URL page
        confirmPurchasePage = new ConfirmPurchasePage(webDriver); // Initialization of the Home Page
    }

    @Test
    public void InvalidCardPaymentTest() {
        confirmPurchasePage.clickAcceptAllCookiesButton();
        //Assertion in order you are in the correct web page
        Assert.assertEquals(assertionConfirmDataPage, "Datos de la operación");
        //Purchase details
        confirmPurchasePage.typeBankCard("4000 0000 0000 1000");
        confirmPurchasePage.typeExpirationDate("11/31");
        confirmPurchasePage.typeCvv("990");
        confirmPurchasePage.clickConfirmPurchaseButton();
        //Final assertion about that appears a pop-up telling card not supported with (RS18) as a code error
        boolean cardErrorPopUp = confirmPurchasePage.waitUntilElementIsDisplayed(popUpPaymentError, 3000);
        Assert.assertTrue(cardErrorPopUp, "The Pop-up with the error that card not supported (RS18) doesn´t appear ");
        if (cardErrorPopUp == true){
            System.out.println("The Pop-up with the error that card not supported (RS18) appears");
        } else {
            System.out.println("The card is supported");
        }
    }

    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.close(); //Ensures complete browser closure
        }
    }
}
