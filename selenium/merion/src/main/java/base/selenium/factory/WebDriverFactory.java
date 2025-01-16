package base.selenium.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.io.File;

public class WebDriverFactory {

    public static WebDriver create(String browserName) {

        if (browserName.equalsIgnoreCase("chrome")) {
            return create(new ChromeOptions());
        }

        if (browserName.equalsIgnoreCase("firefox")) {
            return create(new FirefoxOptions());
        }

        if (browserName.equalsIgnoreCase("safari")) {
            return create(new SafariOptions());
        }

        if (browserName.equalsIgnoreCase("edge")) {
            return create(new EdgeOptions());
        }

        throw new IllegalArgumentException("Don`t support browser: " + browserName + "[chrome|firefox|safari|edge]");
    }


    public static WebDriver create(ChromeOptions options) {
        ChromeOptions defualtChromeOptions = new ChromeOptions();
        defualtChromeOptions.addExtensions(new File("selenium/merion/src/main/resources/chrome_extensions/User-Agent-Switcher-for-Chrome-Chrome-Web-Store.crx"));

        return new ChromeDriver(defualtChromeOptions.merge(options));
    }

    public static WebDriver create(FirefoxOptions options) {
        return new FirefoxDriver(new FirefoxOptions().merge(options));
    }

    public static WebDriver create(SafariOptions options) {
        return new SafariDriver(new SafariOptions().merge(options));
    }

    public static WebDriver create(EdgeOptions options) {
        return new EdgeDriver(new EdgeOptions().merge(options));
    }
}