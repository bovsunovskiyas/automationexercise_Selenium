package com.automationexercise.components;

import com.automationexercise.helpers.Waiter;
import com.automationexercise.pages.BasePage;
import com.automationexercise.pages.CartPage;
import org.openqa.selenium.By;

public class ProductAddedToCartModal{
    private final Waiter waiter = new Waiter(BasePage.getDriver());
    private final By modal = By.id("cartModal");
    private final By viewCartButton = By.xpath("//div[@class='modal-body']//a[@href='/view_cart']");
    private final By continueShoppingButton = By.xpath("//button[@data-dismiss='modal']");


    public CartPage clickViewCartButton() {
        waiter.waitUntilClickable(viewCartButton).click();
        return new CartPage();
    }

    public ProductAddedToCartModal clickContinueShoppingButton() {
        waiter.waitUntilClickable(continueShoppingButton).click();
        return this;
    }

    public ProductAddedToCartModal assertModalIsVisible() {
        waiter.waitUntilVisibleOfElementLocated(modal);
        return this;
    }

    public ProductAddedToCartModal assertModalIsNotVisible(){
        waiter.waitUntilInVisibleOfElementLocated(modal);
        return this;
    }
}
