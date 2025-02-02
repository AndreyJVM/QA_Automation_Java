package selenide.page.object.midlevel;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CartPage {
    public SelenideElement getCartPrice() {
        return  $(".b-header-b-personal-e-icon-count-m-cart");
    }

    public void open() {
        Selenide.open("/cart/");
    }

}