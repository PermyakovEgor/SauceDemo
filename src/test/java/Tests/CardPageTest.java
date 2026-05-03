package Tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CardPageTest extends BaseTest{

    @Test(testName = "Добавление товара в корзину", description = "Добавление товара в корзину со страницы Products")
    public void checkOneItemInCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.addToCard();
        productsPages.openCard();
        assertEquals(cardPage.getCartItem(), 1, "В корзине должен быть 1 товар");
    }

    @Test(testName = "Добавление нескольких товаров в корзину", description = "Добавление трех товаров в корзину со страницы Products",
            dependsOnMethods = "checkOneItemInCart")
    public void checkThreeItemInCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.addFewСard();
        productsPages.openCard();
        assertEquals(cardPage.getCartItem(), 3, "В корзине должно быть 3 товара");
    }

    @Test(testName = "Удаление товаров из корзины", description = "Проверка удаления товаров из корзины после добавления со страницы Products",
            groups = {"smoke"})
    public void checkItemsAfterRemove() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.addFewСard();
        productsPages.openCard();
        cardPage.checkItemsAfterRemove(1);
        assertEquals(cardPage.getCartItem(), 2, "В корзине после удаления должно быть 2 товара");
    }

    @Test(testName = "Оформление заказа", description = "Проверка оформления заказа Checkout из корзины",
            groups = {"smoke"})
    public void checkOpenCheckout() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.openCard();
        cardPage.openCheckout();
        assertEquals(checkoutPage.getTitleCheckout(), "Checkout: Your Information", "Не открывается оформление товара");
    }

    @Test(testName = "Проверка кнопки Continue Shopping", description = "Проверка кнопки Continue Shopping для возвращения из корзины к страницы Products")
    public void checkContinueShopping() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.openCard();
        cardPage.backToShopping();
        assertEquals(productsPages.getTitle(), "Products", "Не возвращается к странице товаров из корзины");
    }
}
