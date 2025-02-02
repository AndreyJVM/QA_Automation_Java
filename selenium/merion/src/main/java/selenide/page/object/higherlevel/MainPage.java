package selenide.page.object.higherlevel;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;

public class MainPage extends BasePage {

    public MainPage open() {
        Selenide.open("/");
        Cookie cookie = new Cookie("cookie_policy", "1");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        return this;
    }
}