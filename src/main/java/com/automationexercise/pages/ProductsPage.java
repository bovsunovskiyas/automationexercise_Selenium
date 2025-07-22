package com.automationexercise.pages;

import com.automationexercise.helpers.Waiter;
import com.automationexercise.pages.interfaces.IProductable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage extends BasePage implements IProductable {

    private final Waiter waiter = new Waiter(getDriver());

    // --- Locators ---
    private final By allProductsTitleLocator = By.xpath("//h2[text()='All Products']");
    private final By productsListLocator = By.className("features_items");

    // --- Locators for Search ---
    private final By searchInputLocator = By.id("search_product");
    private final By searchButtonLocator = By.id("submit_search");
    private final By searchedProductsTitleLocator = By.xpath("//h2[text()='Searched Products']");


    public boolean isAllProductsPageVisible() {
        // Чекаємо на контейнер продуктів, перш ніж перевіряти заголовок
        waiter.waitUntilVisibleOfElementLocated(productsListLocator);
        return waiter.waitUntilVisibleOfElementLocated(allProductsTitleLocator).isDisplayed();
    }

    public boolean isProductsListVisible() {
        return waiter.waitUntilVisibleOfElementLocated(productsListLocator).isDisplayed();
    }

    /**
     * Отримує перший продукт зі списку за допомогою інтерфейсу і клікає на кнопку "View Product".
     * @return Новий екземпляр сторінки ProductDetailPage.
     */
    public ProductDetailPage clickViewProductOfFirstProduct() {
        // Використовуємо метод з інтерфейсу IProductable
        getAllProducts().get(0).getViewProductButton().click();
        return new ProductDetailPage();
    }

    // --- Methods for Search ---

    public ProductsPage enterSearchQuery(String productName) {
        waiter.waitUntilVisibleOfElementLocated(searchInputLocator).sendKeys(productName);
        return this;
    }

    public SearchedProductsPage clickSearchButton() {
        waiter.waitUntilClickable(searchButtonLocator).click();
        return new SearchedProductsPage();
    }

    public boolean isSearchedProductsTitleVisible() {
        return waiter.waitUntilVisibleOfElementLocated(searchedProductsTitleLocator).isDisplayed();
    }




//    public ProductsPage assertNumberOfProducts(int expectedCount) {
//        int actualCount = getAllProducts().size();
//        Assert.assertEquals(actualCount, expectedCount, "The number of products on the page is incorrect.");
//        return this;
//    }

}



///**
 //     * @return List of all product names, which visible on the page.
 //     */
//    public List<String> getDisplayedProductNames() {
//        List<WebElement> productElements = waiter.waitUntilVisibleOfElementLocated(productsListLocator)
//                .findElements(singleProductLocator);
//
//        return productElements.stream()
//                .map(element -> element.findElement(productNameLocator).getText())
//                .collect(Collectors.toList());
//    }
