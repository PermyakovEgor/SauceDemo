package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest{

    @Test(testName = "Your Information - успешный ввод", description = "Успешный ввод при оформлении товара в Checkout: Your Information и переход к Overview",
            groups = {"smoke"})
    @Description("Успешный ввод при оформлении товара в Checkout: Your Information и переход к Overview")
    @Epic("E2E")
    @Feature("Back to product page on card")
    @Story("Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("SouceDemo_Checkout")
    @Issue("SouceDemo_Checkout")
    public void checkInputCheckout() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.openCard();
        cardPage.openCheckout();
        checkoutPage.inputCheckout("Егор", "Пермяков","234");
        assertEquals(checkoutPage.getTitleCheckout(), "Checkout: Overview", "После заполнения полей, не переходит в Overview");
    }

    @Test(testName = "Your Information - кнопка Cancel", description = "Возвращение в Products из Checkout: Your Information при нажатии Cancel")
    @Description("Возвращение в Products из Checkout: Your Information при нажатии Cancel")
    @Epic("E2E")
    @Feature("Cancel button")
    @Story("Checkout")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("SouceDemo_Checkout")
    @Issue("SouceDemo_Checkout")
    public void checkCancelButton1() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.openCard();
        cardPage.openCheckout();
        checkoutPage.putCancel();
        assertEquals(cardPage.getTitleCard(), "Your Cart", "Не переходит в корзину");
    }

    @Test(testName = "Overview - кнопка Cancel", description = "Возвращение в корзину из Checkout: Overview при нажатии Cancel",
    dependsOnMethods = "checkInputCheckout")
    @Description("Возвращение в корзину из Checkout: Overview при нажатии Cancel")
    @Epic("E2E")
    @Feature("Cancel button")
    @Story("Checkout")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("SouceDemo_Checkout")
    @Issue("SouceDemo_Checkout")
    public void checkCancelButton2() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.openCard();
        cardPage.openCheckout();
        checkoutPage.inputCheckout("Егор", "Пермяков","234");
        checkoutPage.putCancel();
        assertEquals(cardPage.getTitleCard(), "Products", "Не переходит к странице продуктов");
    }

    @Test(testName = "Overview - кнопка Finish", description = "Завершение оформления заказа нажатием на кнопку Finish",
            dependsOnMethods = "checkInputCheckout", groups = {"smoke"})
    @Description("Завершение оформления заказа нажатием на кнопку Finish")
    @Epic("E2E")
    @Feature("Finish button")
    @Story("Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("SouceDemo_Checkout")
    @Issue("SouceDemo_Checkout")
    public void checkFinishButton() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.openCard();
        cardPage.openCheckout();
        checkoutPage.inputCheckout("Егор", "Пермяков","234");
        checkoutPage.putFinish();
        assertEquals(cardPage.getTitleCard(), "Checkout: Complete!1", "не подтверждается оформление товара");
    }

    @Test(testName = "Checkout: Complete - кнопка BackHome", description = "Возвращение к странице Products из Checkout: Complete при нажатии BackHome",
            dependsOnMethods = "checkFinishButton")
    @Description("Возвращение к странице Products из Checkout: Complete при нажатии BackHome")
    @Epic("E2E")
    @Feature("BackHome button")
    @Story("Checkout")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("SouceDemo_Checkout")
    @Issue("SouceDemo_Checkout")
    public void checkBachHomeButton() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.openCard();
        cardPage.openCheckout();
        checkoutPage.inputCheckout("Егор", "Пермяков","234");
        checkoutPage.putFinish();
        checkoutPage.putBackHome();
        assertEquals(cardPage.getTitleCard(), "Products", "Не переходит к странице продуктов");
    }


    //Данные для оформления заказа
    @DataProvider(name = "Тестовые данные для негативного оформления заказа")
    public Object [][]  loginData() {
        return new Object[][] {
                {"", "Пермяков", "234", "Error: First Name is required"},
                {"Егор", "", "234", "Error: Last Name is required"},
                {"Егор", "пермяков", "", "Error: Postal Code is required"}
        };
    }

    @Test(testName = "Checkout: Your Information - пустое имя", description = "Получение ошибки в Checkout: Your Information при оставлении поля 'Имя' пустым",
    dataProvider = "Тестовые данные для негативного оформления заказа")
    @Description("Получение ошибки в Checkout: Your Information при оставлении поля 'Имя' пустым")
    @Epic("E2E")
    @Feature("Negative Checkout Input")
    @Story("Checkout")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("SouceDemo_Checkout")
    @Flaky
    @Issue("SouceDemo_Checkout")
    public void checkInputWithoutName(String firstName, String lastName, String zip, String errorMessage) {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPages.openCard();
        cardPage.openCheckout();
        checkoutPage.inputCheckout(firstName, lastName,zip);
        assertEquals(loginPage.getErrorMessage(), errorMessage, "Не появляется ошибка при некорректном оформлении заказа");
    }
}
