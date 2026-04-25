package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductsPages extends BasePage{

    private final By TITLE = By.cssSelector("[data-test=title]");
    private final By ADD_FEE_ITEM =By.xpath("//button[contains(text(), 'Add to cart')]");
    private final By ADD_TO_CARD = By.xpath("//*[@data-test='add-to-cart-sauce-labs-bike-light']");
    private final By REMOVE_FROM_CARD = By.xpath("//*[@data-test='remove-sauce-labs-bike-light']");
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

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public void addFewСard () {
        List<WebElement> elements = driver.findElements(ADD_FEE_ITEM);
        WebElement firstElement = elements.get(0);
        WebElement fourthElement = elements.get(3);
        WebElement sixElement = elements.get(5);
        firstElement.click();
        fourthElement.click();
        sixElement.click();
    }

    public boolean addToCard () {
        driver.findElement(ADD_TO_CARD).click();
        WebElement removeButton = driver.findElement(REMOVE_FROM_CARD);
        return removeButton.isDisplayed(); //Если нашли кнопку REMOVE_FROM_CARD возвращаем true, иначе false
    }

    public boolean removeFromCard () {
        driver.findElement(REMOVE_FROM_CARD).click();
        WebElement addButton = driver.findElement(ADD_TO_CARD);
        return addButton.isDisplayed(); //Если нашли кнопку ADD_TO_CARD возвращаем true, иначе false
    }

    public void openCard () {
        driver.findElement(GO_TO_CARD).click();
    }

    public String openItemOnName () {
        WebElement link = driver.findElement(GO_TO_ITEM_NAME);
        link.click();
        String button_back = driver.findElement(BACK_TO_PRODUCTS).getText();
        return button_back;
    }

    public String openItemOnIcon () {
        WebElement link = driver.findElement(GO_TO_ITEM_IMG);
        link.click();
        String button_back = driver.findElement(BACK_TO_PRODUCTS).getText();
        return button_back;
    }

    public String logOut () {
        driver.findElement(BURGER_MENU_BTN).click();
        WebElement logbutton = wait.until(ExpectedConditions.elementToBeClickable(BURGER_MENU_LOGOUT));
        logbutton.click();
        String logbut = driver.findElement(LOGIN_BUTTON).getAttribute("id");
        return logbut;
    }

    public boolean sortAZ () {
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

    public boolean sortZA () {
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

    public boolean sortLowHigh () {
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

    public boolean sortHighLow () {
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
