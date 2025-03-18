package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;
import java.time.Duration;
import java.util.List;

public class BasePage {
    //Driver initialization
    protected WebDriver webDriver; // Protected in order to be used by child classes.

    //Constructor with WebDriver as a parameter.
    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    //Locators
    private By acceptAllCookiesButton = By.id("onetrust-accept-btn-handler");
    private By errorButtonLocator = By.xpath("//div[@class='error00']");

    /**
     * Writes text inside a given element locator.
     *
     * @param locator By with the locator of the element.
     * @param text    String with the text that should be written.
     */
    public void setElementText(By locator, String text) {
        webDriver.findElement(locator).sendKeys(text);
    }

    /**
     * Clicks a given element locator.
     *
     * @param locator By with the locator of the element.
     */
    public void clickElement(By locator) {
        webDriver.findElement(locator).click();
    }

    /**
     * Returns "true" or "false" depending on if a given element locator is currently selected or unselected.
     * Normally used to interact with checkboxes or radio buttons.
     *
     * @param inputLocator By with the input locator of the element.
     */
    public boolean isElementSelected(By inputLocator) {
        return webDriver.findElement(inputLocator).isSelected();
    }

    /**
     * Scrolls a given element locator to the center of the screen.
     *
     * @param locator By with the locator of the element.
     */
    public void scrollElementIntoView(By locator) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView({block: 'center'});",
                webDriver.findElement(locator));
    }

    /**
     * Marks a given element locator as selected or unselected.
     * Normally used to interact with checkboxes or radio buttons.
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
     * Accepts all cookies in any Page.
     */
    public void clickAcceptAllCookiesButton() {
        WebElement acceptButton = new WebDriverWait(webDriver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(acceptAllCookiesButton));
        acceptButton.click();
    }

    /**
     * Waits until an element is displayed in any Page.
     * @param locator as a By
     * @param timeout as a long
     */
    public boolean waitUntilElementIsDisplayed(By locator, long timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(timeout));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
