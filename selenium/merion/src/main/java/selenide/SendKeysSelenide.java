package selenide;

import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class SendKeysSelenide {
    public static void main(String[] args) {

        open("http://uitestingplayground.com/textinput");

        $("#newButtonName").val("Merion");

        $("#newButtonName").press(Keys.BACK_SPACE);

        $("#newButtonName").press(Keys.SHIFT, Keys.ARROW_UP);

       $("#updatingButton").click();
    }
}
