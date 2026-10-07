import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*

FluentWait  permette di creare un'attesa esplicita e configurabile.
Posso definire un timeout massimo con withTimeout,
l'intervallo tra i controlli con pollingEvery,
eventuali eccezioni da ignorare con ignoring e la condizione da attendere tramite until.
Se la condizione non viene soddisfatta entro il timeout, viene generata una TimeoutException.


WebDriver è un'interfaccia di Selenium che permette di interagire con e controllare il browser.

*/

public class GoogleTest {

    private WebDriver driver;

    @Test
    void googleTest() {

        // SETUP
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(500));

        // ACTION
        driver.get("https://www.google.com");

        // Aspetto il pulsante "Accetta tutto"
       // wait.until(ExpectedConditions.elementToBeClickable(By.id("L2AGLb"))).click();

        // ASSERT
        assertEquals("Google", driver.getTitle());

        // Ricerca
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));

        searchBox.sendKeys("Test Automation", Keys.ENTER);

        // Aspetto che compaia il risultato
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3")));

        assertEquals("Automazione del collaudo del software", result.getText());

        // CLEANUP
        driver.quit();
    }
}