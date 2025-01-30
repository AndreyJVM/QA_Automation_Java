package task3;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import task3.page.AuthPage;
import task3.page.CartCheckoutPage;
import task3.page.CatalogPage;

import java.util.HashSet;
import java.util.Set;

/**
 * Открыть сайт магазина https://www.saucedemo.com/
 * Авторизоваться под пользователем standard_user
 * Добавить в корзину товары:
 * Sauce Labs Backpack
 * Sauce Labs Bolt T-Shirt
 * Sauce Labs Onesie
 * Перейти в корзину
 * Нажать Checkout
 * Заполнить форму данными:
 * Имя
 * Фамиля
 * Почтовый индекс
 * Нажать continue
 * Прочитать со стрницы итоговую стоимость ( Total )
 * Закрыть браузер
 * Вывести в консоль итоговую стоимость ```
 */

@Execution(ExecutionMode.CONCURRENT)
public class OrderMarket {
    WebDriver driver;
    CatalogPage catalogPage;
    AuthPage authPage;

    @RepeatedTest(3)
    void getTotalPrice() {

        Set<String> itemNames = new HashSet<>();
        itemNames.add("Sauce Labs Backpack");
        itemNames.add("Sauce Labs Bolt T-Shirt");
        itemNames.add("Sauce Labs Onesie");

        String username = "standard_user";
        String password = "secret_sauce";
        String firstName = "Andrey";
        String secondName = "Raw";
        String postalCode = "1011010";

        driver = new ChromeDriver();

        try {
            authPage = new AuthPage(driver).open();

            catalogPage = authPage.loginAs(username, password);
            catalogPage.addItems(itemNames);

            String total = new CartCheckoutPage(driver)
                    .open()
                    .clickCheckout()
                    .setContactData(firstName, secondName, postalCode)
                    .getTotalPrice();

            System.out.println(total);
        } finally {
            driver.quit();
        }
    }
}