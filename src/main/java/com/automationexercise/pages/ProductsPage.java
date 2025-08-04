package com.automationexercise.pages;

import com.automationexercise.helpers.Waiter;
import com.automationexercise.pages.interfaces.IProductable;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

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

    public ProductDetailsPage clickViewProductOfFirstProduct() {
        // Використовуємо метод з інтерфейсу IProductable
        getAllProducts().get(0).getViewProductButton().click();
        return new ProductDetailsPage();
    }

    @Step("Verify Product Page is successfully loaded")
    public ProductsPage assertProductsPageSuccessfullyLoaded(){
        waiter.waitUntilVisibleOfElementLocated(productsListLocator);
        return new ProductsPage();
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
