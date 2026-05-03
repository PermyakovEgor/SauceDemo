package Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest{

    @Test(testName = "Успешная авторизация", description = "Авторизация в онлайн-магазине SouceDemo с валидными данными", priority = 1,
            groups = {"smoke"})
    public void CheckLoginWithValid() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPages.getTitle(), "Products", "Не происходит авторизация");
    }

    @DataProvider(name = "Тестовые данные для негативного логина")
    public Object [][]  loginData() {
        return new Object[][] {
                {"standard_user", "", "Epic sadface: Password is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(testName = "Авторизация - неправильные данные", description = "Авторизация в онлайн-магазине SouceDemo с неверными данными", priority = 2,
    dataProvider = "Тестовые данные для негативного логина")
    public void CheckLoginWithNegativeCred(String user, String password, String errorMessage) {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(loginPage.getErrorMessage(), errorMessage, "Не получаем ошибку авторизации");
    }
}