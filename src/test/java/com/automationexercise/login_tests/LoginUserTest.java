package com.automationexercise.login_tests;

import com.automationexercise.SecretsManager;
import com.automationexercise.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.automationexercise.BaseTest;

public class LoginUserTest extends BaseTest {

    private HomePage homePage;

    @BeforeMethod
    public void precondions(){
        homePage = new HomePage()
                .openMainPage()
                .assertMainPageSuccessfullyLoaded()
                .getMainMenu()
                .clickLoginButton()
                .assertLoginPageSuccessfullyLoaded()
                .enterLoginEmail(SecretsManager.get("USER_EMAIL"))
                .enterLoginPassword(SecretsManager.get("USER_PASSWORD"))
                .clickLoginButton();
    }

    //Test Case 2: Login User with correct email and password
    @Test
    public void testLoginUserWithCorrectCredentials() {
        Assert.assertTrue(homePage.getMainMenu().getLoggedInAsText().contains("dani"), "Logged in username does not match.");
    }

    //        Test Case 4: Logout User
        @Test
        public void testLogOutUser() {

            homePage
                    .getMainMenu()
                    .clickLogoutButton()
                    .assertLoginPageSuccessfullyLoaded();


            // 10. Verify that user is navigated to login page
            // A reliable way to verify this is by checking the URL and the presence of the 'Login to your account' header.
//            String currentUrl = driver.getCurrentUrl();
//            Assert.assertTrue(currentUrl.contains("/login"), "User is not on the login page after logout.");
//
//            WebElement loginToAccount = driver.findElement(By.xpath("//h2[text()='Login to your account']"));
//            Assert.assertTrue(loginToAccount.isDisplayed(), "'Login to your account' header is not visible after logout.");
        }




//




//    @Test
//    public void testLoginUserWithIncorrectCredentials() {
//
//        // Створюємо екземпляр HomePage, передаючи залежності з BaseTest
//        MainPage mainPage = new MainPage(driver, waiter);
//        Assert.assertTrue(mainPage.isLogoVisible(), "Home page is not visible.");
//
//        // Переходимо на сторінку логіну
//        LoginPage loginPage = mainPage.goToLoginPage();
//
//        // Генеруємо некоректні дані
//        String incorrectEmail = faker.internet().emailAddress();
//        String incorrectPassword = faker.internet().password();
//
//        // Виконуємо спробу входу
//        loginPage.login(incorrectEmail, incorrectPassword);
//
//        // Перевіряємо, чи з'явилося повідомлення про помилку
//        Assert.assertTrue(loginPage.isIncorrectLoginErrorVisible(), "Error message is not visible.");
//    }
//

//    }

}
