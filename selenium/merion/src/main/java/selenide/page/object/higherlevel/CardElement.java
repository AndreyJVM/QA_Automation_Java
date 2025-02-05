package selenide.page.object.higherlevel;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CardElement {

    private final SelenideElement title = $(".product-card__name");
    private final SelenideElement buyButton = $(".btn-tocart .buy-link");

    public String getTitle() {
        return title.text();
    }

    public CardElement addToCart() {
        buyButton.click();
        return this;
    }
}
