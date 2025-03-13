package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.ResultsPage;


public class ResultsPageSelectAvailableTicketTest {
    //Locators
    private By assertionResultsPage = By.xpath("//span[@class='label' and contains(text(), 'Selecciona tu viaje')]");

    //Variables
    private WebDriver webDriver;
    private ResultsPage resultsPage;

    @BeforeMethod
    public void setup() {
        // Configuration Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        // Initialization of the ChromeDriver with the configured options
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        webDriver.get("https://venta.renfe.com/vol/buscarTrenEnlaces.do?c=_EKaf"); // URL page
        resultsPage = new ResultsPage(webDriver); // Initialization of the Results Page
    }

    @Test
    public void resultsPageInvalidCardPaymentTest() {
        resultsPage.clickAcceptAllCookiesButton();
        //Assertion in order to ensure you are inn the correct web page
        Assert.assertEquals(assertionResultsPage, "Selecciona tu viaje");
        resultsPage.clickFirstAvailableTrain();
        resultsPage.clickFareApplied();
        resultsPage.clickSelectionApplied();
        resultsPage.setRefundCheckboxSelected(true);
        resultsPage.clickLinkContinueNoRefund();
    }

    @AfterMethod
    public void tearDown() {
    if (webDriver != null) {
    webDriver.close(); //Ensures complete browser closure
    }
    }

}
