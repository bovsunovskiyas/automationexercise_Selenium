package com.automationexercise.models;

import com.automationexercise.components.ProductAddedToCartModal;
import com.automationexercise.helpers.Waiter;
import com.automationexercise.pages.BasePage;
import com.automationexercise.pages.ProductDetailsPage;
import lombok.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductCard {

    private WebElement image;
    private String price;
    private String name;
    private WebElement addToCartButton;
    private WebElement viewProductButton;
    private WebElement overlayAddToCartButton;
    private final Waiter waiter = new Waiter(BasePage.getDriver());

    public ProductDetailsPage clickViewProductButton() {
        this.viewProductButton.click();
        return new ProductDetailsPage();
    }

    public ProductAddedToCartModal clickAddToCartButton() {
        Actions actions = new Actions(BasePage.getDriver());
        actions.moveToElement(this.image).pause(500).build().perform();
        waiter.waitUntilClickable(this.overlayAddToCartButton).click();
        return new ProductAddedToCartModal();
    }

    @Override
    public String toString() {
        return "Product{" +
                "price='" + price + '\'' +
                ", name='" + name + '\'' +
                '}'+"\n";
    }
}
