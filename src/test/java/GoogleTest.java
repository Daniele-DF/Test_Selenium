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


public class GoogleTest  {

    private WebDriver driver;

    @Test
    void googleTest() throws InterruptedException {



        //setup
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        // Creo il browser
        driver = new ChromeDriver(options);



        //action
       driver.get("https://www.google.com");

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15000));

        // Aspetto che il pulsante "Accetta tutto" sia visibile
        wait.until(ExpectedConditions.visibilityOfElementLocated( By.id("L2AGLb"))).click();

        //WebElement input = driver.findElement(By.cssSelector("input[placeholder='Cerca con Google o digita un URL']"));
        //input.sendKeys("Test Automation", Keys.ENTER);
        //driver.findElement(By.id("input")).sendKeys("Test Automation", Keys.ENTER);

     //Assertion
     assertEquals("Google",driver.getTitle());

     // ricerca sulla barra di ricerca di google la parola automation tests
        driver.findElement(By.name("q")).sendKeys("Test Automation", Keys.ENTER);

        // validazione soluzione ottenuta

        WebElement result = driver.findElement(By.cssSelector("h3"));

        assertEquals("Automazione del collaudo del software", result.getText());

     //CleanUp
      driver.quit();
    }
}
