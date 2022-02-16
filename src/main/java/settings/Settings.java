package settings;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Настройки
 * Пока работаем с файлом resource/app.properties
 */
public class Settings {
    private static Properties properties = new Properties();
    private static ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    private static InputStream is = classLoader.getResourceAsStream("app.properties");

    static {
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Этот метод затачиваем под путь к картинкам
     */
    public static String getImagePath() {
        return properties.getProperty("ImagePath");
    }
}