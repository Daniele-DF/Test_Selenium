
package dartBlue;
import ddf.test.DartBluPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SignUpTest {
/*

    private static final String BASE_URL = "https://dart-blu.onrender.com";

    private WebDriver driver;

    private DartBluPage dartBluPage;


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


    @AfterEach
    void tearDown() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Test
    void signUpTest() {

        // SETUP
        String name = "Test0510";
        String nickname = "Ottobre";
        String email = "test0510@email.com";
        String password = "Ottobre2026";

        // ASSERT - Sign Up button
        assertTrue(dartBluPage.getSignUpButton().isDisplayed());
        assertTrue(dartBluPage.getSignUpButton().isEnabled());

        // ACTION - click Sign Up
        dartBluPage.clickSignUp();

        // ACTION - create user
        dartBluPage.enterName(name);
        dartBluPage.enterNickname(nickname);
        dartBluPage.enterSignUpEmail(email);
        dartBluPage.enterSignUpPassword(password);

        // ASSERT
        assertTrue(dartBluPage.getSignUpSubmitButton().isDisplayed());
        assertTrue(dartBluPage.getSignUpSubmitButton().isEnabled());

        // ACTION
        dartBluPage.submitSignUp();
    }

 */
}


