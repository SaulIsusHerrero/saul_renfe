package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.ResultsPage;
import pages.TravelerDataPage;
import pages.ConfirmPurchasePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InvalidCardPaymentTest {
    //Locator
    private By acceptButtonLocator = By.xpath("//button[contains(text(),'Aceptar')]");

    //Variables
    private BasePage basePage;
    private WebDriver webDriver;
    private HomePage homePage;
    private ResultsPage resultsPage;
    private TravelerDataPage travelerDataPage;
    private ConfirmPurchasePage confirmPurchasePage;

    @BeforeMethod
    public void setup() throws InterruptedException {
        //Chrome: Initialization of the ChromeDriver with the configured options
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.manage().window().maximize();
        webDriver.get("https://www.renfe.com/es/es"); // URL page.
        basePage = new BasePage(webDriver); // Initialization of the Base Page.
        homePage = new HomePage(webDriver); // Initialization of the Home Page.
        resultsPage = new ResultsPage(webDriver); // Initialization of the Results Page.
        travelerDataPage = new TravelerDataPage(webDriver); // Initialization of the Traveler Data Page.
        confirmPurchasePage = new ConfirmPurchasePage(webDriver); // Initialization of Confirm Purchase Page.
    }

    @Test
    public void RenfeInvalidCardPaymentTest() {
        basePage.clickAcceptAllCookiesButton();
        homePage.enterOrigin("VALENCIA JOAQUÍN SOROLLA");
        // Verifica que el campo de origen tenga el valor correcto
        WebElement originInput = webDriver.findElement(homePage.originInputLocator);
        Assert.assertEquals("VALENCIA JOAQUÍN SOROLLA", originInput.getAttribute("value"));
        homePage.enterDestination("BARCELONA-SANTS");
        // Verifica que el campo de destino tenga el valor correcto
        WebElement destinationInput = webDriver.findElement(homePage.destinationInputLocator);
        Assert.assertEquals("BARCELONA-SANTS", destinationInput.getAttribute("value"));
        homePage.selectDepartureDate();
        homePage.clickOnlyGoRadioButtonSelected(true);

        // Inicializa el WebElement acceptButton dentro del test
        WebElement acceptButton = webDriver.findElement(acceptButtonLocator);
        basePage.scrollElementIntoView(acceptButtonLocator);
        homePage.clickAcceptButton();
        homePage.clickSearchTicketButton();
        //resultsPage.clickFirstAvailableTrain();
        //resultsPage.clickFareApplied();
        //resultsPage.clickSelectionApplied();
        //resultsPage.setRefundCheckboxSelected(true);
        //resultsPage.clickLinkContinueNoRefund();
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
        //confirmPurchasePage.typeBankCard("4970100000000063");
        //confirmPurchasePage.typeExpirationDate("11/31");
        //confirmPurchasePage.typeCvv("990");
        //confirmPurchasePage.clickConfirmPurchaseButton();
        //Finally,verifies that the pop-up error appears.
    }

    //@AfterMethod
    //public void tearDown() {
        //if (webDriver != null) {
            //webDriver.quit(); // Closes the current instance of the browser
        //}
    //}
}