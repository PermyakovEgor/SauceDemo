package tests;

import io.qameta.allure.Description;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.CardPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPages;
import utils.TestListener;

import java.util.HashMap;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {

    WebDriver driver;
    LoginPage loginPage;
    ProductsPages productsPages;
    CardPage cardPage;
    CheckoutPage checkoutPage;

    @Parameters({"browser"})
    @BeforeMethod (groups = {"smoke"}, description = "Настройка браузера")
    @Description("Настройка браузера")
    public void setUp(@Optional("chrome") String browser, ITestContext iTestContext) {
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
                options.addArguments("--headless");
                driver = new ChromeDriver(options);
            } else if (browser.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless");
                driver = new FirefoxDriver(options);
                driver.manage().window().maximize();
            }

            cardPage = new CardPage(driver);
            checkoutPage = new CheckoutPage(driver);
            loginPage = new LoginPage(driver);
            productsPages = new ProductsPages(driver);

            iTestContext.setAttribute("driver", driver);

        } catch (Exception e) {
            System.err.println("Ошибка в setUp(): " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @AfterMethod (groups = {"smoke"}, description = "Закрытие браузера")
    @Description("Закрытие браузера")
    public void tearDawn() {
        if (driver != null) {
            driver.quit();
        }
    }
}
