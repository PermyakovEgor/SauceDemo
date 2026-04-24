package Tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductPageTest extends BaseTest {

    //1. Добавление товара в корзину
    @Test
    public void checkItemOnnCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        boolean isAdded = productsPages.add_to_card();
        assertTrue(isAdded, "Товар не добавлен в корзину, со страницы с товарами");
    }

    //2. Удаление товара из корзины
    @Test
    public void checkItemNotOnCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.add_to_card();
        boolean isRemove = productsPages.remove_from_card();
        assertTrue(isRemove, "Товар не удален из корзины, со страницы с товарами");
    }

    //3. Переход в корзину
    @Test
    public void checkOpenCard() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.openCard();
        assertEquals(cardPage.getTitleCard(), "Your Cart", "Не переходит в корзину");
    }

    //4. Переход к карточке товара по названию
    @Test
    public void checkOpenItemOnName() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPages.openItemOnName(), "Back to products", "не переходит в карточку товара по названию");
    }

    //5. Переход к карточке товара по иконке
    @Test
    public void checkOpenItemOnIcon() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPages.openItemOnIcon(), "Back to products", "не переходит в карточку товара по иконке");
    }

    //6. Переход к карточке товара по иконке
    @Test
    public void checkLogOut() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPages.LogOut(), "login-button", "Не происходит разлогин");
    }

    //7. Проверка сортировки по алфавиту, возрастание
    @Test
    public void checkSort_A_Z() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPages.Sort_A_Z(), "Отсортировано не в алфавитном порядке");
    }

    //8. Проверка сортировки по алфавиту, убывание
    @Test
    public void checkSort_Z_A() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPages.Sort_Z_A(), "Отсортировано не в порядке Z-A");
    }

    //9. Проверка сортировки по цене, возрастание
    @Test
    public void checkSort_Low_High() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPages.sort_Low_High(), "Отсортировано не в порядке увеличения цены");
    }

    //10. Проверка сортировки по цене, убывание
    @Test
    public void checkSort_High_Low() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPages.sort_High_Low(), "Отсортировано не в порядке убывания цены");
    }

}
