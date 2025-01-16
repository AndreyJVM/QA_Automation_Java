package task2;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

@Execution(ExecutionMode.CONCURRENT)
public class OrderMarket {

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

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

        driver.findElement(By.cssSelector("#user-name")).sendKeys(username);
        driver.findElement(By.cssSelector("#password")).sendKeys(postalCode);
        driver.findElement(By.cssSelector("#login-button")).click();

        List<WebElement> items = driver.findElements(By.cssSelector(".inventory_item"));

        for (WebElement item : items) {
            String productName = item.findElement(By.cssSelector(".inventory_item_name")).getText();
            if(itemNames.contains(productName)){
                item.findElement(By.cssSelector("button")).click();
            }
        }

        driver.findElement(By.cssSelector("#shopping_cart_container")).click();

        driver.findElement(By.cssSelector("#checkout")).click();

        driver.findElement(By.cssSelector("#first-name")).sendKeys(firstName);
        driver.findElement(By.cssSelector("#last-name")).sendKeys(secondName);
        driver.findElement(By.cssSelector("#postal-code")).sendKeys(postalCode);

        driver.findElement(By.cssSelector("#continue")).click();

        String total = driver.findElement(By.cssSelector(".summary_total_label")).getText();

        driver.quit();

        System.out.println(total);
    }
}