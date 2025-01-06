package find.elements;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import spting.factory.WebDriverFactory;

public class JSExecution {

    public static void main(String[] args) {

        String jsRemoveBanner = "document.querySelector(\".tgb-wrapper\").remove()";

        WebDriver driver = WebDriverFactory.create("chrome");
        driver.get("https://mail.ru/");

        ((JavascriptExecutor)driver).executeScript(jsRemoveBanner);

        driver.quit();
    }
}
