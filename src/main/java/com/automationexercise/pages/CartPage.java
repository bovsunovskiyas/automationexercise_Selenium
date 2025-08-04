package com.automationexercise.pages;

import com.automationexercise.helpers.Waiter;
import com.automationexercise.models.ProductInCart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage{

    private final Waiter waiter = new Waiter(getDriver());
    //First cart item
    private final By containerLocator = By.xpath("//tr[contains(@id, 'product-')]");
    private final By imageLocator = By.xpath(".//td[@class='cart_product']//img");
    private final By nameLocator = By.xpath(".//td[@class='cart_description']//a");
    private final By categoryLocator = By.xpath(".//td[@class='cart_description']//p");
    private final By priceLocator = By.xpath(".//td[@class='cart_price']//p");
    private final By quantityLocator = By.xpath(".//td[@class='cart_quantity']//button");
    private final By totalLocator = By.xpath(".//td[@class='cart_total']//p");
    private final By deleteButtonLocator = By.xpath(".//a[@class='cart_quantity_delete']");


    public List<ProductInCart> getAllProductsInCart(){
    List<ProductInCart> productCards = new ArrayList<>();
    List<WebElement> productContainers = getDriver().findElements(containerLocator);

    for (WebElement container : productContainers){
        WebElement image = waiter.findOrNull(container, imageLocator);
        WebElement name = waiter.findOrNull(container, nameLocator);
        String nameAsText = name.getText();
        String category = waiter.getTextOrNull(container, categoryLocator);
        String price = waiter.getTextOrNull(container, priceLocator);
        String quantity = waiter.getTextOrNull(container, quantityLocator);
        String total = waiter.getTextOrNull(container, totalLocator);
        WebElement deleteButton = waiter.findOrNull(container, deleteButtonLocator);

        ProductInCart productInCart = ProductInCart.builder()
                .image(image)
                .name(name)
                .nameAsText(name.getText())
                .category(category)
                .price(price)
                .quantity(quantity)
                .total(total)
                .deleteButton(deleteButton)
                .build();

        productCards.add(productInCart);
    }

    return productCards;
    }

    public ProductInCart getProductByName(List<ProductInCart> allProductsInCart, String productName) {
        return allProductsInCart.stream().filter(productInCart -> productInCart.getNameAsText().equals(productName)).findFirst().orElseThrow();
    }
}
