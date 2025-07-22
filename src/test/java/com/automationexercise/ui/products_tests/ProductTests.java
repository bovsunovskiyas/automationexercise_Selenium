package com.automationexercise.ui.products_tests;

import com.automationexercise.models.ProductCard;
import com.automationexercise.models.ProductDetails;
import com.automationexercise.pages.SearchedProductsPage;
import com.automationexercise.ui.BaseTest;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.ProductDetailsPage;
import com.automationexercise.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
    public void verifyAllProductsAndProductDetailTest() {
        // Попередні кроки (1-5) виконано в @BeforeMethod
//
        List<ProductCard> allProductCards = productsPage
                .assertNumberOfProducts(34)
                .getAllProducts();

        String productName = "Blue Top";
        String productPrice = "Rs. 500";

        ProductCard actualProductCard = allProductCards.get(0);
        ProductCard expectedProductCard = ProductCard.builder()
                .name(productName)
                .price(productPrice).build();
//        System.out.println(allProductCards);

        productsPage.assertProductDetails(actualProductCard, expectedProductCard);

//        System.out.println(allProductCards.get(5));
//        System.out.println(allProductCards.stream().count());
//        System.out.println(allProductCards);

        // 7. Click on 'View Product' of first product
        ProductDetailsPage productDetailsPage = actualProductCard.clickViewProductButton();

        ProductDetails actualProductDetails = productDetailsPage.getProductDetails();
        ProductDetails expectedProductDetails = ProductDetails.builder()
                .name(productName)
                .price(productPrice)
                .category("Category: Women > Tops")
                .availability("Availability: In Stock")
                .condition("Condition: New")
                .brand("Brand: Polo")
                .build();

        assertThat(actualProductDetails)
                .usingRecursiveComparison()
                .isEqualTo(expectedProductDetails);

//        // 6. The products list is visible
//        Assert.assertTrue(productsPage.isProductsListVisible(), "Products list is not visible.");
//
//        // Перевіряємо кількість продуктів на сторінці
//        productsPage.assertNumberOfProducts(34);
//
//        // 7. Click on 'View Product' of first product
////        ProductDetailsPage productDetailsPage = productsPage.clickViewProductOfFirstProduct();
//
//        // 8. User is landed to product detail page
//        // 9. Verify that detail is visible: product name, category, price, availability, condition, brand
//        productDetailsPage.assertProductDetailPageLoaded();
//        Assert.assertTrue(productDetailsPage.isProductNameVisible(), "Product name is not visible.");
//        Assert.assertTrue(productDetailsPage.isCategoryVisible(), "Category is not visible.");
//        Assert.assertTrue(productDetailsPage.isPriceVisible(), "Price is not visible.");
//        Assert.assertTrue(productDetailsPage.isAvailabilityVisible(), "Availability is not visible.");
//        Assert.assertTrue(productDetailsPage.isConditionVisible(), "Condition is not visible.");
//        Assert.assertTrue(productDetailsPage.isBrandVisible(), "Brand is not visible.");
    }

    @Test(description = "Test Case 9: Search Product")
    public void verifySearchProduct() {
        // Попередні кроки (1-5) виконано в @BeforeMethod

        // 6. Enter product name in search input and click search button
        String searchQuery = "top for";

        SearchedProductsPage searchedProductsPage = productsPage
                .enterSearchQuery(searchQuery)
                .clickSearchButton();


        List<ProductCard> allProductCards = searchedProductsPage
                .assertNumberOfProducts(2)
                .getAllProducts();
        System.out.println(allProductCards);


        ProductCard actualFirstProductCard = allProductCards.get(0);
        ProductCard expectedFirstProductCard = ProductCard.builder()
                .name("Madame Top For Women")
                .price("Rs. 1000").build();


        searchedProductsPage.assertProductDetails(actualFirstProductCard, expectedFirstProductCard);

        ProductCard actualSecondProductCard = allProductCards.get(1);
        ProductCard expectedSecondProductCard = ProductCard.builder()
                .name("Lace Top For Women")
                .price("Rs. 1400").build();

        searchedProductsPage.assertProductDetails(actualSecondProductCard, expectedSecondProductCard);


        // 7. Verify 'SEARCHED PRODUCTS' is visible
        Assert.assertTrue(productsPage.isSearchedProductsTitleVisible(), "'SEARCHED PRODUCTS' title is not visible.");

        // 8. Verify all the products related to search are visible
        List<ProductCard> productCards = productsPage.getAllProducts();
        Assert.assertFalse(productCards.isEmpty(), "No products were found for the search query.");

        for (ProductCard productCard : productCards) {
            String productName = productCard.getName();
            Assert.assertTrue(productName.toLowerCase().contains(searchQuery.toLowerCase()),
                    "Product '" + productName + "' does not match the search query '" + searchQuery + "'.");
        }
    }


}

