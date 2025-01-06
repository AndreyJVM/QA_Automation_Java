package find.elements;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import spting.factory.WebDriverFactory;

public class JSExecution {

    public static void main(String[] args) {

        String jsRemoveBanner = "document.querySelector(\".tgb-wrapper\").remove()";

        // Set value with run Window
        String jsSetLocalStorage = "localStorage.setItem(\"bestScore\", \"7777777\")";

        WebDriver driver = WebDriverFactory.create("chrome");
        driver.get("https://mail.ru/");
        ((JavascriptExecutor) driver).executeScript(jsRemoveBanner);

        driver.close();

        driver.get("https://play2048.co/");
        ((JavascriptExecutor) driver).executeScript(jsSetLocalStorage);
        driver.navigate().refresh();

        driver.quit();
    }
}