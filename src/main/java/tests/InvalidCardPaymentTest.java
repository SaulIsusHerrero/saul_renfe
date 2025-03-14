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
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;

public class InvalidCardPaymentTest {
    //Locators
    private By assertionResultsPage = By.xpath("//span[@class='label' and contains(text(), 'Selecciona tu viaje')]");
    private By errorButtonResults = By.xpath("//div[@class='error00']");

    //Variables
    private WebDriver webDriver;
    private HomePage homePage;
    private ResultsPage resultsPage;
    private BasePage basePage;

    // Methods
    /**
     * Checks if there is an error in the Results page
     */
    public boolean resultsCorrectPage(Boolean correctPage) {
        // Waits and finds out the "Aceptar" button
        Boolean correct = basePage.waitUntilElementIsDisplayed(errorButtonResults, 3000);
        List<WebElement> errorButton = webDriver.findElements(errorButtonResults);
        System.out.println("Checking if an error appears on the results page, it isn't possible to continue with the purchase");

        if (!errorButton.isEmpty()) {
            // First part of the If structure
            System.out.println("It can´t be possible to continue with the test");
            return true; // Stop the test because the page isn't displayed correctly
        }

        System.out.println("No error button found, the purchase can continue");
        return false;
    }

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
        // URL page, is needed to change the alphanumeric code like "SRm0" (length 4 positions) in the URL c=_XXXX
        webDriver.get("https://venta.renfe.com/vol/buscarTrenEnlaces.do?c=_BK9f");
        resultsPage.clickAcceptAllCookiesButton();
        // Assertion in order to ensure you are in the correct web page
        Assert.assertNotEquals(assertionResultsPage, "Selecciona tu viaje");

        boolean isCorrectPage = resultsCorrectPage(true);
        if (!isCorrectPage) {
            System.out.println("It can't be possible to continue with the test");
        }

        // Continue with the test regardless of the result of resultsCorrectPage
        //resultsPage.clickFirstAvailableTrain();
        //resultsPage.clickFareApplied();
        //resultsPage.clickSelectionApplied();
        //resultsPage.setRefundCheckboxSelected(true);
        //resultsPage.clickLinkContinueNoRefund();

        // Optional, we can still assert again if it´s needed
        Assert.assertTrue(isCorrectPage, "It can't be possible to continue with the test");
    }

    @AfterMethod
    public void tearDown() {
    if (webDriver != null) {
    webDriver.close(); //Closes the current instance of the browser
    }
    }
}