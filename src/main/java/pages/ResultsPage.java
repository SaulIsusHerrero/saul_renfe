package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultsPage extends BasePage {

    public ResultsPage(WebDriver driver) {
        super(driver);
    }

    //Locators
    private By availableTrain = By.xpath("//div[@id='tren_1_item1']");
    private By fareTrain = By.xpath("(//span[text()='Básico'])[1]");
    private By selectButton = By.xpath(("//div[@class='rowitem1 d-none d-lg-inline d-xl-inline d-md-inline']//span[@id='totalTrayectoBanner' and contains(text(), '39,05€')]"));
    private By noShowAgainRefundLabel = By.xpath("//label[@for='modal_fareupg_nomostrar']");
    private By noShowAgainRefundInput = By.xpath("//input[@id='modal_fareupg_nomostrar']");
    private By continueFareLink = By.xpath("//p[@id='aceptarConfirmacionFareUpgrade']");

    //Methods
    /**
    * Clicks the first available train button in the Results page.
    /*/
    public void clickFirstAvailableTrain(){
        //waitUntilElementIsDisplayed(availableTrain, 1000);
        clickElement(availableTrain);
    }

    /**
    * Clicks the Basic fare button in the Results page.
    */
    public void clickFareApplied() {
        //waitUntilElementIsDisplayed(fareTrain, 1000);
        clickElement(fareTrain);
    }

     /**
     * Clicks the Selection button in the Results page.
     */
    public void clickSelectionApplied() {
        //waitUntilElementIsDisplayed(selectButton, 1000);
        clickElement(selectButton);
    }

     /**
     * Marks the No show again checkbox as selected or unselected in the Results page.
     *
    * @param expectedSelected boolean with the expected selected state of the element, for example "true" or "false"
    */
    public void setRefundCheckboxSelected(boolean expectedSelected) {
        //waitUntilElementIsDisplayed(noShowAgainRefundLabel, 5000);
        setElementSelected(noShowAgainRefundInput, noShowAgainRefundLabel, expectedSelected);
    }

    /**
    * Clicks the link to continue without refund of the train ticket in the Results page
    */
    public void clickLinkContinueNoRefund() {
        //waitUntilElementIsDisplayed(continueFareLink, 1000);
        clickElement(availableTrain);
    }

}


