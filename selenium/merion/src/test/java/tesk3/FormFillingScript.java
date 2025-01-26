package tesk3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import tesk3.page.RegisterPage;

public class FormFillingScript {
    /**
     * Открыть страницу https://bonigarcia.dev/selenium-webdriver-java/data-types.html
     * Заполнить форму значениями
     * Нажать кнопку Submit
     * Вывести в консоль цвет полей Zip code, E-mail и Phone (background-color)
     * */
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        RegisterPage registerPage = new RegisterPage(driver);

        try {
            registerPage.open();

            registerPage.set("first-name", "Иван");
            registerPage.set("last-name", "Петров");
            registerPage.set("address", "Ленина, 55-3");
            registerPage.set("city", "Москва");
            registerPage.set("country", "Москва");
            registerPage.set("job-position", "QA");
            registerPage.set("company", "Merion");

            registerPage.clickTypeSubmit();

            registerPage.getCssProperty("#zip-code", "background-color");
            registerPage.getCssProperty("#e-mail", "background-color");
            registerPage.getCssProperty("#phone", "background-color");
        } finally {
            driver.quit();
        }
    }
}