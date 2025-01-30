package task3.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class TextInputPage {
    private final WebDriver driver;
    private final String BASE_URL = "http://uitestingplayground.com/textinput";
    private final WebDriverWait wait;

    private static final String NEW_BUTTON_NAME_CSS = "#newButtonName";
    private static final String UPDATING_BUTTON_CSS = "#updatingButton";

    @FindBy(css = NEW_BUTTON_NAME_CSS)
    private WebElement newButtonName;

    @FindBy(css = UPDATING_BUTTON_CSS)
    private WebElement updatingButton;

    public TextInputPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public TextInputPage open() {
        driver.get(BASE_URL);
        return this;
    }

    public TextInputPage setButtonName(String buttonName) {
        if (newButtonName.isDisplayed()) {
            newButtonName.sendKeys(buttonName);
            return this;
        } else {
            throw new RuntimeException("Element not found: " + NEW_BUTTON_NAME_CSS);
        }
    }

    public TextInputPage clickUpdatingButton() {
        if (updatingButton.isDisplayed()) {
            updatingButton.click();
            wait.until(textToBe(By.cssSelector(UPDATING_BUTTON_CSS), "Merion"));
            return this;
        } else {
            throw new RuntimeException("Element not found: " + UPDATING_BUTTON_CSS);
        }
    }

    public String getButtonText() {
        if (updatingButton.isDisplayed()) {
            return updatingButton.getText();
        } else {
            throw new RuntimeException("Element not found: " + UPDATING_BUTTON_CSS);
        }
    }

}