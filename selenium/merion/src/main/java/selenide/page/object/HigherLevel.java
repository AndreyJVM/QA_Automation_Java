package selenide.page.object;

import com.codeborne.selenide.Configuration;

import selenide.page.object.higherlevel.CartPage;
import selenide.page.object.higherlevel.MainPage;

public class HigherLevel {

    public static void main(final String[] args) {
        Configuration.baseUrl = "https://www.labirint.ru";

        new MainPage()
                .open()
                .getHeader()
                .searchFor("Java")
                .addAllItemsToCart();

        String text = new CartPage().open().getCartPrice();

        System.out.println(text);
    }
}
