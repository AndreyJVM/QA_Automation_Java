package selenide.config;

import com.codeborne.selenide.*;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.*;

public class ElementsCollectionsDemo {

    public static void main(String[] args) {
        Configuration.baseUrl = "https://www.labirint.ru";

        open("/");
        Cookie cookie = new Cookie("cookie_policy", "1");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);

        open("/");
        $("#search-field").val("Java").pressEnter();

        ElementsCollection cards = $$(".product-card");
        cards.forEach(card -> card.find(".buy-link").click());

        $(".b-header-b-personal-e-icon-count-m-cart")
                .shouldHave(Condition.text(String.valueOf(cards.size())));

        String text = $(".b-header-b-personal-e-icon-count-m-cart").getText();

        refresh();
    }
}
