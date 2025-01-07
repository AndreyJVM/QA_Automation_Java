package task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class ClickOnTheButton {

    /**
     Open a page "http://the-internet.herokuapp.com/add_remove_elements/"
     Click the "Add Element" button 5 times
     Collect a list of "Delete" buttons from the page
     Display the size of the list
     * */

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");

        WebElement button = driver.findElement(By.cssSelector("#content > div > button"));
        for(int i = 0; i < 5; i++){
            button.click();
        }

        List<WebElement> deleteButton = driver.findElements(By.cssSelector("#elements .added-manually"));
        System.out.println("List size 'deleteButton': " + deleteButton.size());

        driver.quit();
    }
}
