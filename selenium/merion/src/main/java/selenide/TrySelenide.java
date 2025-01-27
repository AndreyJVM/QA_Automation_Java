package selenide;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.*;

public class TrySelenide {
    public static void main(String[] args) {

        open("https://habr.com/ru");

        String title = Selenide.title();
        System.out.println("Title: " + title);

        $("#test");
        $$("#li");
        $x("Hello");
        $$x("List");

    }
}