package com.automationexercise.pages.interfaces;

import com.automationexercise.helpers.Waiter;
import com.automationexercise.models.ProductCard;
import com.automationexercise.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;


public interface IProductable {

    default Waiter waiter(){
        return new Waiter(BasePage.getDriver());
    }

    By containerLocator = By.xpath("//div[@class='product-image-wrapper']");
    By productImageLocator = By.xpath(".//img");
    By productPriceLocator = By.xpath(".//h2[1]");
    By productNameLocator = By.xpath(".//p[1]");
    By addToCartButtonLocator = By.xpath(".//a[contains(@class, 'add-to-cart')][1]");
    By viewProductButtonLocator = By.xpath(".//i[contains(@class, 'fa-plus-square')][1]");

    default WebElement findOrNull(WebElement container, By locator){
        List<WebElement> elements = container.findElements(locator);
        return elements.isEmpty() ? null : elements.get(0);
    }

    default String getTextOrNull(WebElement container, By locator){
        List<WebElement> elements = container.findElements(locator);
        return elements.isEmpty() ? null : elements.get(0).getText();
    }


    default List<ProductCard> getAllProducts(){
        List<ProductCard> productCards = new ArrayList<>();
        List<WebElement> productContainer = BasePage.getDriver().findElements(containerLocator);
        for (WebElement container : productContainer){
            WebElement productImage = findOrNull(container, productImageLocator);
            String productPrice = getTextOrNull(container, productPriceLocator);
            String productName = getTextOrNull(container, productNameLocator);
            WebElement addToCartButton = findOrNull(container, addToCartButtonLocator);
            WebElement viewProductButton = findOrNull(container, viewProductButtonLocator);

            ProductCard productCard = ProductCard.builder()
                    .image(productImage)
                    .price(productPrice)
                    .name(productName)
                    .addToCartButton(addToCartButton)
                    .viewProductButton(viewProductButton)
                    .build();

            productCards.add(productCard);
        }

        return productCards;
    }

    default IProductable assertNumberOfProducts(int expectedCount) {
        waiter().waitUntilNumberOfElementsToBe(containerLocator, expectedCount);
        return this;
    }

    default IProductable assertProductDetails(ProductCard actualProductCard, ProductCard expectedProductCard) {
        Assert.assertNotNull(actualProductCard.getImage(), format("The product image is null. [%s]", expectedProductCard.getName()));
        Assert.assertEquals(actualProductCard.getPrice(), expectedProductCard.getPrice(), format("The product price is incorrect. [%s]", expectedProductCard.getName()));
        Assert.assertEquals(actualProductCard.getName(), expectedProductCard.getName(), format("The product name is incorrect. [%s]", expectedProductCard.getName()));
        Assert.assertNotNull(actualProductCard.getAddToCartButton(), "The 'Add to cart' button is missing. "+ expectedProductCard.getName());
        Assert.assertNotNull(actualProductCard.getViewProductButton(), "The 'View product' button is missing. "+ expectedProductCard.getName());

        return this;
    }

}
