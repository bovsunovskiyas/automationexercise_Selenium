package com.automationexercise.models;

import com.automationexercise.components.ProductAddedToCartModal;
import com.automationexercise.helpers.Waiter;
import com.automationexercise.pages.BasePage;
import com.automationexercise.pages.ProductDetailsPage;
import lombok.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProductInCart {

    private WebElement image;
    private WebElement name;
    private String nameAsText;
    private String category;
    private String price;
    private String quantity;
    private String total;
    private WebElement deleteButton;
    private final Waiter waiter = new Waiter(BasePage.getDriver());

}
