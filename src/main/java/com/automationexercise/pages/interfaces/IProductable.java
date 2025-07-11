package com.automationexercise.pages.interfaces;

import com.automationexercise.models.Product;
import com.automationexercise.pages.BasePage;
import com.automationexercise.pages.ProductsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public interface IProductable {

    By containerLocator = By.xpath("//div[@class='product-image-wrapper']");
    By productImageLocator = By.xpath(".//img");
    By productPriceLocator = By.xpath(".//h2[1]");
    By productNameLocator = By.xpath(".//p[1]");
    By addToCartButtonLocator = By.xpath(".//a[contains(@class, 'add-to-cart')][1]");
    By viewProductButtonLocator = By.xpath(".//i[contains(@class, 'fa-plus-square')][1]");

    default List<Product> getAllProducts(){
        List<Product> products = new ArrayList<>();
        List<WebElement> productContainer = BasePage.getDriver().findElements(containerLocator);
        for (WebElement container : productContainer){
            WebElement productImage = container.findElement(productImageLocator);
            String productPrice = container.findElement(productPriceLocator).getText();
            String productName = container.findElement(productNameLocator).getText();
            WebElement addToCartButton = container.findElement(addToCartButtonLocator);
            WebElement viewProductButton = container.findElement(viewProductButtonLocator);

            Product product = Product.builder()
                    .image(productImage)
                    .price(productPrice)
                    .name(productName)
                    .addToCartButton(addToCartButton)
                    .viewProductButton(viewProductButton)
                    .build();

            products.add(product);
        }


        return products;
    }

}
