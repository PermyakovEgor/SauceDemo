package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.AllureUtils;

import static org.testng.Assert.assertEquals;
import static utils.AllureUtils.takeScreenshot;

public class LoginTest extends BaseTest{

    @Test(testName = "Успешная авторизация", description = "Авторизация в онлайн-магазине SouceDemo с валидными данными", priority = 1,
            groups = {"smoke"})
    @Description("Авторизация в онлайн-магазине SouceDemo с валидными данными")
    @Epic("E2E")
    @Feature("Login in to SauceDemo")
    @Story("Positive Login")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("SouceDemo_CheckLogin")
    @Issue("SouceDemo_CheckLogin")
    @Flaky
    @Owner("Permyakov Egor")
    public void CheckLoginWithValid() {
        loginPage.isPageOpened()
                .login(user, password);
        assertEquals(productsPages.getTitle(), "Products", "Не происходит авторизация");
    }

    @DataProvider(name = "Тестовые данные для негативного логина")
    public Object [][]  loginData() {
        return new Object[][] {
                {user, "", "Epic sadface: Password is required"},
                {"", password, "Epic sadface: Username is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(testName = "Авторизация - неправильные данные", description = "Авторизация в онлайн-магазине SouceDemo с неверными данными", priority = 2,
    dataProvider = "Тестовые данные для негативного логина")
    @Description("Попытка вторизации в онлайн-магазине SouceDemo с невалидными данными")
    @Epic("E2E")
    @Feature("Login in to SauceDemo with broke data")
    @Story("Negative Login")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("SouceDemo_CheckLogin")
    @Issue("SouceDemo_CheckLogin")
    @Owner("Permyakov Egor")
    public void CheckLoginWithNegativeCred(String user, String password, String errorMessage) {
        loginPage.isPageOpened()
                .login(user, password);
        assertEquals(loginPage.getErrorMessage(), errorMessage, "Не получаем ошибку авторизации");
    }
}