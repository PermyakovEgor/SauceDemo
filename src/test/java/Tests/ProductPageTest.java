package Tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductPageTest extends BaseTest {

    @Test(testName = "Добавление товара в корзину", description = "Проверка добавление товара в корзину со страницы Products",
            groups = {"smoke"})
    public void checkItemOnCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        boolean isAdded = productsPages.addToCard();
        assertTrue(isAdded, "Товар не добавлен в корзину, со страницы с товарами");
    }

    @Test(testName = "Удаление товара из корзины", description = "Проверка удаление товара из корзины со страницы Products",
            dependsOnMethods = "checkItemOnCart")
    public void checkItemNotOnCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.addToCard();
        boolean isRemove = productsPages.removeFromCard();
        assertTrue(isRemove, "Товар не удален из корзины, со страницы с товарами");
    }

    @Test(testName = "Вход в корзину", description = "Проверка перехода в корзину при нажатии на тележку",
            groups = {"smoke"})
    public void checkOpenCard() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.openCard();
        assertEquals(cardPage.getTitleCard(), "Your Cart", "Не переходит в корзину");
    }

    @Test(testName = "Переход к карточке товара по названию", description = "Проверка перехода к карточке товара при нажатии на название товара",
            groups = {"smoke"})
    public void checkOpenItemOnName() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPages.openItemOnName(), "Back to products", "не переходит в карточку товара по названию");
    }

    @Test(testName = "Переход к карточке товара по иконке", description = "Проверка перехода к карточке товара при нажатии на иконку товара")
    public void checkOpenItemOnIcon() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPages.openItemOnIcon(), "Back to products", "не переходит в карточку товара по иконке");
    }

    @Test(testName = "Разлогин", description = "Проверка разлогина с сайта со страницы Products")
    public void checkLogOut() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPages.logOut(), "login-button", "Не происходит разлогин");
    }

    @Test(testName = "Сортировка по алфавиту", description = "Проверка корректной сортировки товаров на странице по алфавиту")
    public void checkSortAZ() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPages.sortAZ(), "Отсортировано не в алфавитном порядке");
    }

    @Test(testName = "Сортировка по алфавиту убывание", description = "Проверка корректной сортировки товаров на странице по алфавиту в обратном порядке")
    public void checkSortZA() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPages.sortZA(), "Отсортировано не в порядке Z-A");
    }

    @Test(testName = "Сортировка по возрастанию цены", description = "Проверка корректной сортировки товаров на странице по взрастанию цены")
    public void checkSortLowHigh() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPages.sortLowHigh(), "Отсортировано не в порядке увеличения цены");
    }

    //10. Проверка сортировки по цене, убывание
    @Test(testName = "Сортировка по убыванию цены", description = "Проверка корректной сортировки товаров на странице по убыванию цены")
    public void checkSortHighLow() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPages.sortHighLow(), "Отсортировано не в порядке убывания цены");
    }
}
