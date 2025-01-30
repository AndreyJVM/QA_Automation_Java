package task3.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ImageGalleryPage {

    private final WebDriver driver;
    private final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/loading-images.html";
    private final WebDriverWait wait;

    private static final String SPINNER_CSS = "#spinner";
    private static final String IMAGE_CONTAINER_CSS = "#image-container";
    private static final String IMG_CSS = "img";

    @FindBy(css = IMAGE_CONTAINER_CSS)
    private WebElement imageContainer;

    public ImageGalleryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void open() {
        driver.get(BASE_URL);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(SPINNER_CSS)));
    }

    public String getImageProperty(int imageIndex, String propertyName) {
        return imageContainer.findElements(By.cssSelector(IMG_CSS))
                .get(imageIndex)
                .getAttribute(propertyName);
    }
}