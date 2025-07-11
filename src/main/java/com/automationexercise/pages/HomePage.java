package com.automationexercise.pages;

import com.automationexercise.SecretsManager;
import com.automationexercise.components.MainMenu;
import lombok.Getter;
import org.openqa.selenium.By;
import com.automationexercise.helpers.Waiter;

public class HomePage extends BasePage{

        @Getter
        private final MainMenu mainMenu = new MainMenu();
        private final Waiter waiter = new Waiter(getDriver());


        // Locators
        private final By loggedInAsLocator = By.xpath("//a[contains(text(),'Logged in as')]");
        private final By featuresItemsTitleLocator = By.xpath("//*[@class='features_items']//h2[@class='title text-center']");
        private final By homePageSliderLocator = By.id("slider");


        public HomePage openMainPage(){
                String baseUrl = SecretsManager.get("BASE_URL");
                getDriver().get(baseUrl);
                waiter.waitUntilUrlToBe(baseUrl);
                return this;
        }

        public HomePage assertMainPageSuccessfullyLoaded(){
                waiter.waitUntilVisibleOfElementLocated(logoMainLocator);
                waiter.waitUntilVisibleOfElementLocated(featuresItemsTitleLocator);
                return this;
        }

        public boolean isMainLogoVisible() {
                return waiter.waitUntilVisibleOfElementLocated(logoMainLocator).isDisplayed();
        }

        public boolean isHomePageVisible() {
                return waiter.waitUntilVisibleOfElementLocated(homePageSliderLocator).isDisplayed();
        }

}


