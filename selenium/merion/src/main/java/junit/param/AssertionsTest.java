package junit.param;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import ru.merion.aqa.lesson12.ext.ReportBuilder;
import ru.merion.aqa.lesson12.ext.WebDriverInjector;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith({WebDriverInjector.class, ReportBuilder.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AssertionsTest {

    @Test
    @Disabled
    public void eqAssert() {
        int actual = 2 + 2;
        int expected = 4;
        assertEquals(expected, actual, "Математика сломалась");
    }

    @Test
    @Order(1)
    public void arraysEqualityDemo() {
        int[] arrA = {1, 2, 3, 4, 5};
        int[] arrB = {1, 2, 3, 4, 5};
        assertArrayEquals(arrA, arrB);
    }

    @Test
    public void arraysNonEqualityDemo() {
        int[] arrA = {1, 2, 3, 4, 5};
        int[] arrB = {1, 2, 3, 4, 9};
        assertFalse(Arrays.equals(arrA, arrB));
    }

    @Test
    public void checkTwoObjectEquality() {
        User userFromXml = new User(1, "Tester name", "TEST");
        User userFromDb = new User(2, "Tester name", "TEST");
        assertEquals(userFromXml, userFromDb);
    }

    @Test
    public void checkTwoObjectNonEquality() {
        User userFromXml = new User(1, "Tester name", "TEST");
        User userFromDb = new User(2, "Tester name", "TEST");
        assertNotEquals(userFromXml, userFromDb);
    }

    @Test
    public void checkConnectionIsNotSecure() {
        String url = "http://ya.ru";
        assertFalse(url.contains("https"));
    }

    @Test
    public void checkConnectionIsSecure() {
        String url = "https://ya.ru";
        assertTrue(url.contains("https"));
    }

    public WebDriver driver;

    @Test
    @Order(2)
    public void checkDriverIsNull() {
        assertNull(driver);
    }

    @Test
    @Order(3)
    public void checkThrows() {
        assertThrows(NullPointerException.class, () -> driver.get("https://ya.ru"));
    }


}
