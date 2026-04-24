package Tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest{

    //1. Успешный ввод в Checkout: Your Information
    @Test
    public void checkInputCheckout() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.openCard();
        cardPage.openCheckout();
        checkoutPage.inputCheckout("Егор", "Пермяков","234");
        assertEquals(checkoutPage.getTitleCheckout(), "Checkout: Overview", "После заполнения полей, не переходит в Overview");
    }

    //2. Проверка кнопки Cancel в Checkout: Your Information
    @Test
    public void checkCancelButton1() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.openCard();
        cardPage.openCheckout();
        checkoutPage.putCancel();
        assertEquals(cardPage.getTitleCard(), "Your Cart", "Не переходит в корзину");
    }

    //3. Проверка кнопки Cancel в Checkout: Overview
    @Test
    public void checkCancelButton2() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.openCard();
        cardPage.openCheckout();
        checkoutPage.inputCheckout("Егор", "Пермяков","234");
        checkoutPage.putCancel();
        assertEquals(cardPage.getTitleCard(), "Products", "Не переходит к странице продуктов");
    }

    //4. Проверка кнопки Finish в Checkout: Overview
    @Test
    public void checkFinishButton() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.openCard();
        cardPage.openCheckout();
        checkoutPage.inputCheckout("Егор", "Пермяков","234");
        checkoutPage.putFinish();
        assertEquals(cardPage.getTitleCard(), "Checkout: Complete!", "Не переходит в корзину");
    }

    //5. Проверка кнопки  BackHome в Checkout: Complete
    @Test
    public void checkBachHomeButton() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.openCard();
        cardPage.openCheckout();
        checkoutPage.inputCheckout("Егор", "Пермяков","234");
        checkoutPage.putFinish();
        checkoutPage.putBackHome();
        assertEquals(cardPage.getTitleCard(), "Products", "Не переходит в корзину");
    }

    //6. Ввод в Checkout: Your Information без имени
    @Test
    public void checkInputWithoutName() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.openCard();
        cardPage.openCheckout();
        checkoutPage.inputCheckout("", "Пермяков","234");
        assertEquals(loginPage.getErrorMessage(), "Error: First Name is required", "После пропуска имени в форме не появляется ошибка");
    }

    //7. Ввод в Checkout: Your Information без фамилии
    @Test
    public void checkInputWithoutLastName() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.openCard();
        cardPage.openCheckout();
        checkoutPage.inputCheckout("Егор", "","234");
        assertEquals(loginPage.getErrorMessage(), "Error: Last Name is required", "После пропуска фамилии в форме не появляется ошибка");
    }

    //8. Ввод в Checkout: Your Information без кода индекс
    @Test
    public void checkInputWithoutPostCode() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.openCard();
        cardPage.openCheckout();
        checkoutPage.inputCheckout("Егор", "Пермяков","");
        assertEquals(loginPage.getErrorMessage(), "Error: Postal Code is required", "После пропуска почтового индекса в форме не появляется ошибка");
    }
}
