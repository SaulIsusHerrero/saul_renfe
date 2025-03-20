package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.SeleccionarTuViajePage;
import java.time.Duration;

public class InvalidCardPaymentTest {
    //Instances
    private WebDriver webDriver;
    private BasePage basePage;
    private HomePage homePage;
    private SeleccionarTuViajePage seleccionarTuViajePage;

    @BeforeMethod
    public void setup() throws InterruptedException {
        //Chrome: Initialization of the ChromeDriver.
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.manage().window().maximize();
        webDriver.get("https://www.renfe.com/es/es"); //URL page.
        basePage = new BasePage(webDriver); //Initialization of the Base Page.
        homePage = new HomePage(webDriver); //Initialization of the Home Page.
        seleccionarTuViajePage = new SeleccionarTuViajePage(webDriver); //Initialization of the SeleccionarTuViaje Page.
    }

    @Test
    public void RenfeInvalidCardPaymentTest() {
        basePage.clickAcceptAllCookiesButton();
        homePage.enterOrigin("VALENCIA JOAQUÍN SOROLLA");
        homePage.enterDestination("BARCELONA-SANTS");
        homePage.selectDepartureDate();
        homePage.clickSoloIdaButtonSelected(true);
        homePage.clickAcceptButton();
        homePage.clickSearchTicketButton();
        basePage.sleep(10);
        Assert.assertTrue(seleccionarTuViajePage.isSeleccionaTuViajeLabelEnabled());
        basePage.sleep(10);
        Assert.assertTrue(seleccionarTuViajePage.selectNextDayUntilTrainsAvailable());
        //seleccionarTuViajePage.clickFareApplied();
        //Assert.assertTrue(seleccionarTuViajePage.verifyNumberOfTravelers());
        //Assert.assertTrue(seleccionarTuViajePage.verifyFareIsBasic());
        //Assert.assertTrue(seleccionarTuViajePage.verifyTotalPriceEqualsFarePrice());
        //seleccionarTuViajePage.clickSelectButton();
        //Assert.assertTrue(seleccionarTuViajePage.verifyNoContinueButtonAppears());
        //Assert.assertTrue(seleccionarTuViajePage.verifyPopUpAppears());
        //seleccionarTuViajePage.clickContinueButton();
        //Assert.assertTrue(seleccionarTuViajePage.isSeleccionaTuViajeLabelEnabled());
    }

    //@AfterMethod
        //public void tearDown() {
            //if (webDriver != null) {
                //webDriver.quit(); //Closes the current instance of the browser
            //}
        //}
}