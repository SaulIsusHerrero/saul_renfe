package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import pages.BasePage;
import pages.HomePage;

public class HomePageSelectTicketTest {
    //Variables
    private WebDriver webDriver;
    private HomePage homePage;

    @Before
    public void setup() {
        // Configuration Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        // Initialization of the ChromeDriver with the configured options
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        webDriver.get("https://www.renfe.com/es/es"); // URL page
        homePage = new HomePage(webDriver); // Initialization of the Home Page
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
        String date = "lun., 31/03/25";
        homePage.selectDate(date);
        homePage.clickSearchTicketButton();
    }

    @After
    public void tearDown() {
    if (webDriver != null) {
    webDriver.close(); //Ensures complete browser closure
    }
    }
}
