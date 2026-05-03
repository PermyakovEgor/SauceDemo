package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
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

    @Parameters({"browser"})
    @BeforeMethod (groups = {"smoke"})
    public void setUp(@Optional("chrome") String browser) {
        try {
            if (browser.equalsIgnoreCase("chrome")) {
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
            } else if (browser.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
            }


            cardPage = new CardPage(driver);
            checkoutPage = new CheckoutPage(driver);
            loginPage = new LoginPage(driver);
            productsPages = new ProductsPages(driver);
        } catch (Exception e) {
            System.err.println("Ошибка в setUp(): " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @AfterMethod (groups = {"smoke"})
    public void tearDawn() {
        if (driver != null) {
            driver.quit();
        }
    }
}
