
package ddf.test;

import java.net.URI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

    public static WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        String remoteUrl = System.getenv("SELENIUM_REMOTE_URL");

        if (remoteUrl != null && !remoteUrl.isBlank()) {
            try {
                return new RemoteWebDriver(
                        URI.create(remoteUrl).toURL(),
                        options
                );
            } catch (Exception e) {
                throw new IllegalStateException(
                        "Impossibile collegarsi al browser Selenium remoto",
                        e
                );
            }
        }

        return new ChromeDriver(options);
    }
}