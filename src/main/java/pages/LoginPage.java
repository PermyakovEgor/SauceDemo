package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage{

    /*
    1. Описываем в классе элементы, с которыми мы взаимодействуем
    2. Описываем методы взаимодействия с этими элементами
     */

    private final By USERNAME_FIELD = By.xpath("//*[@data-test='username']");
    private final By PASSWORD_FIELD = By.xpath("//*[@data-test='password']");
    private final By LOGIN_BUTTON = By.xpath("//*[@data-test='login-button']");
    private final By ERROR_MESSAGE = By.xpath("//*[@data-test='error']");
    private final By LOGIN_PAGE = By.id("root");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы Login")
    @Override
    public LoginPage isPageOpened() {
        driver.get(BASE_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_PAGE));
        return this;
    }

    @Step("Вход в магазин с именем пользователя '{user}' и паролем '{password}'")
    public ProductsPages login(String user, String password) {
        driver.findElement(USERNAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new ProductsPages(driver);
    }

    @Step("Получение текста ошибки")
    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
