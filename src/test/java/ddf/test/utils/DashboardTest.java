package ddf.test.utils;
import ddf.test.DartBluPage;
import ddf.test.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DashboardTest {

    private static final String BASE_URL = "https://dart-blu.onrender.com";

    private WebDriver driver;

    private DartBluPage dartBluPage;


    // =========================
    // SETUP
    // =========================

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

        dartBluPage = new DartBluPage(driver);

        driver.get(BASE_URL);
    }


    @AfterEach
    void tearDown() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }


    // =========================
    // TEST
    // =========================

    @Test
    void verifyHomePage() {

        // =========================
        // HOME PAGE
        // =========================

        String dartTitle = "Classifica Dart BU Blue";

        assertTrue(dartBluPage.getTitle().isDisplayed());

        assertTrue(dartBluPage.getTitle().getText().contains(dartTitle));


        // =========================
        // BUTTONS
        // =========================

        assertTrue(dartBluPage.getLoginButton().isDisplayed());

        assertTrue(dartBluPage.getLoginButton().isEnabled());

        assertTrue(dartBluPage.getSignUpButton().isDisplayed());

        assertTrue(dartBluPage.getSignUpButton().isEnabled());

        assertTrue(dartBluPage.getLastMatchButton().isDisplayed());

        assertTrue(dartBluPage.getLastMatchButton().isEnabled());

        assertTrue(dartBluPage.getMonthsButton().isDisplayed());

        assertTrue(dartBluPage.getMonthsButton().isEnabled());

        assertTrue(dartBluPage.getHistoryButton().isDisplayed());

        assertTrue(dartBluPage.getHistoryButton().isEnabled());



      }
    }
