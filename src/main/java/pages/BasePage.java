package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver webDriver; // Protected in order to be used by child classes

    //Constructor with WebDriver as a parameter
    public BasePage(WebDriver driver) {
        this.webDriver = driver;
    }

    //Locators
    private By acceptAllCookiesButton = By.id("onetrust-accept-btn-handler");

    /**
     * Writes text inside a given element locator
     *
     * @param locator By with the locator of the element
     * @param text    String with the text that should be written
     */
    public void setElementText(By locator, String text) {
        webDriver.findElement(locator).sendKeys(text);
    }

    /**
     * Clicks a given element locator
     *
     * @param locator By with the locator of the element
     */
    public void clickElement(By locator) {
        webDriver.findElement(locator).click();
    }

    /**
     * Returns "true" or "false" depending on if a given element locator is currently selected or unselected
     * Normally used to interact with checkboxes or radio buttons
     *
     * @param inputLocator By with the input locator of the element
     */
    public boolean isElementSelected(By inputLocator) {
        return webDriver.findElement(inputLocator).isSelected();
    }

    /**
     * Scrolls a given element locator to the center of the screen
     *
     * @param locator By with the locator of the element
     */
    public void scrollElementIntoView(By locator) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView({block: 'center'});", webDriver.findElement(locator));
    }

    /**
     * Marks a given element locator as selected or unselected
     * Normally used to interact with checkboxes or radio buttons
     *
     * @param inputLocator     By with the input locator of the element
     * @param labelLocator     By with the label locator of the element
     * @param expectedSelected boolean with the expected selected state of the element
     */
    public void setElementSelected(By inputLocator, By labelLocator, boolean expectedSelected) {
        boolean actualSelected = isElementSelected(inputLocator);
        if (actualSelected != expectedSelected) {
            scrollElementIntoView(labelLocator);
            clickElement(labelLocator);
        }
    }
    /**
     * Accepts all cookies in the Home Page
     */
    public void clickAcceptAllCookiesButton() {
        WebElement acceptButton = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(acceptAllCookiesButton));
        acceptButton.click();
    }

}
