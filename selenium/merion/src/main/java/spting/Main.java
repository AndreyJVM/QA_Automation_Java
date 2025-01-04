package spting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

public class Main {
    public static void main(String[] args) {


        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addExtensions(new File("selenium/merion/src/main/resources/chrome_extensions/User-Agent-Switcher-for-Chrome-Chrome-Web-Store.crx"));

        WebDriver driver = new ChromeDriver();

        driver.get("https://google.com");
    }
}