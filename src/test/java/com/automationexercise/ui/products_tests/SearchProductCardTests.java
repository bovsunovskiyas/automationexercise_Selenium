package com.automationexercise.ui.products_tests;

import com.automationexercise.models.ProductCard;
import com.automationexercise.ui.BaseTest;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SearchProductCardTests extends BaseTest {

    @Test(description = "Test Case 9: Search Product")
    public void testSearchProduct() {
        // 2. Navigate to url 'http://automationexercise.com'
        HomePage homePage = new HomePage().openMainPage();

        // 3. Verify that home page is visible successfully
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible.");

        // 4. Click on 'Products' button
        ProductsPage productsPage = homePage.getMainMenu().clickProductsButton();

        // 5. Verify user is navigated to ALL PRODUCTS page successfully
        Assert.assertTrue(productsPage.isAllProductsPageVisible(), "'ALL PRODUCTS' page is not visible.");

        // 6. Enter product name in search input and click search button
        String searchQuery = "Blue Top";
        productsPage.enterSearchQuery(searchQuery)
                .clickSearchButton();

        // 7. Verify 'SEARCHED PRODUCTS' is visible
        Assert.assertTrue(productsPage.isSearchedProductsTitleVisible(), "'SEARCHED PRODUCTS' title is not visible.");

        // 8. Verify all the products related to search are visible
        // Отримуємо список об'єктів Product за допомогою методу з інтерфейсу
        List<ProductCard> productCards = productsPage.getAllProducts();
        Assert.assertFalse(productCards.isEmpty(), "No products were found for the search query.");

        // Перевіряємо, що назва кожного знайденого продукту відповідає пошуковому запиту
        for (ProductCard productCard : productCards) {
            String productName = productCard.getName();
            Assert.assertTrue(productName.toLowerCase().contains(searchQuery.toLowerCase()),
                    "Product '" + productName + "' does not match the search query '" + searchQuery + "'.");
        }
    }
}
