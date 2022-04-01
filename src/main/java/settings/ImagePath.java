package settings;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Путь к фото на резюме
 *
 */
public class ImagePath {
    private static Properties properties = new Properties();
    private static ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    private static InputStream is = classLoader.getResourceAsStream("img.properties");

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