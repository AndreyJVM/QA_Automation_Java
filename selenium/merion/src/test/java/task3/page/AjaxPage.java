package task3.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AjaxPage {
    private final WebDriver driver;

    private static final String MAIN_URL = "http://uitestingplayground.com/ajax";

    @FindBy(css = "#ajaxButton")
    private WebElement ajaxButton;

    @FindBy(css = "#content > p")
    private WebElement content;


    public AjaxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open(){
        driver.get(MAIN_URL);
    }

    public void clickTheButton() {
        ajaxButton.click();
    }

    public String getContent() {
        return content.getText();
    }
}