package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import pages.CardPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPages;

import java.util.HashMap;

public class BaseTest {

    WebDriver driver;
    LoginPage loginPage;
    ProductsPages productsPages;
    CardPage cardPage;
    CheckoutPage checkoutPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);

        loginPage = new LoginPage(driver);
        productsPages = new ProductsPages(driver);
        cardPage = new CardPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @AfterMethod (alwaysRun = true)
    public void tearDawn() {
        driver.quit();
    }
}
