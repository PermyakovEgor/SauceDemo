package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage{

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private final By TITLECHECKOUT = By.cssSelector("[data-test=title]");
    private final By FIRSTNAME_FIELD = By.xpath("//*[@data-test='firstName']");
    private final By LASTNAME_FIELD = By.xpath("//*[@data-test='lastName']");
    private final By ZIP_FIELD = By.xpath("//*[@data-test='postalCode']");
    private final By CONTINUE_BUTTON = By.xpath("//*[@data-test='continue']");
    private final By CANCEL_BUTTON = By.xpath("//*[@data-test='cancel']");
    private final By FINISH_BUTTON = By.xpath("//*[@data-test='finish']");
    private final By BACKHOMEBUTTON = By.xpath("//*[@data-test='back-to-products']");

    @Step("Открытие страницы Оформления заказа")
    public String getTitleCheckout() {
        return driver.findElement(TITLECHECKOUT).getText();
    }

    public void inputCheckout(String FirstName, String LastName, String Zip) {
        driver.findElement(FIRSTNAME_FIELD).sendKeys(FirstName);
        driver.findElement(LASTNAME_FIELD).sendKeys(LastName);
        driver.findElement(ZIP_FIELD).sendKeys(Zip);
        driver.findElement(CONTINUE_BUTTON).click();
    }

    @Step("Проверка кнопки Отмена")
    public void putCancel () {
        driver.findElement(CANCEL_BUTTON).click();
    }

    @Step("Проверка кнопки Финиш")
    public void putFinish () {
        driver.findElement(FINISH_BUTTON).click();
    }

    @Step("Проверка кнопки Вернуться Домой")
    public void putBackHome () {
        driver.findElement(BACKHOMEBUTTON).click();
    }

}
