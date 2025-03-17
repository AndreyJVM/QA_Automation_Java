package stepik.properties;

import lombok.SneakyThrows;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private static Properties properties;

    public static Properties getConfiguration() {
        return getConfiguration("config.properties");
    }

    @SneakyThrows
    public static Properties getConfiguration(String filename) {
        try (InputStream input = Configuration.class.getClassLoader().getResourceAsStream(filename)) {

            if (input == null) {
                throw new IllegalArgumentException("Unable to find " + filename);
            }

            properties = new Properties();
            properties.load(input);
        }
        return properties;
    }
}