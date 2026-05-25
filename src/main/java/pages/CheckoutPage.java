package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
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


    @Step("Открытие сайдпейджа оформления заказа")
    @Override
    public CheckoutPage isPageOpened() {
        log.info("open Checkout page");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLECHECKOUT));
        return this;
    }

    @Step("Возврат текста TITLE сайдпейджа оформления заказа")
    public String getTitleCheckout() {
        log.info("return title Checkout page");
        isPageOpened();
        return driver.findElement(TITLECHECKOUT).getText();
    }

    public CheckoutPage inputCheckout(String FirstName, String LastName, String Zip) {
        log.info("Writing checkout form with name: '{}'", FirstName);
        driver.findElement(FIRSTNAME_FIELD).sendKeys(FirstName);
        driver.findElement(LASTNAME_FIELD).sendKeys(LastName);
        driver.findElement(ZIP_FIELD).sendKeys(Zip);
        driver.findElement(CONTINUE_BUTTON).click();
        return this;
    }

    @Step("Проверка кнопки Отмена")
    public ProductsPages putCancel () {
        log.info("Click on Cancel button");
        driver.findElement(CANCEL_BUTTON).click();
        return new ProductsPages(driver);
    }

    @Step("Проверка кнопки Финиш")
    public CheckoutPage putFinish () {
        log.info("Click on Finish button");
        driver.findElement(FINISH_BUTTON).click();
        return this;
    }

    @Step("Проверка кнопки Вернуться Домой")
    public ProductsPages putBackHome () {
        log.info("Click on BackHome button");
        driver.findElement(BACKHOMEBUTTON).click();
        return new ProductsPages(driver);
    }

}
