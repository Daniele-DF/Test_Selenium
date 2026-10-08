package ddf.test;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input != null) {
                properties.load(input);
            }

        } catch (Exception e) {
            throw new RuntimeException(
                    "Errore nella lettura del file config.properties",
                    e
            );
        }
    }

    public static String get(String key) {

        // Prima prova a leggere dal config.properties locale
        String value = properties.getProperty(key);

        // Se non trovato, prova a leggere dalle variabili d'ambiente
        if (value == null || value.isBlank()) {
            value = System.getenv(key);
        }

        // Se non trovato da nessuna parte, genera errore
        if (value == null || value.isBlank()) {
            throw new RuntimeException(
                    "Configurazione non trovata per la chiave: " + key
            );
        }

        return value;
    }
}