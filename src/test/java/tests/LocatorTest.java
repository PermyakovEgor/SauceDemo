package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class LocatorTest extends BaseTest{

    @Test
    public void locatorTest() {
        WebDriver driver = new ChromeDriver();

        //Открываем страницу браузера
        driver.get("https://www.saucedemo.com/");

        //Ждем 2 секунды, чтобы прогрузилось
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        //Ищем поле логина по id и заполняем
        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("standard_user");

        //Ищем поле пароля по name и заполняем
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("secret_sauce");

        //Ищем кнопку по имени класса и кликаем
        WebElement login = driver.findElement(By.className("submit-button"));
        login.click();

        //после авторизации пользуемся всеми оставшимися локаторами
        WebElement tagName = driver.findElement(By.tagName("button"));
        String tagNameText = tagName.getTagName();
        System.out.println("По tagname возвращает: " + tagNameText);

        WebElement linkText = driver.findElement(By.linkText("Sauce Labs Bike Light"));
        String linkTextText = linkText.getText();
        System.out.println("По linktext возвращает: " + linkTextText);

        WebElement partialLinkText = driver.findElement(By.partialLinkText("T-Shirt"));
        String partialLinkTextText = partialLinkText.getText();
        System.out.println("По partiallinktext возвращает: " + partialLinkTextText);

        WebElement xpathAtribute = driver.findElement(By.xpath("//a[@data-test='item-4-title-link']"));
        String xpathAtributeText = xpathAtribute.getText();
        System.out.println("По xpathAtribute возвращает: " + xpathAtributeText);

        WebElement xpathText = driver.findElement(By.xpath("//div[text()='29.99']"));
        String xpathTextText = xpathText.getText();
        System.out.println("По xpathTextText возвращает: " + xpathTextText);

        WebElement xpathAtribute1 = driver.findElement(By.xpath("//a[contains(@data-test,'1-title')]"));
        String xpathAtributeText1 = xpathAtribute1.getText();
        System.out.println("По частичному xpathAtribute возвращает: " + xpathAtributeText1);

        WebElement xpathText1 = driver.findElement(By.xpath("//div[contains(text(), 'Bolt')]"));
        String xpathTextText1 = xpathText1.getText();
        System.out.println("По частичному xpathTex возвращает: " + xpathTextText1);

        WebElement ancestor = driver.findElement(By.xpath("//*[text()='Add to cart']//ancestor::div"));
        String ancestorText = ancestor.getAttribute("id");
        System.out.println("По частичному xpathAncestor возвращает: " + ancestorText);

        WebElement descendant = driver.findElement(By.xpath("//div[@class='primary_header']//descendant::div"));
        String descendantText = descendant.getText();
        System.out.println("По частичному xpathDescendant возвращает: " + descendantText);

        //Устал выводить и смотреть, не падает и ладно
        driver.findElement(By.xpath("//*[@data-test='inventory-container']//*/following::div[@class='pricebar']"));  //following

        driver.findElement(By.xpath("//*[@data-test='inventory-container']/parent::*"));   //parent

        driver.findElement(By.xpath("//*[@data-test='inventory-container']/preceding::*"));   //preceding

        driver.findElement(By.xpath("//*[@class='inventory_container' and @id='inventory_container']"));   //поиск элемента с условием AND, например input[@class='_2zrpKA_1dBPDZ' and @type='text']

        driver.findElement(By.cssSelector(".shopping_cart_container"));   //.class

        driver.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory"));   //.class1.class2

        driver.findElement(By.cssSelector(".header_container .primary_header"));   //.class1 .class2

        driver.findElement(By.cssSelector("#shopping_cart_container"));   //#id

        driver.findElement(By.cssSelector("footer"));   //tagname

        driver.findElement(By.cssSelector("div.page_wrapper"));   //tagname.class

        driver.findElement(By.cssSelector("[lang=en]"));   //[attribute=value]

        driver.findElement(By.cssSelector("[alt~=Labs]"));   //[attribute~=value]

        driver.findElement(By.cssSelector("[data-test|=active]"));   //[attribute|=value]

        driver.findElement(By.cssSelector("[data-test^=active]"));   //[attribute^=value]

        driver.findElement(By.cssSelector("[data-test$=option]"));   //[attribute$=value]

        driver.findElement(By.cssSelector("[data-test*=opt]"));   //[attribute*=value]
    }
}
