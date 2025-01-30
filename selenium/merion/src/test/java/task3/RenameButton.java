package task3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import task3.page.TextInputPage;

/**
 * Перейти на сайт http://uitestingplayground.com/textinput
 * Указать в поле ввода текст "Merion"
 * Нажать на синюю кнопку
 * Получить текст кнопки и вывести в консоль (Merion)
 */
public class RenameButton {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        TextInputPage inputPage = new TextInputPage(driver);

        try {
            inputPage
                    .open()
                    .setButtonName("Merion")
                    .clickUpdatingButton();

            String buttonText = inputPage.getButtonText();

            System.out.println("Button text: " + buttonText);
        } finally {
            driver.quit();
        }
    }
}