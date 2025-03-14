package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.ResultsPage;
import pages.TravelerDataPage;
import pages.ConfirmPurchasePage;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;

public class InvalidCardPaymentTest {
    //Locators
    private By assertionResultsPage = By.xpath("//span[@class='label' and contains(text(), 'Selecciona tu viaje')]");
    private By assertionTravelerDataPage = By.xpath("//span[@class='label' and contains(text(), 'Introduce tus datos')]");
    private By assertionConfirmPurchasePage = By.xpath("//div[@class='datosDeLaOperacion']");
    private By popUpPaymentError = By.xpath("//div[@id='myModalBody']//li[contains(text(), 'Tarjeta no soportada (RS18)')]");

    //Variables
    private WebDriver webDriver;
    private HomePage homePage;
    private ResultsPage resultsPage;
    private BasePage basePage;
    private TravelerDataPage travelerDataPage;
    private ConfirmPurchasePage confirmPurchasePage;

    @BeforeMethod
    public void setup() {
        // Configuration Chrome options
        WebDriverManager.chromedriver().setup(); // Download and configure the driver automatically.
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        // Initialization of the ChromeDriver with the configured options
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        webDriver.get("https://www.renfe.com/es/es"); // URL page
        homePage = new HomePage(webDriver); // Initialization of the Home Page
        resultsPage = new ResultsPage(webDriver); // Initialization of the Results Page
        basePage = new BasePage(webDriver); // Initialization of the Base Page
        travelerDataPage = new TravelerDataPage(webDriver); // Initialization of the Traveler data Page
        confirmPurchasePage = new ConfirmPurchasePage(webDriver); // Initialization of Confirm purchase Page
    }

    @Test
    public void homePageInvalidCardPaymentTest() {
        //Ticket selection and submit search
        basePage.clickAcceptAllCookiesButton();
        //Assertion to ensure you are in the correct web page
        Assert.assertEquals("Buscar billete", "Buscar billete");
        homePage.enterOrigin("Valencia Joaquín Sorolla"); //Select an origin
        homePage.enterDestination("Barcelona-Sants"); //Select a destination
        homePage.selectDepartureDate();
        homePage.clickOnlyGoRadioButtonSelected(true);
        homePage.clickAcceptButton();
        homePage.clickSearchTicketButton();
    }

    @Test
    public void resultsPageInvalidCardPaymentTest() {
        // URL page, is needed to change the alphanumeric code like "SRm0" (length 4 positions) in the URL c=_XXXX
        webDriver.get("https://venta.renfe.com/vol/buscarTrenEnlaces.do?c=_BK9f");
        basePage.clickAcceptAllCookiesButton();
        // Assertion in order to ensure you are in the correct web page
        Assert.assertNotEquals(assertionResultsPage, "Selecciona tu viaje");

        boolean isCorrectPage = basePage.CorrectPage(true);
        if (!isCorrectPage) {
            System.out.println("It can't be possible to continue with the test");
        }

        // Continue with the test regardless of the result of DataCorrectPage
        //resultsPage.clickFirstAvailableTrain();
        //resultsPage.clickFareApplied();
        //resultsPage.clickSelectionApplied();
        //resultsPage.setRefundCheckboxSelected(true);
        //resultsPage.clickLinkContinueNoRefund();

        // Optional, we can still assert again if it´s needed that we can´t access to the page.
        Assert.assertTrue(isCorrectPage, "It can't be possible to continue with the test");
    }

    @Test
    public void travelerDataInvalidCardPaymentTest() {
        // URL page, is needed to change the alphanumeric code like "SRm0" (length 4 positions) in the URL c=_XXXX
        webDriver.get("https://venta.renfe.com/vol/buscarTrenEnlaces.do?c=_PdDV");
        basePage.clickAcceptAllCookiesButton();
        //Assertion in order you are in the correct web page
        Assert.assertNotEquals(assertionTravelerDataPage, "Introduce tus datos");
        boolean isCorrectPage = basePage.CorrectPage(true);
        if (!isCorrectPage) {
            System.out.println("It can't be possible to continue with the test");
        }

        // Continue with the test regardless of the result of CorrectPage
        //travelerDataPage.typeName("John");
        //travelerDataPage.typeFirstSurnamne("Doe");
        //travelerDataPage.typeSecondSurnamne("Marsos");
        //travelerDataPage.typeDni("46131651E");
        //travelerDataPage.typeEmail("test@qa.com");
        //travelerDataPage.typePhone("696824570");
        //travelerDataPage.clickPersonalizeTravelButton();
        //travelerDataPage.clickFollowPurchaseButton();
        //travelerDataPage.typeEmailPayment("test@qa.com");
        //travelerDataPage.typePhonePayment("696824570");
        //Assert.assertTrue(travelerDataPage.isDataCompletedCheckboxGreen(), "Please, complete mandatory fields");
        //travelerDataPage.clickPersonalizeTravelButton();
        //travelerDataPage.clickFollowPurchaseButton();
        //travelerDataPage.typeEmailPayment("test@qa.com");
        //travelerDataPage.typePhone("659851562");
        //travelerDataPage.cardRadioButtonSelected(true);
        //travelerDataPage.clickNewCard();
        //travelerDataPage.setCheckboxSelectedPP(true);
        //travelerDataPage.clickCompletePurchaseButton();

        // Optional, we can still assert again if it´s needed that we can´t access to the correct page
        Assert.assertTrue(isCorrectPage, "It can't be possible to continue with the test");
    }

    @Test
    public void confirmPurchaseInvalidCardPaymentTest() {
        // URL page to confirm the purchaes
        webDriver.get("https://sis.redsys.es/sis/realizarPago");
        //Assertion in order you are in the correct web page
        Assert.assertNotEquals(assertionConfirmPurchasePage, "Datos de la operación");
        boolean isCorrectPage = basePage.CorrectPage(true);
        if (!isCorrectPage) {
            System.out.println("It can't be possible to continue with the test");
        }

        // Continue with the test regardless of the result of CorrectPage
        //confirmPurchasePage.typeBankCard("4970100000000063");
        //confirmPurchasePage.typeExpirationDate("11/31");
        //confirmPurchasePage.typeCvv("990");
        //confirmPurchasePage.clickConfirmPurchaseButton();

        // Optional, we can still assert again if it´s needed that we can´t access to the correct page
        Assert.assertFalse(isCorrectPage, "It can't be possible to continue with the test");
    }

    @Test
    public void popUpWithExpectedErrorAppears(){
    //Finally,verifies that the pop-up error appears.
    // URL page, is needed to change the alphanumeric code like "SRm0" (length 4 positions) in the URL c=_XXXX
    webDriver.get("https://venta.renfe.com/vol/buscarTrenEnlaces.do?c=_PdDV");
    basePage.clickAcceptAllCookiesButton();
    boolean cardErrorPopUp = basePage.waitUntilElementIsDisplayed(popUpPaymentError, 3000);
    Assert.assertFalse(cardErrorPopUp, "No apareció el mensaje de error 'Tarjeta no soportada (RS18)'");

        if (cardErrorPopUp) {
        System.out.println("El pop-up con el error 'Tarjeta no soportada (RS18)' apareció.");
    } else {
        System.out.println("El pop-up con el error 'Tarjeta no soportada (RS18)' NO apareció.");
    }
}

    @AfterMethod
    public void tearDown() {
    if (webDriver != null) {
    webDriver.close(); //Closes the current instance of the browser
    }
    }

}