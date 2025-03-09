package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import pages.TravelerDataPage;

public class TravelerDataTest {
    //Locators
    private By assertionTravelerDataPage = By.xpath("//span[@class='label' and contains(text(), 'Introduce tus datos')]");
    //Variables
    private WebDriver webDriver;
    private TravelerDataPage travelerDataPage;

    @Before
    public void setup() {
        // Configuration Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        // Initialization of the ChromeDriver with the configured options
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        webDriver.get("https://venta.renfe.com/vol/datosViajeEnlaces.do?c=_6wq6"); // URL page
        travelerDataPage = new TravelerDataPage(webDriver); // Initialization of the Home Page
    }

    @Test
    public void travelerPageInvalidCardPaymentTest() {
        travelerDataPage.clickAcceptAllCookiesButton();
        //Assertion in order you are in the correct web page
        Assert.assertEquals(assertionTravelerDataPage, "Introduce tus datos");
        //Traveler data
        travelerDataPage.typeName("John");
        travelerDataPage.typeFirstSurnamne("Doe");
        travelerDataPage.typeSecondSurnamne("Marsos");
        travelerDataPage.typeDni("46131651E");
        travelerDataPage.typeEmail("test@qa.com");
        travelerDataPage.typePhone("696824570");
        travelerDataPage.clickPersonalizeTravelButton();
        travelerDataPage.clickFollowPurchaseButton();
        travelerDataPage.typeEmailPayment("test@qa.com");
        travelerDataPage.typePhonePayment("696824570");
        Assert.assertTrue(travelerDataPage.isDataCompletedCheckboxGreen(),"Please, complete mandatory fields");
        travelerDataPage.clickPersonalizeTravelButton();
        travelerDataPage.clickFollowPurchaseButton();
        travelerDataPage.typeEmailPayment("test@qa.com");
        travelerDataPage.typePhone("659851562");
        travelerDataPage.cardRadioButtonSelected(true);
        travelerDataPage.clickNewCard();
        travelerDataPage.setCheckboxSelectedPP(true);
        travelerDataPage.clickCompletePurchaseButton();
    }

    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.close(); //Ensures complete browser closure
        }
    }
}
