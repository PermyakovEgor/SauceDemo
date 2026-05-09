package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    @Step("Удаление товара из корзины по названию '{product}'")
    public String removeFromCart(String product) {
        driver.findElement(By.xpath(String.format(REMOVE_FROM_CART_PATTERN, product))).click();
        return product;
    }

    @Step("Открытие страницы корзины")
    public String getTitleCard() {
        return driver.findElement(TITLECARD).getText();
    }

    @Step("Получение кол-ва товаров в корзине")
    public int getCartItem() {
        return driver.findElements(ITEMINCARD).size();
    }

    @Step("Открытие SidePage оформления заказа")
    public void openCheckout () {
        driver.findElement(CHECKOUT).click();
    }

    @Step("Проверка кнопки Continue Shopping в корзине")
    public void backToShopping () {
        driver.findElement(CONTINUESHOPPING).click();
    }
}
