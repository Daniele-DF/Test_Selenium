
package ddf.test;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("File config.properties non trovato");
            }

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file config.properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}