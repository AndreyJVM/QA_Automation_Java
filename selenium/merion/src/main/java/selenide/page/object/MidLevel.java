package selenide.page.object;

import com.codeborne.selenide.Configuration;
import selenide.page.object.midlevel.CartPage;
import selenide.page.object.midlevel.MainPage;
import selenide.page.object.midlevel.ResultPage;

public class MidLevel {

    public static void main(String[] args) {
        Configuration.baseUrl = "https://www.labirint.ru";

        MainPage mainPage = new MainPage();
        mainPage.open();
        mainPage.searchFor("Java");

        ResultPage resultPage = new ResultPage();
        resultPage.addAllItemsToCart();

        CartPage cartPage = new CartPage();
        cartPage.open();
        String text = cartPage.getCartPrice().text();

        System.out.println(text);
    }
}