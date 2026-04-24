package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CardPage extends BasePage{

    private final By TITLECARD = By.cssSelector("[data-test=title]");
    private final By ITEMINCARD = By.xpath("//div[@class='cart_item' and @data-test='inventory-item']");
    private final By REMOVEITEMINCARD = By.xpath("//button[contains(text(), 'Remove')]");
    private final By CHECKOUT = By.xpath("//button[contains(text(), 'Checkout')]");
    private final By CONTINUESHOPPING = By.id("continue-shopping");

    public CardPage(WebDriver driver) {
        super(driver);
    }

    public String getTitleCard() {
        return driver.findElement(TITLECARD).getText();
    }

    public int getCartItem() {
        return driver.findElements(ITEMINCARD).size();
    }

    public void checkItemsAfterRemove(int number) {
        List<WebElement> elements = driver.findElements(REMOVEITEMINCARD);
        WebElement firstElement = elements.get(number);
        firstElement.click();
    }

    public void openCheckout () {
        driver.findElement(CHECKOUT).click();
    }

    public void backToShopping () {
        driver.findElement(CONTINUESHOPPING).click();
    }
}
