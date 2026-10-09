import ddf.test.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

import ddf.test.ConfigReader;

public class dartBlu {




        // =========================================================
        // CONFIGURAZIONE
        // =========================================================

        private static final String BASE_URL = "https://dart-blu.onrender.com";

        private WebDriver driver;  //   //WEB DRIVER  permette di controllare il browser, tramite codice

        private FluentWait<WebDriver> wait; // permette di gestire le attese (aspetto l'elemento)

        // =========================================================
        // BEFORE EACH
        // =========================================================

        @BeforeEach
        void setUp() {

            /*

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");

            driver = new ChromeDriver(options);
            */

            driver = DriverFactory.createDriver();


            // Creo il FluentWait
            wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(20)); //attendo l'elemento massimo 10 secondi

            // Rendo disponibile il driver al TestWatcher
            // TestWatcherExtension.driver = driver;

            driver.get(BASE_URL);
        }


        @AfterEach
        void tearDown() {

            if (driver != null) {
                driver.quit();
                driver = null;
            }
        }

        @Test
     void verifyHomePage(){

            //title
            String dartTitle = "Classifica Dart BU Blue";
            WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

            //assert
            assertTrue(title.isDisplayed());
            assertTrue(title.getText().contains(dartTitle));

             //login button
             WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnLogin")));

            //assert
            assertTrue(loginButton.isDisplayed());
            assertTrue(loginButton.isEnabled());


            //signUp button
            //login button
            WebElement signUpButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSignUp")));

            //assert
            assertTrue(signUpButton.isDisplayed());
            assertTrue(signUpButton.isEnabled());




            /*
            //Create user

            //click on signUp button
            signUpButton.click();


            //WebElement cancelButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnCancel")));
            //assertTrue(cancelButton.isDisplayed());
            //assertTrue(cancelButton.isEnabled());

            //click on signUp button
           // signUpButton.click();

            //Create a user
            WebElement buttonSignUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signUpButton")));
            WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nome")));
            WebElement soprannome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("soprannome")));
            WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signUpEmail")));
            WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));

            //assert
            assertTrue(signUpButton.isDisplayed());
            //assertFalse(signUpButton.isEnabled());

            //sendKeys
            name.sendKeys("Test0510");
            soprannome.sendKeys("Ottobre");
            email.sendKeys("test0510@email.com");
            password.sendKeys("Ottobre2026");

            //assert
           assertTrue(buttonSignUp.isEnabled());
           buttonSignUp.click();


             */


            //Last Match button
            WebElement btnLastMatch = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnLastMatch")));

            //assert
            assertTrue(btnLastMatch.isDisplayed());
            assertTrue(btnLastMatch.isEnabled());




            //Months button
            WebElement btnMonths = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnMonths")));

            //assert
            assertTrue(btnMonths.isDisplayed());
            assertTrue(btnMonths.isEnabled());

            //History button
            WebElement btnHistory = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnHistory")));

            //assert
            assertTrue(btnHistory.isDisplayed());
            assertTrue(btnHistory.isEnabled());


            //login

            //recupero credenziali
            String emailLogin = ConfigReader.get("EMAIL_LOGIN");
            String passwordLogin = ConfigReader.get("PASSWORD_LOGIN");

            assertNotNull(emailLogin);
            assertNotNull(passwordLogin);

            // Apro il form di login
            wait.until(ExpectedConditions.elementToBeClickable(By.id("btnLogin"))).click();

            //verifico i campi e i bottoni

            // Campo Email
            WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginEmail")));

            // Campo Password
            WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginPassword")));

            // Bottone Login
            WebElement submitLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginButton")));

            assertFalse(submitLogin.isEnabled());


            email.sendKeys(emailLogin);
            password.sendKeys(passwordLogin);
            submitLogin.click();

            //Verificare che siamo loggati

            WebElement toastSuccess = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("success")));
            assertTrue(toastSuccess.isDisplayed());
            assertTrue(toastSuccess.getText().contains("Login avvenuto con successo!"));

            WebElement userGreeting = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("user-greeting")));

            assertTrue(userGreeting.isDisplayed());
            assertTrue(userGreeting.getText().contains("Benvenuto"));


            WebElement profileButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnProfile")));

            assertTrue(profileButton.isDisplayed());
            assertTrue(profileButton.isEnabled());

            WebElement btnInsert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnInsert")));

            assertTrue(btnInsert.isDisplayed());
            assertTrue(btnInsert.isEnabled());


            WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnLogout")));

            assertTrue(logoutButton.isDisplayed());
            assertTrue(logoutButton.isEnabled());

            logoutButton.click();

            WebElement toastLogout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("success")));

            assertTrue(toastLogout.isDisplayed());

        }

}
