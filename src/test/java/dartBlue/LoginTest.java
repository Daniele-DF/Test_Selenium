package dartBlue;

import ddf.test.DartBluPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ddf.test.ConfigReader;

public class LoginTest {

    private static final String BASE_URL = "https://dart-blu.onrender.com";

    private WebDriver driver;

    private DartBluPage dartBluPage;


    // =========================
    // SETUP
    // =========================

    @BeforeEach
    void setUp() {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);

        dartBluPage = new DartBluPage(driver);

        driver.get(BASE_URL);
    }


    // =========================
    // LOGIN TEST
    // =========================

    @Test
    void loginTest() {

        // =========================
        // GET CREDENTIALS
        // =========================

        String emailLogin = ConfigReader.get("EMAIL_LOGIN");

        String passwordLogin =
                ConfigReader.get("PASSWORD_LOGIN");

        assertNotNull(emailLogin);
        assertNotNull(passwordLogin);


        // =========================
        // LOGIN
        // =========================

        dartBluPage.clickLogin();

        assertFalse(dartBluPage.getSubmitLogin().isEnabled());

        dartBluPage.enterEmail(emailLogin);

        dartBluPage.enterPassword(passwordLogin);

        dartBluPage.submitLogin();


        // =========================
        // VERIFY LOGIN
        // =========================

        assertTrue(dartBluPage.getSuccessMessage().isDisplayed());

        assertTrue(dartBluPage.getSuccessMessage()
                        .getText()
                        .contains("Login avvenuto con successo!")
        );

        assertTrue(dartBluPage.getUserGreeting().isDisplayed());

        assertTrue(dartBluPage.getUserGreeting()
                        .getText()
                        .contains("Benvenuto")
        );


        // =========================
        // LOGGED USER
        // =========================

        assertTrue(dartBluPage.getProfileButton().isDisplayed());

        assertTrue(dartBluPage.getProfileButton().isEnabled());

        assertTrue(dartBluPage.getInsertButton().isDisplayed());

        assertTrue(dartBluPage.getInsertButton().isEnabled());

        assertTrue(dartBluPage.getLogoutButton().isDisplayed());

        assertTrue(dartBluPage.getLogoutButton().isEnabled());


        // =========================
        // LOGOUT
        // =========================

        dartBluPage.logout();


        // =========================
        // VERIFY LOGOUT
        // =========================

        assertTrue(dartBluPage.getSuccessMessage().isDisplayed());
    }


    // =========================
    // CLEANUP
    // =========================

    @AfterEach
    void tearDown() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}