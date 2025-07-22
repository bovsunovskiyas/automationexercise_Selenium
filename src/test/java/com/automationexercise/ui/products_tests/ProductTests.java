package com.automationexercise.ui.products_tests;

import com.automationexercise.pages.SearchedProductsPage;
import com.automationexercise.ui.BaseTest;
import com.automationexercise.models.Product;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.ProductDetailPage;
import com.automationexercise.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ProductTests extends BaseTest {

    private ProductsPage productsPage;

    @BeforeMethod
    public void navigateToProductsPage() {
        // 2. Navigate to url 'http://automationexercise.com'
        HomePage homePage = new HomePage().openMainPage();

        // 3. Verify that home page is visible successfully
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible.");

        // 4. Click on 'Products' button
        productsPage = homePage.getMainMenu().clickProductsButton();

        // 5. Verify user is navigated to ALL PRODUCTS page successfully
        Assert.assertTrue(productsPage.isAllProductsPageVisible(), "'ALL PRODUCTS' page is not visible.");

        productsPage.scrollToFirstProductElement();
    }

    @Test(description = "Test Case 8: Verify All Products and product detail page")
    public void verifyAllProductsAndProductDetailPage() {
        // Попередні кроки (1-5) виконано в @BeforeMethod
//
        List<Product> allProducts = productsPage
                .assertNumberOfProducts(34)
                .getAllProducts();

        Product actualProduct = allProducts.get(0);
        Product expectedProduct = Product.builder()
                .name("Blue Top")
                .price("Rs. 500").build();
        System.out.println(allProducts);

        productsPage.assertProductDetails(actualProduct, expectedProduct);


        System.out.println(allProducts.get(5));
        System.out.println(allProducts.stream().count());
        System.out.println(allProducts);

        // 6. The products list is visible
        Assert.assertTrue(productsPage.isProductsListVisible(), "Products list is not visible.");

        // Перевіряємо кількість продуктів на сторінці
        productsPage.assertNumberOfProducts(34);

        // 7. Click on 'View Product' of first product
        ProductDetailPage productDetailPage = productsPage.clickViewProductOfFirstProduct();

        // 8. User is landed to product detail page
        // 9. Verify that detail is visible: product name, category, price, availability, condition, brand
        productDetailPage.assertProductDetailPageLoaded();
        Assert.assertTrue(productDetailPage.isProductNameVisible(), "Product name is not visible.");
        Assert.assertTrue(productDetailPage.isCategoryVisible(), "Category is not visible.");
        Assert.assertTrue(productDetailPage.isPriceVisible(), "Price is not visible.");
        Assert.assertTrue(productDetailPage.isAvailabilityVisible(), "Availability is not visible.");
        Assert.assertTrue(productDetailPage.isConditionVisible(), "Condition is not visible.");
        Assert.assertTrue(productDetailPage.isBrandVisible(), "Brand is not visible.");
    }

    @Test(description = "Test Case 9: Search Product")
    public void verifySearchProduct() {
        // Попередні кроки (1-5) виконано в @BeforeMethod

        // 6. Enter product name in search input and click search button
        String searchQuery = "top for";

        SearchedProductsPage searchedProductsPage = productsPage
                .enterSearchQuery(searchQuery)
                .clickSearchButton();


        List<Product> allProducts = searchedProductsPage
                .assertNumberOfProducts(2)
                .getAllProducts();
        System.out.println(allProducts);


        Product actualFirstProduct = allProducts.get(0);
        Product expectedFirstProduct = Product.builder()
                .name("Madame Top For Women")
                .price("Rs. 1000").build();


        searchedProductsPage.assertProductDetails(actualFirstProduct, expectedFirstProduct);

        Product actualSecondProduct = allProducts.get(1);
        Product expectedSecondProduct = Product.builder()
                .name("Lace Top For Women")
                .price("Rs. 1400").build();

        searchedProductsPage.assertProductDetails(actualSecondProduct, expectedSecondProduct);


        // 7. Verify 'SEARCHED PRODUCTS' is visible
        Assert.assertTrue(productsPage.isSearchedProductsTitleVisible(), "'SEARCHED PRODUCTS' title is not visible.");

        // 8. Verify all the products related to search are visible
        List<Product> products = productsPage.getAllProducts();
        Assert.assertFalse(products.isEmpty(), "No products were found for the search query.");

        for (Product product : products) {
            String productName = product.getName();
            Assert.assertTrue(productName.toLowerCase().contains(searchQuery.toLowerCase()),
                    "Product '" + productName + "' does not match the search query '" + searchQuery + "'.");
        }
    }


}

