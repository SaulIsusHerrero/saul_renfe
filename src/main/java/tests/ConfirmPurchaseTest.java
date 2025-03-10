package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import utils.DriverManager;
import pages.ConfirmPurchasePage;

public class ConfirmPurchaseTest {
    // Locators
    private By assertionConfirmDataPage = By.xpath("//div[@class='datosDeLaOperacion']");
    private By popUpPaymentError = By.xpath("//div[@id='myModalBody']//li[contains(text(), 'Tarjeta no soportada (RS18)')]");

    // Variables
    private WebDriver webDriver;
    private ConfirmPurchasePage confirmPurchasePage;
    private BasePage basePage;

    @Before
    public void setup() {
        webDriver = DriverManager.getDriver();
        if (webDriver == null) {
            throw new RuntimeException("WebDriver no pudo ser inicializado. Revisa DriverManager.");
        }
        webDriver.get("https://sis.redsys.es/sis/realizarPago");
        confirmPurchasePage = new ConfirmPurchasePage(webDriver); // Asegura que confirmPurchasePage no sea null
    }

    @Test
    public void InvalidCardPaymentTest() {
        basePage.clickAcceptAllCookiesButton();

        // Verificar que estamos en la página correcta
        Assert.assertNotNull("confirmPurchasePage es null", confirmPurchasePage);
        Assert.assertEquals("Datos de la operación", webDriver.findElement(assertionConfirmDataPage).getText());

        // Ingresar datos de pago
        confirmPurchasePage.typeBankCard("4000 0000 0000 1000");
        confirmPurchasePage.typeExpirationDate("11/31");
        confirmPurchasePage.typeCvv("990");
        confirmPurchasePage.clickConfirmPurchaseButton();

        // Verificar que aparece el error
        BasePage basePage = new BasePage(webDriver);
        boolean cardErrorPopUp = basePage.waitUntilElementIsDisplayed(popUpPaymentError, 3000);
        Assert.assertTrue("No apareció el mensaje de error 'Tarjeta no soportada (RS18)'", cardErrorPopUp);

        if (cardErrorPopUp) {
            System.out.println("El pop-up con el error 'Tarjeta no soportada (RS18)' apareció.");
        } else {
            System.out.println("La tarjeta fue aceptada.");
        }
    }

    @After
    public void tearDown() {
        DriverManager.closeDriver();
    }
}
