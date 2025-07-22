package com.automationexercise.models;

import lombok.*;
import org.openqa.selenium.WebElement;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    private WebElement image;
    private String price;
    private String name;
    private WebElement addToCartButton;
    private WebElement viewProductButton;

    @Override
    public String toString() {
        return "Product{" +
                "price='" + price + '\'' +
                ", name='" + name + '\'' +
                '}'+"\n";
    }
}
