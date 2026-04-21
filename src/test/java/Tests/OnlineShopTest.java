package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class OnlineShopTest extends BaseTest{

    @Test
    public void onlineShop() {

        SoftAssert softAssert = new SoftAssert();

        //Открываем страницу браузера
        driver.get("https://www.saucedemo.com/");

        //Ждем 2 секунды, чтобы прогрузилось
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        //Ищем поле логина по id и заполняем
        WebElement username = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        username.sendKeys("standard_user");

        //Ищем поле пароля по названия и заполняем
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        password.sendKeys("secret_sauce");

        //Ищем кнопку подтверждения по типу и авторизуемся
        WebElement acceptButton = driver.findElement(By.xpath("//input[contains(@type,'submit')]"));
        acceptButton.click();

        //Находим третий товар в списке с помощью cssSelector
        WebElement product = driver.findElement(By.cssSelector("#inventory_container .inventory_list .inventory_item:nth-child(3) button.btn.btn_primary.btn_small.btn_inventory"));
        product.click();

        WebElement cart = driver.findElement(By.cssSelector("[data-test=shopping-cart-link]"));
        cart.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        //Проверяем соответствие названия
        WebElement nameProductInCart = driver.findElement(By.cssSelector("div.inventory_item_name"));
        String name = nameProductInCart.getText();
        softAssert.assertEquals(name, "Sauce Labs Bolt T-Shirt", "Не тот товар в корзине");

        WebElement priceProductInCart = driver.findElement(By.xpath("//div[@class='inventory_item_price']"));
        priceProductInCart.getText();
        softAssert.assertEquals(priceProductInCart, "$15.99", "Стоимость товара в корзине не соответствует ожидаемой");

        driver.quit();
    }
}
