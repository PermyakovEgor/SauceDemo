package Tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CardPageTest extends BaseTest{

    //1. Добавление одного товара в корзину
    @Test
    public void checkOneItemInCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.addToCard();
        productsPages.openCard();
        assertEquals(cardPage.getCartItem(), 1, "В корзине должен быть 1 товар");
    }

    //2. Добавление трех товаров в корзину
    @Test
    public void checkThreeItemInCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.addFewСard();
        productsPages.openCard();
        assertEquals(cardPage.getCartItem(), 3, "В корзине должно быть 3 товара");
    }

    //3. Удаление одного из товаров
    @Test
    public void checkItemsAfterRemove() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.addFewСard();
        productsPages.openCard();
        cardPage.checkItemsAfterRemove(1);
        assertEquals(cardPage.getCartItem(), 2, "В корзине после удаления должно быть 2 товара");
    }

    //4. Проверить Checkout
    @Test
    public void checkOpenCheckout() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.openCard();
        cardPage.openCheckout();
        assertEquals(checkoutPage.getTitleCheckout(), "Checkout: Your Information", "Не открывается оформление товара");
    }

    //5. Проверить кнопку Continue Shopping - пока не трогал
    @Test
    public void checkContinueShopping() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.openCard();
        cardPage.backToShopping();
        assertEquals(productsPages.getTitle(), "Products", "Не возвращается к странице товаров из корзины");
    }
}
