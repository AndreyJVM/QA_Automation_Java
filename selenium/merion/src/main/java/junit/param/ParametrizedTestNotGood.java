package junit.param;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.merion.aqa.WebDriverFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ParametrizedTestNotGood {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = WebDriverFactory.create();
        driver.get("http://uitestingplayground.com/sampleapp");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @ParameterizedTest(name = "{index} -> Авторизуемся со значением логина {0} и пароля {1} | {arguments}")
    @DisplayName("Авторизация")
    @MethodSource("loginAndPassProvider")
    public void tryToAuth(String login, String pass, boolean isHappyPath) {
        driver.findElement(By.cssSelector("[name=UserName]")).sendKeys(login);
        driver.findElement(By.cssSelector("[name=Password]")).sendKeys(pass);
        driver.findElement(By.cssSelector("#login")).click();

        String msg = driver.findElement(By.cssSelector("#loginstatus")).getText();

        if (isHappyPath) {
            assertEquals("Welcome, " + login + "!", msg);
        } else {
            assertEquals("Invalid username/password", msg);
        }
    }

    static Stream<Arguments> loginAndPassProvider() {
        return Stream.of(
                arguments("Test", "pwd", true),
                arguments("Тест", "pwd", true),
                arguments("12345", "pwd", true),
                arguments("mail@mail.ru", "pwd", true),
                arguments("", "", false),
                arguments("", "pwd", false),
                arguments("Test", "", false),
                arguments("Test", "_pwd_", false)
        );
    }
}
