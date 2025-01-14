package task2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Перейти на сайт http://uitestingplayground.com/textinput
 * Указать в поле ввода текст "Merion"
 * Нажать на синюю кнопку
 * Получить текст кнопки и вывести в консоль (Merion)
 * */
public class RenameButton {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("http://uitestingplayground.com/textinput");


    }
}
