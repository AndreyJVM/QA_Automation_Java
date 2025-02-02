package selenide.page.object.higherlevel;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class ResultPage extends BasePage {

    private final ElementsCollection productCart = $$(".product-card .buy-link");

    public void addAllItemsToCart() {
    }
}
