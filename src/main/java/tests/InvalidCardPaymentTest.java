package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ResultsPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class InvalidCardPaymentTest {
    //Locators
    private By assertionResultsPage = By.xpath("//span[@class='label' and contains(text(), 'Selecciona tu viaje')]");

    //Variables
    private WebDriver webDriver;
    private HomePage homePage;
    private ResultsPage resultsPage;

    @BeforeMethod
    public void setup() {
        // Configuration Chrome options
        WebDriverManager.chromedriver().setup(); // Descarga y configura el driver automáticamente
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        // Initialization of the ChromeDriver with the configured options
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        webDriver.get("https://www.renfe.com/es/es"); // URL page
        homePage = new HomePage(webDriver); // Initialization of the Home Page
        resultsPage = new ResultsPage(webDriver); // Initialization of the Results Page
    }

    @Test
    public void homePageInvalidCardPaymentTest() {
        //Ticket selection and submit search
        homePage.clickAcceptAllCookiesButton();
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
    webDriver.get("https://venta.renfe.com/vol/buscarTrenEnlaces.do?c=_EKaf"); // URL page
    resultsPage.clickAcceptAllCookiesButton();
    //Assertion in order to ensure you are inn the correct web page
    Assert.assertNotEquals(assertionResultsPage, "Selecciona tu viaje");
    resultsPage.clickFirstAvailableTrain();
    resultsPage.clickFareApplied();
    resultsPage.clickSelectionApplied();
    resultsPage.setRefundCheckboxSelected(true);
    resultsPage.clickLinkContinueNoRefund();
    }

    @AfterMethod
    public void tearDown() {
    if (webDriver != null) {
    webDriver.quit(); //Close all the browser´s instances
    }
    }
}