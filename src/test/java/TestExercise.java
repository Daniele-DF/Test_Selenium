import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*

FluentWait  permette di creare un'attesa esplicita e configurabile.
Posso definire un timeout massimo con withTimeout,
l'intervallo tra i controlli con pollingEvery,
eventuali eccezioni da ignorare con ignoring e la condizione da attendere tramite until.
Se la condizione non viene soddisfatta entro il timeout, viene generata una TimeoutException.


WebDriver è un'interfaccia di Selenium che permette di interagire con e controllare il browser.

*/


public class TestExercise {


    // =========================================================
    // CONFIGURAZIONE
    // =========================================================

    private static final String BASE_URL = "https://automationteststore.com/";

    private WebDriver driver;  //   //WEB DRIVER  permette di controllare il browser, tramite codice

    private FluentWait<WebDriver> wait; // permette di gestire le attese (aspetto l'elemento)

    // =========================================================
    // BEFORE EACH
    // =========================================================

    @BeforeEach
    void setUp() {

        // Creo una nuova istanza di Chrome
        driver = new ChromeDriver();

        // Apro Chrome a tutto schermo
        driver.manage().window().maximize();

        // Creo il FluentWait
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10)); //attendo l'elemento massimo 10 secondi

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
    void TestOne() {


        /* Displayed and Visible */

        //section APPAREL & ACCESSORIES.
        WebElement apparel = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("APPAREL & ACCESSORIES")));

        // elementToBeClickable : Selenium aspetta che l'elemento sia cliccabile.

        assertTrue(apparel.isDisplayed());
        assertTrue(apparel.isEnabled());



        //APPAREAL Categories
        //get and click first elements of product categories

        apparel.click();

        //first element shoes
       // List<WebElement> products = driver.findElements(By.cssSelector("a.prdocutname"));
       // WebElement firstProduct = products.get(0);
        //firstProduct.click();


        WebElement shoes = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Shoes")));
        shoes.click();

        // click on Fiorella Purple Peep Toes (don't work)


        // By.cssSelector
        List<WebElement> purpleShoes = driver.findElements(By.cssSelector("a[href*='product_id=115']"));
        WebElement FirstpurpleShoes = purpleShoes.get(0);
        FirstpurpleShoes.click();

        //choose the size

        String SelectedSize = "40";
        WebElement sizeElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("option342")));
        Select size = new Select(sizeElement);
        size.selectByVisibleText(SelectedSize);

        WebElement otherShoes = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("ul.breadcrumb a[href*='path=68_69']")));
        otherShoes.click();

        //add to cart
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.productcart[data-id='118']")));
        addToCart.click();

       //add to cart detail of product
       wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.cart"))).click();

       //section MAKEUP

       WebElement makeup = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("MAKEUP")));
       assertTrue(makeup.isDisplayed());
       assertTrue(makeup.isEnabled());
       makeup.click();

       //acquistare 3 volte l'elemento

       wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.productcart[data-id='56']"))).click();

       //aggiungere l'elemento 3 volte nel carrello

       String quantityProduct = "3";
       WebElement quantity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("product_quantity")));

       quantity.clear();
       quantity.sendKeys(quantityProduct);

       wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.cart"))).click();

       // Total cart home
       WebElement homeTotal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart_total")));

       String homePrice = homeTotal.getText();

       System.out.println("Totale home: " + homePrice);

       homeTotal.click();


// Sub-Total

       WebElement subTotalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='totals_table']//tr[td//span[contains(text(),'Sub-Total:')]]/td[2]/span")));

       String subTotalPrice = subTotalElement.getText();

       System.out.println("Sub-Total: " + subTotalPrice);

       assertEquals(homePrice, subTotalPrice);


// Converto Sub-Total in double

       double subTotalValue = Double.parseDouble(subTotalPrice.replace("$", ""));


// Shipping

       WebElement shippingElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='totals_table']//tr[td//span[contains(text(),'Flat Shipping Rate:')]]/td[2]/span")));
       double shipping = Double.parseDouble(shippingElement.getText().replace("$", ""));


// Total
       WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='totals_table']//tr[td//span[text()='Total:']]/td[2]/span")));
       double total = Double.parseDouble(totalElement.getText().replace("$", ""));


// Calcolo
       double calculatedTotal = subTotalValue + shipping;

       System.out.println("Shipping: " + shipping);
       System.out.println("Calculated Total: " + calculatedTotal);
       System.out.println("Total: " + total);


// Verifica
       assertEquals(total, calculatedTotal, 0.01);


 //rimuovere tutti gli elementi nel carrello

        while (!driver.findElements(By.cssSelector("a.btn.btn-sm.btn-default")).isEmpty()) {
            WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.btn.btn-sm.btn-default")));
            removeButton.click();
        }


        // verifico che il totale si sia aggiornato di conseguenza, ovvero che adesso sia uguale a 0.00
        WebElement homeUpdateTotal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart_total")));
        double updateHammerPrice = Double.parseDouble(homeUpdateTotal.getText().replace("$", ""));


        assertEquals(0.00,updateHammerPrice);
        System.out.println("Totale home: " + updateHammerPrice);

        //verifico che non ci sono più elementi
        assertTrue(driver.findElements(By.cssSelector("a.btn.btn-sm.btn-default")).isEmpty());


        // Verifico che non ci siano più elementi
        assertTrue(driver.findElements(By.cssSelector("a.btn.btn-sm.btn-default")).isEmpty());

      // Verifico che nella sezione carrello sia presente il testo "Your shopping cart is empty!"

        WebElement cartContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".contentpanel")));
        assertTrue(cartContent.getText().contains("Your shopping cart is empty!"));

    }


    @Test
    void TestTwo() {


        // ricerca book e click
        WebElement searchInput = wait.until( ExpectedConditions.visibilityOfElementLocated(By.id("filter_keyword")));
        searchInput.sendKeys("book");
        wait.until( ExpectedConditions.elementToBeClickable( By.cssSelector(".button-in-search"))).click();

        //add to cart

        wait.until( ExpectedConditions.elementToBeClickable( By.cssSelector("a.cart"))).click();

        // 6. Verificare che nel carrello ci sia 1 elemento
        String[] quantities = {"0", "1"};
        String qty =  quantities[1];

        WebElement cartItems = wait.until( ExpectedConditions.visibilityOfElementLocated( By.cssSelector(".topcart .label-orange") ) );
        assertEquals(qty, cartItems.getText());

        //remove Element
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.btn.btn-sm.btn-default"))).click();

        //check number of items in the cart
        WebElement cartItemsAfterRemove = wait.until( ExpectedConditions.visibilityOfElementLocated( By.cssSelector(".topcart .label-orange")));
        // check if the cart is empty
        String empty = quantities[0];
        assertEquals(empty, cartItemsAfterRemove.getText());

    }

    @Test
    void TestThree() {


        //cliccare su books
        wait.until( ExpectedConditions.elementToBeClickable( By.cssSelector("a[href*='path=65']") ) ).click();

        // 3. Salvare il primo item recuperato
        WebElement firstItem = wait.until( ExpectedConditions.visibilityOfElementLocated( By.cssSelector(".prdocutname")));
        String firstProduct = firstItem.getText();

        // 4. Cambiare ordine di visualizzazione

        // Rating Lowest

        WebElement sortSelect = wait.until( ExpectedConditions.visibilityOfElementLocated( By.id("sort")));
        Select select = new Select(sortSelect);
        select.selectByValue("rating-ASC");

        // 5. Salvare il primo item recuperato
        // dopo il cambio di ordinamento

        WebElement secondItem = wait.until( ExpectedConditions.visibilityOfElementLocated( By.cssSelector(".prdocutname") ) );
        String secondProduct = secondItem.getText();

        // 6. Controllare che il primo item sia diverso dal secondo item
        assertNotEquals(firstProduct, secondProduct);


    }


    @Test
    void TestFour() {


         // 2. Scroll in basso alla pagina
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

            // 3. Cliccare su "Site Map"
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='content/sitemap']"))).click();

            // 4. Trovare "Shampoo" e cliccare
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Shampoo"))).click();

            // 5. Cliccare "Eau Parfumee au The Vert Shampoo"
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[title='Eau Parfumee au The Vert Shampoo']"))).click();

            // 6. Controllare che il Model sia 522823
            String mod = "522823";
            WebElement model = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".productinfo li:nth-child(2)")));

            assertEquals(mod, model.getText().replace("Model:", "").trim());

            // 7. Controllare che ci sia il button "Add to Cart"
            WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.cart")));
            assertEquals("Add to Cart", addToCart.getText());

            // 8. Controllare che ci sia il button "Print"
            WebElement printButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.productprint")));
            assertEquals("Print", printButton.getText());

            // 9. Controllare che la description non sia vuota
            WebElement description = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#description p")));
            assertFalse(description.getText().trim().isEmpty());


    }


    @Test
    void TestFive() {


            // 2. Cliccare su Specials
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.menu_specials"))).click();

            // 3. Controllare che ci sia l'elemento "sale"
            List<WebElement> saleElements = driver.findElements(By.cssSelector(".sale"));
            assertFalse(saleElements.isEmpty());

    }


    @Test
    void TestEight() {


            // 2. Cliccare sulla barra di ricerca
            WebElement searchBar = wait.until(ExpectedConditions.elementToBeClickable(By.id("filter_keyword")));
            searchBar.click();

            // 3. Scrivere "qwerty"
            searchBar.sendKeys("qwerty");

            // Premere il pulsante di ricerca
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".button-in-search"))).click();

            // 4. Controllare che nessun risultato sia recuperato
            List<WebElement> results = driver.findElements(By.cssSelector(".fixed_wrapper .prdocutname"));

            assertTrue(results.isEmpty());

    }


}





