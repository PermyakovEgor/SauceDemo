package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductPageTest extends BaseTest {

    @Test(testName = "Добавление товара в корзину", description = "Проверка добавление товара в корзину со страницы Products",
            groups = {"smoke"})
    @Description("Проверка добавление товара в корзину со страницы Products")
    @Epic("E2E")
    @Feature("Check add product on cart")
    @Story("Product page")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("SouceDemo_CheckProductPage")
    @Issue("SouceDemo_CheckProductPage")
    @Owner("Permyakov Egor")
    public void checkItemOnCart() {
        loginPage.isPageOpened()
                .login("standard_user", "secret_sauce")
                .addToCart("Sauce Labs Backpack")
                .openCard();
        assertEquals(cardPage.getCartItem(),1, "В корзину добавлен товар");
    }

    @Test(testName = "Удаление товара из корзины", description = "Проверка удаление товара из корзины со страницы Products",
            dependsOnMethods = "checkItemOnCart")
    @Description("Проверка удаление товара из корзины со страницы Products с указанием {'products'} названия продукта который хотим добавить и удалить")
    @Epic("E2E")
    @Feature("Check remove product on cart")
    @Story("Product page")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("SouceDemo_CheckProductPage")
    @Issue("SouceDemo_CheckProductPage")
    @Owner("Permyakov Egor")
    public void checkItemNotOnCart() {
        loginPage.isPageOpened()
                .login("standard_user", "secret_sauce")
                .addToCart("Sauce Labs Backpack")
                .openCard()
                .removeFromCart("Sauce Labs Backpack");
        assertEquals(cardPage.getCartItem(),0, "Товар не удален из корзины, со страницы с товарами");
    }

    @Test(testName = "Вход в корзину", description = "Проверка перехода в корзину при нажатии на тележку",
            groups = {"smoke"})
    @Description("Проверка перехода в корзину при нажатии на тележку")
    @Epic("E2E")
    @Feature("Check add product on cart")
    @Story("Product page")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("SouceDemo_CheckProductPage")
    @Issue("SouceDemo_CheckProductPage")
    @Owner("Permyakov Egor")
    public void checkOpenCard() {
        loginPage.isPageOpened()
                .login("standard_user", "secret_sauce")
                .openCard();
        assertEquals(cardPage.getTitleCard(), "Your Cart", "Не переходит в корзину");
    }

    @Test(testName = "Переход к карточке товара по названию", description = "Проверка перехода к карточке товара при нажатии на название товара",
            groups = {"smoke"})
    @Description("Проверка перехода к карточке товара при нажатии на название товара")
    @Epic("E2E")
    @Feature("Check open product card with name")
    @Story("Product page")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("SouceDemo_CheckProductPage")
    @Issue("SouceDemo_CheckProductPage")
    @Owner("Permyakov Egor")
    public void checkOpenItemOnName() {
        loginPage.isPageOpened()
                .login("standard_user", "secret_sauce");
        assertEquals(productsPages.openItemOnName(), "Back to products", "не переходит в карточку товара по названию");
    }

    @Test(testName = "Переход к карточке товара по иконке", description = "Проверка перехода к карточке товара при нажатии на иконку товара")
    @Description("Проверка перехода к карточке товара при нажатии на иконку товара")
    @Epic("E2E")
    @Feature("Check open product card with icon")
    @Story("Product page")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("SouceDemo_CheckProductPage")
    @Issue("SouceDemo_CheckProductPage")
    @Owner("Permyakov Egor")
    public void checkOpenItemOnIcon() {
        loginPage.isPageOpened()
                .login("standard_user", "secret_sauce");
        assertEquals(productsPages.openItemOnIcon(), "Back to products", "не переходит в карточку товара по иконке");
    }

    @Test(testName = "Разлогин", description = "Проверка разлогина с сайта со страницы Products")
    @Description("Проверка перехода к карточке товара при нажатии на иконку товара")
    @Epic("E2E")
    @Feature("Check open product card with icon")
    @Story("Product page")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("SouceDemo_CheckProductPage")
    @Issue("SouceDemo_CheckProductPage")
    @Owner("Permyakov Egor")
    public void checkLogOut() {
        loginPage.isPageOpened()
                .login("standard_user", "secret_sauce");
        assertEquals(productsPages.logOut(), "login-button", "Не происходит разлогин");
    }

    @Test(testName = "Сортировка по алфавиту", description = "Проверка корректной сортировки товаров на странице по алфавиту")
    @Description("Проверка корректной сортировки товаров на странице по алфавиту")
    @Epic("E2E")
    @Feature("Sort product A-Z")
    @Story("Product page")
    @Severity(SeverityLevel.MINOR)
    @Link("https://www.saucedemo.com/")
    @TmsLink("SouceDemo_CheckProductPage")
    @Issue("SouceDemo_CheckProductPage")
    @Owner("Permyakov Egor")
    public void checkSortAZ() {
        loginPage.isPageOpened()
                .login("standard_user", "secret_sauce");
        assertTrue(productsPages.sortAZ(), "Отсортировано не в алфавитном порядке");
    }

    @Test(testName = "Сортировка по алфавиту убывание", description = "Проверка корректной сортировки товаров на странице по алфавиту в обратном порядке")
    @Description("Проверка корректной сортировки товаров на странице по алфавиту в обратном порядке")
    @Epic("E2E")
    @Feature("Sort product Z-A")
    @Story("Product page")
    @Severity(SeverityLevel.MINOR)
    @Link("https://www.saucedemo.com/")
    @TmsLink("SouceDemo_CheckProductPage")
    @Issue("SouceDemo_CheckProductPage")
    @Owner("Permyakov Egor")
    public void checkSortZA() {
        loginPage.isPageOpened()
                .login("standard_user", "secret_sauce");
        assertTrue(productsPages.sortZA(), "Отсортировано не в порядке Z-A");
    }

    @Test(testName = "Сортировка по возрастанию цены", description = "Проверка корректной сортировки товаров на странице по взрастанию цены")
    @Description("Проверка корректной сортировки товаров на странице по взрастанию цены")
    @Epic("E2E")
    @Feature("Sort product LowHigh")
    @Story("Product page")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("SouceDemo_CheckProductPage")
    @Issue("SouceDemo_CheckProductPage")
    @Owner("Permyakov Egor")
    public void checkSortLowHigh() {
        loginPage.isPageOpened()
                .login("standard_user", "secret_sauce");
        assertTrue(productsPages.sortLowHigh(), "Отсортировано не в порядке увеличения цены");
    }

    //10. Проверка сортировки по цене, убывание
    @Test(testName = "Сортировка по убыванию цены", description = "Проверка корректной сортировки товаров на странице по убыванию цены")
    @Description("Проверка корректной сортировки товаров на странице по убыванию цены")
    @Epic("E2E")
    @Feature("Sort product HighLow")
    @Story("Product page")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("SouceDemo_CheckProductPage")
    @Issue("SouceDemo_CheckProductPage")
    @Owner("Permyakov Egor")
    public void checkSortHighLow() {
        loginPage.isPageOpened()
                .login("standard_user", "secret_sauce");
        assertTrue(productsPages.sortHighLow(), "Отсортировано не в порядке убывания цены");
    }
}
