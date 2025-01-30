package task4;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Перейти на сайт http://uitestingplayground.com/textinput
 * Указать в поле ввода текст "Merion"
 * Нажать на синюю кнопку
 * Получить текст кнопки и вывести в консоль (Merion)
 * */
public class RenameButton {

    public static void main(String[] args) {
        open("http://uitestingplayground.com/textinput");

        $("#newButtonName").val("Merion");
        $("#updatingButton").click();

        System.out.println("Button text: " + $("#updatingButton").getText());
    }
}