package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class CardPage extends BasePage{

    private final By TITLECARD = By.cssSelector("[data-test=title]");
    private final By ITEMINCARD = By.xpath("//div[@class='cart_item' and @data-test='inventory-item']");
    private final String REMOVE_FROM_CART_PATTERN =
            "//*[text()='%s']/ancestor::div[@class='cart_list']//button[text()='Remove']";
    private final By CHECKOUT = By.xpath("//button[contains(text(), 'Checkout')]");
    private final By CONTINUESHOPPING = By.id("continue-shopping");

    public CardPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы корзины")
    @Override
    public CardPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLECARD));
        return this;
    }

    @Step("Удаление товара из корзины по названию '{product}'")
    public String removeFromCart(String product) {
        log.info("Product deleted from card");
        driver.findElement(By.xpath(String.format(REMOVE_FROM_CART_PATTERN, product))).click();
        return product;
    }

    @Step("Возврат текста TITLE со страницы корзины")
    public String getTitleCard() {
        log.info("Return title Card Page");
        isPageOpened();
        return driver.findElement(TITLECARD).getText();
    }

    @Step("Получение кол-ва товаров в корзине")
    public int getCartItem() {
        log.info("Return size Card");
        return driver.findElements(ITEMINCARD).size();
    }

    @Step("Открытие SidePage оформления заказа")
    public CheckoutPage openCheckout () {
        log.info("open Checkout Page");
        driver.findElement(CHECKOUT).click();
        return new CheckoutPage(driver);
    }

    @Step("Проверка кнопки Continue Shopping в корзине")
    public ProductsPages backToShopping () {
        log.info("Button BackToShopping in card open product Page");
        driver.findElement(CONTINUESHOPPING).click();
        return new ProductsPages(driver);
    }
}
