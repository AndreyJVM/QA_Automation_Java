package task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class AuthorizationForm {

    /**
     * Open a page http://the-internet.herokuapp.com/login
     * In the username field, enter the value "tomsmith"
     * Enter the "SuperSecretPassword!" value in the password field!
     * Click the Login button
     * Print the text of the green bar that appears to the console.
     * */

    public static void main(String[] args) {

        String login = "tomsmith";
        String password = "SuperSecretPassword!";

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://the-internet.herokuapp.com/login");

        WebElement inputLogin = driver.findElement(By.cssSelector("#username"));
        WebElement inputPassword = driver.findElement(By.cssSelector("#password"));
        WebElement loginButton = driver.findElement(By.cssSelector(".fa-sign-in"));

        inputLogin.clear();
        inputLogin.sendKeys(login);

        inputPassword.clear();
        inputPassword.sendKeys(password);

        loginButton.click();

        System.out.println("Print the text of the green bar that appears to the console" + driver.findElement(By.cssSelector("#flash")).getText());

        driver.quit();
    }
}