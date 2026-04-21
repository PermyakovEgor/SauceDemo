package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPages extends BasePage{

    private final By TITLE = By.cssSelector("[data-test=title]");

    public ProductsPages(WebDriver driver) {
        super(driver);
    }

    public void open () {
        driver.get(BASE_URL + "/inventory.html");
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }
}
