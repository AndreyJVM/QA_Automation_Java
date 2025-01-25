package tesk3;

import org.openqa.selenium.By;
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

            driver.findElement(By.cssSelector("[name=first-name]")).sendKeys("Иван");
            driver.findElement(By.cssSelector("[name=last-name]")).sendKeys("Петров");
            driver.findElement(By.cssSelector("[name=address]")).sendKeys("Ленина, 55-3");
            driver.findElement(By.cssSelector("[name=city]")).sendKeys("Москва");
            driver.findElement(By.cssSelector("[name=country]")).sendKeys("Россия");
            driver.findElement(By.cssSelector("[name=job-position]")).sendKeys("QA");
            driver.findElement(By.cssSelector("[name=company]")).sendKeys("Merion");

            driver.findElement(By.cssSelector("[type=submit]"))
                    .click();

            System.out.println("zip_code_bg: " + driver.findElement(By.cssSelector("#zip-code")).getCssValue("background-color"));
            System.out.println("email_bg: " + driver.findElement(By.cssSelector("#e-mail")).getCssValue("background-color"));
            System.out.println("phone_bg: " + driver.findElement(By.cssSelector("#phone")).getCssValue("background-color"));
        } finally {
            driver.quit();
        }
    }
}