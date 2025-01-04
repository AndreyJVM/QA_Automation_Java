package spting.navigate;

import org.openqa.selenium.WebDriver;
import spting.factory.WebDriverFactory;

public class QuitClose {

    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.create("chrome");

        driver.get("https://ya.ru");

        driver.quit();
        driver.close();
    }
}