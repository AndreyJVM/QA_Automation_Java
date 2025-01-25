package page.object.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderElement {

    private final WebDriver driver;

    @FindBy(css = "#searchform")
    private WebElement form;

    @FindBy(css = "#search-field")
    private WebElement searchField;

    @FindBy(css = ".b-header-b-personal-e-icon-count-m-cart")
    private WebElement cardIcon;

    public HeaderElement(WebDriver driver) {
        this.driver = driver;
    }

    public ResultPage searchFor(String term) {
        searchField.sendKeys("Java", Keys.ENTER);
        return new ResultPage(driver);
    }

    public String getIconText() {
        return cardIcon.getText();
    }

    public CartPage clickCartIcon() {
        cardIcon.click();
        return new CartPage(driver);
    }
}