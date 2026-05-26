package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j2
public class ProductsPages extends BasePage{

    private final By TITLE = By.cssSelector("[data-test=title]");
    private final By ADD_FEE_ITEM =By.xpath("//button[contains(text(), 'Add to cart')]");
    private final String ADD_TO_CART_PATTERN =
            "//*[text()='%s']/ancestor::div[@class='inventory_item']//button[text()='Add to cart']";
    private final By GO_TO_CARD = By.cssSelector("#shopping_cart_container > a");
    private final By GO_TO_ITEM_IMG = By.id("item_5_img_link");
    private final By BACK_TO_PRODUCTS = By.id("back-to-products");
    private final By GO_TO_ITEM_NAME = By.id("item_5_title_link");
    private final By SAVE_ITEMS_FOR_SORT_BY_NAME = By.xpath("//div[@class='inventory_item_name ' and @data-test='inventory-item-name']");
    private final By SAVE_ITEMS_FOR_SORT_BY_PRICE = By.xpath("//div[@class='inventory_item_price' and @data-test='inventory-item-price']");
    private final By SELECT_SORT = By.cssSelector("[data-test='product-sort-container']");
    private final By BURGER_MENU_BTN = By.id("react-burger-menu-btn");
    private final By BURGER_MENU_LOGOUT = By.id("logout_sidebar_link");
    private final By LOGIN_BUTTON = By.xpath("//*[@data-test='login-button']");

    public ProductsPages(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы Login")
    @Override
    public ProductsPages isPageOpened() {
        log.info("Open PageProduct");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    @Step("Возврат текста TITLE страницы Продуктов")
    public String getTitle() {
        log.info("Return title Product Page");
        isPageOpened();
        return driver.findElement(TITLE).getText();
    }

    @Step("Добавление товара с названием '{product}' в корзину")
    public ProductsPages addToCart(String product) {
        log.info("Add product '{}' in card", product);
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
        return this;
    }

    @Step("Открытие страницы Корзина")
    public CardPage openCard () {
        log.info("Open Card Page");
        driver.findElement(GO_TO_CARD).click();
        return new CardPage(driver);
    }

    @Step("Открытие карточки товара по Названию")
    public String openItemOnName () {
        log.info("Open Product card by Name");
        WebElement link = driver.findElement(GO_TO_ITEM_NAME);
        link.click();
        String button_back = driver.findElement(BACK_TO_PRODUCTS).getText();
        return button_back;
    }

    @Step("Открытие карточки товара по иконке")
    public String openItemOnIcon () {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(GO_TO_ITEM_IMG));
            WebElement link = driver.findElement(GO_TO_ITEM_IMG);
            link.click();
            String button_back = driver.findElement(BACK_TO_PRODUCTS).getText();
            return button_back;
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            return "Error: timeout waiting for element";
        }
    }

    @Step("Разлогин")
    public String logOut () {
        log.info("Logout SouseDemo and get title LoginPage");
        driver.findElement(BURGER_MENU_BTN).click();
        WebElement logbutton = wait.until(ExpectedConditions.elementToBeClickable(BURGER_MENU_LOGOUT));
        logbutton.click();
        String logbut = driver.findElement(LOGIN_BUTTON).getAttribute("id");
        return logbut;
    }

    @Step("Сортировка по алфавиту")
    public boolean sortAZ () {
        log.info("Sorting products on the product page - AZ");
        List<WebElement> productElements = driver.findElements(SAVE_ITEMS_FOR_SORT_BY_NAME);
        List<String> productNames = new ArrayList<>();
        for (WebElement element : productElements) {
            productNames.add(element.getText());
        }
        List<String> sortedNames = new ArrayList<>(productNames);
        Collections.sort(sortedNames);
        if (productNames.equals(sortedNames)) {
            return true;
        }
        else {
            return false;
        }
    }

    @Step("Сортировка по алфавиту в обратном порядке")
    public boolean sortZA () {
        log.info("Sorting products on the product page - ZA");
        WebElement selectElement = driver.findElement(SELECT_SORT);
        Select dropdown = new Select(selectElement);
        dropdown.selectByValue("za");
        List<WebElement> productElements = driver.findElements(SAVE_ITEMS_FOR_SORT_BY_NAME);
        List<String> productNames = new ArrayList<>();
        for (WebElement element : productElements) {
            productNames.add(element.getText());
        }
        List<String> sortedNames = new ArrayList<>(productNames);
        Collections.sort(sortedNames);
        Collections.reverse(sortedNames);//переворачиваем список
        if (productNames.equals(sortedNames)) {
            return true;
        }
        else {
            return false;
        }
    }

    @Step("Сортировка по возрастанию цены")
    public boolean sortLowHigh () {
        log.info("Sorting products on the product page - low to high");
        WebElement selectElement = driver.findElement(SELECT_SORT);
        Select dropdown = new Select(selectElement);
        dropdown.selectByValue("lohi");
        List<WebElement> productElements = driver.findElements(SAVE_ITEMS_FOR_SORT_BY_PRICE);
        List<Double> prices = new ArrayList<>();
        for (WebElement element : productElements) {
            String priceText = element.getText().replace("$", "").trim();
            prices.add(Double.parseDouble(priceText));
        }
        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);
        if (prices.equals(sortedPrices)) {
            return true;
        }
        else {
            return false;
        }
    }

    @Step("Сортировка по убыванию цены")
    public boolean sortHighLow () {
        log.info("Sorting products on the product page - high to low");
        WebElement selectElement = driver.findElement(SELECT_SORT);
        Select dropdown = new Select(selectElement);
        dropdown.selectByValue("hilo");
        List<WebElement> productElements = driver.findElements(SAVE_ITEMS_FOR_SORT_BY_PRICE);
        List<Double> prices = new ArrayList<>();
        for (WebElement element : productElements) {
            String priceText = element.getText().replace("$", "").trim();
            prices.add(Double.parseDouble(priceText));
        }
        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);
        Collections.reverse(sortedPrices);//переворачиваем список
        if (prices.equals(sortedPrices)) {
            return true;
        }
        else {
            return false;
        }
    }
}
