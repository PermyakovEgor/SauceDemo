package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CardPageTest extends BaseTest{

    @Test(testName = "Добавление товара в корзину", description = "Добавление товара в корзину со страницы Products")
    @Description("Добавление товара в корзину со страницы Products")
    @Epic("E2E")
    @Feature("add product in card")
    @Story("Card page")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("SouceDemo_CheckCardPage")
    @Issue("SouceDemo_CheckCardPage")
    @Owner("Permyakov Egor")
    public void checkOneItemInCart() {
        loginPage.isPageOpened()
                .login(user, password)
                .addToCart("Sauce Labs Backpack")
                .openCard();
        assertEquals(cardPage.getCartItem(), 1, "В корзине должен быть 1 товар");
    }

    @Test(testName = "Добавление нескольких товаров в корзину", description = "Добавление трех товаров в корзину со страницы Products",
            dependsOnMethods = "checkOneItemInCart")
    @Description("Добавление трех товаров в корзину со страницы Products")
    @Epic("E2E")
    @Feature("add few product in card")
    @Story("Card page")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("SouceDemo_CheckCardPage")
    @Issue("SouceDemo_CheckCardPage")
    @Flaky
    @Owner("Permyakov Egor")
    public void checkThreeItemInCart() {
        loginPage.isPageOpened()
                .login(user, password)
                .addToCart("Sauce Labs Backpack")
                .addToCart("Sauce Labs Fleece Jacket")
                .addToCart("Test.allTheThings() T-Shirt (Red)")
                .openCard();
        assertEquals(cardPage.getCartItem(), 3, "В корзине должно быть 3 товара");
    }

    @Test(testName = "Удаление товаров из корзины", description = "Проверка удаления товаров из корзины после добавления со страницы Products",
            groups = {"smoke"})
    @Description("Проверка удаления товаров из корзины после добавления со страницы Products")
    @Epic("E2E")
    @Feature("remove product from card")
    @Story("Card page")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("SouceDemo_CheckCardPage")
    @Issue("SouceDemo_CheckCardPage")
    @Owner("Permyakov Egor")
    public void checkItemsAfterRemove() {
        loginPage.isPageOpened()
                .login(user, password)
                .addToCart("Sauce Labs Backpack")
                .addToCart("Sauce Labs Fleece Jacket")
                .addToCart("Test.allTheThings() T-Shirt (Red)")
                .openCard()
                .removeFromCart("Test.allTheThings() T-Shirt (Red)");
        assertEquals(cardPage.getCartItem(), 2, "В корзине после удаления должно быть 2 товара");
    }

    @Test(testName = "Оформление заказа", description = "Проверка оформления заказа Checkout из корзины",
            groups = {"smoke"})
    @Description("Проверка оформления заказа Checkout из корзины")
    @Epic("E2E")
    @Feature("Making an order")
    @Story("Card page")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("SouceDemo_CheckCardPage")
    @Issue("SouceDemo_CheckCardPage")
    public void checkOpenCheckout() {
        loginPage.isPageOpened()
                .login(user, password)
                .openCard()
                .openCheckout();
        assertEquals(checkoutPage.getTitleCheckout(), "Checkout: Your Information", "Не открывается оформление товара");
    }

    @Test(testName = "Проверка кнопки Continue Shopping", description = "Проверка кнопки Continue Shopping для возвращения из корзины к страницы Products")
    @Description("Проверка кнопки Continue Shopping для возвращения из корзины к страницы Products")
    @Epic("E2E")
    @Feature("Back to product page on card")
    @Story("Card page")
    @Severity(SeverityLevel.MINOR)
    @Link("https://www.saucedemo.com/")
    @TmsLink("SouceDemo_CheckCardPage")
    @Issue("SouceDemo_CheckCardPage")
    public void checkContinueShopping() {
        loginPage.isPageOpened()
                .login(user, password)
                .openCard()
                .backToShopping();
        assertEquals(productsPages.getTitle(), "Products", "Не возвращается к странице товаров из корзины");
    }
}
