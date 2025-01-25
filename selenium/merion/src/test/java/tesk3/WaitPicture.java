package tesk3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import tesk3.page.ImageGalleryPage;

import java.time.Duration;
import java.util.List;

/**
 * Перейти на сайт https://bonigarcia.dev/selenium-webdriver-java/loading-images.html
 * Дождаться загрузки 3й картинки
 * Получить значение атрибута src у 3й картинки
 * Вывести значение в консоль
 */

public class WaitPicture {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        ImageGalleryPage galleryPage = new ImageGalleryPage(driver);

        try {
            galleryPage.open();
            String src = galleryPage.getImageProperty(2, "src");
            System.out.println(src);

        } finally {
            driver.quit();
        }
    }
}