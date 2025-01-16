package task2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

/**
 * Открыть сайт магазина https://www.saucedemo.com/
 * Авторизоваться под пользователем standard_user
 * Добавить в корзину товары:
 *      Sauce Labs Backpack
 *      Sauce Labs Bolt T-Shirt
 *      Sauce Labs Onesie
 * Перейти в корзину
 * Нажать Checkout
 * Заполнить форму данными:
 *      Имя
 *      Фамиля
 *      Почтовый индекс
 * Нажать continue
 * Прочитать со стрницы итоговую стоимость ( Total )
 * Закрыть браузер
 * Вывести в консоль итоговую стоимость ```
 * */

public class OrderMarket {

    public static void main(String[] args) {

        String username = "standard_user";
        String password = "secret_sauce";

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        driver.findElement(By.cssSelector("#user-name"))
                        .sendKeys(username);
        driver.findElement(By.cssSelector("#password"))
                        .sendKeys(password);
        driver.findElement(By.cssSelector("#login-button"))
                        .click();



        driver.quit();
    }
}
