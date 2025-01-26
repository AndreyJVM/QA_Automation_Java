package tesk3.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.List;

public class CatalogPage {

    private final WebDriver driver;

    public CatalogPage(WebDriver driver) {
        this.driver = driver;
    }


    public void addItems(Collection<String> itemNames) {
        List<WebElement> items = driver.findElements(By.cssSelector(".inventory_item"));

        for (WebElement item : items) {
            String productName = item.findElement(By.cssSelector(".inventory_item_name")).getText();
            if(itemNames.contains(productName)){
                item.findElement(By.cssSelector("button")).click();
            }
        }
    }
}