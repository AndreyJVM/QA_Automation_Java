package tesk3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import tesk3.page.AjaxPage;

import java.time.Duration;

/**
 * Перейти на страницу http://uitestingplayground.com/ajax
 * Нажать на синюю кнопку
 * Получить текст из зеленой плашки
 * Вывести его в консоль (”Data loaded with AJAX get request.”)
 * */

public class ClickOnButton {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        AjaxPage ajaxPage = new AjaxPage(driver);

        ajaxPage.open();
        ajaxPage.clickTheButton();
        String textRequest = ajaxPage.getContent();

        System.out.println("Text from the green field: \"" + textRequest +"\"");

        driver.quit();
    }
}