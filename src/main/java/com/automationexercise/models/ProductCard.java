package com.automationexercise.models;

import com.automationexercise.pages.ProductDetailsPage;
import lombok.*;
import org.openqa.selenium.WebElement;

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

    public ProductDetailsPage clickViewProductButton() {
        this.viewProductButton.click();
        return new ProductDetailsPage();
    }

    @Override
    public String toString() {
        return "Product{" +
                "price='" + price + '\'' +
                ", name='" + name + '\'' +
                '}'+"\n";
    }
}
