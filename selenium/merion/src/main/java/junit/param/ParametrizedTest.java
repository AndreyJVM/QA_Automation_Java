package junit.param;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.merion.aqa.lesson12.ext.InjectWebDriver;
import ru.merion.aqa.lesson12.ext.WebDriverInjector;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@ExtendWith(WebDriverInjector.class)
@InjectWebDriver(browserName = "safari")
public class ParametrizedTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver.get("http://uitestingplayground.com/sampleapp");
    }

    @ParameterizedTest(name = "{index} -> Авторизуемся со значением логина {0}")
    @DisplayName("Успешная авторизация")
    @ValueSource(strings = {"Test", "Тест", "_", "12345", "mail@mail.ru"})
    public void happyPath(String username) {
        driver.findElement(By.cssSelector("[name=UserName]")).sendKeys(username);
        driver.findElement(By.cssSelector("[name=Password]")).sendKeys("pwd");
        driver.findElement(By.cssSelector("#login")).click();

        String msg = driver.findElement(By.cssSelector("#loginstatus")).getText();
        assertEquals("Welcome, " + username + "!", msg);
    }

    @ParameterizedTest(name = "{index} -> Авторизуемся со значением логина {0} и пароля {1} | {arguments}")
    @DisplayName("Неуспешная авторизация")
    @MethodSource("loginAndPassProvider")
    public void tryToAuth(String login, String pass) {
        driver.findElement(By.cssSelector("[name=UserName]")).sendKeys(login);
        driver.findElement(By.cssSelector("[name=Password]")).sendKeys(pass);
        driver.findElement(By.cssSelector("#login")).click();

        String msg = driver.findElement(By.cssSelector("#loginstatus")).getText();
        assertEquals("Invalid username/password", msg);
    }

    static Stream<Arguments> loginAndPassProvider() {
        return Stream.of(
                arguments("", ""),
                arguments("", "pwd"),
                arguments("Test", ""),
                arguments("Test", "_pwd_")
        );
    }
}