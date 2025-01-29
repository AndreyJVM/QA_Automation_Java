package selenide.config;

import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.open;

public class ConfigDemo {
    public static void main(String[] args) {
        /// Change settings selenide

        /// Change default timeout 4 sec -> 8 sec
        /// Default value: 4000 (milliseconds)
        Configuration.timeout = 8 * 1000; // 1sec <=> 1000ms

        /// Default: false
        /// true -> ignore UI interface
        Configuration.headless = true;

        /// Which browser to use. Can be configured either programmatically, via selenide. properties file or by system property "-Dselenide. browser=ie".
        /// Supported values: "chrome", "firefox", "ie", "edge", "safari".
        /// Default value: "chrome"
        Configuration.browser = "firefox";





        open("https://habr.com/ru");
    }
}