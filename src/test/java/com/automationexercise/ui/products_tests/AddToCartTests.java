package com.automationexercise.ui.products_tests;

import com.automationexercise.models.ProductCard;
import com.automationexercise.models.ProductInCart;
import com.automationexercise.pages.CartPage;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.ProductsPage;
import com.automationexercise.ui.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AddToCartTests extends BaseTest {

    private ProductsPage productsPage;

    @BeforeMethod
    public void navigateToProductsPage() {
        // 2. Navigate to url 'http://automationexercise.com'
        productsPage = new HomePage()
                .openMainPage()
                .assertMainPageSuccessfullyLoaded()
                .getMainMenu()
                .clickProductsButton()
                .assertProductsPageSuccessfullyLoaded();

        productsPage.scrollToFirstProductElement();

//        // 3. Verify that home page is visible successfully
//        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible.");
//
//        // 4. Click on 'Products' button
//        productsPage = homePage.getMainMenu().clickProductsButton();
//
//        // 5. Verify user is navigated to ALL PRODUCTS page successfully
//        Assert.assertTrue(productsPage.isAllProductsPageVisible(), "'ALL PRODUCTS' page is not visible.");
//
//        productsPage.scrollToFirstProductElement();
    }

    @Test(description = "Test Case 12: Add Products in Cart")
    public void addProductsToCart(){

//        productsPage.scrollToFirstProductElement();

        List<ProductCard> allProducts = productsPage.getAllProducts();
        allProducts
                .get(0)
                .clickAddToCartButton()
                .assertModalIsVisible()
                .clickContinueShoppingButton()
                .assertModalIsNotVisible();

//        allProducts
//                .get(0)
//                .clickAddToCartButton()
//                .assertModalIsVisible()
//                .clickContinueShoppingButton()
//                .assertModalIsNotVisible();

        CartPage cartPage = allProducts
                .get(1)
                .clickAddToCartButton()
                .assertModalIsVisible()
                .clickViewCartButton();

        List<ProductInCart> allProductsInCart = cartPage.getAllProductsInCart();

        ProductInCart expectedFirstProductInCart = ProductInCart.builder()
                .nameAsText("Blue Top")
                .category("Women > Tops")
                .price("Rs. 500")
                .quantity("1")
                .total("Rs. 500")
                .build();

        ProductInCart actualFirstProductInCart = cartPage.getProductByName(allProductsInCart, "Blue Top");

        assertThat(actualFirstProductInCart)
                .as("First product in cart is not as expected: " + actualFirstProductInCart.getNameAsText())
                .usingRecursiveComparison()
                .ignoringFields("image","name","deleteButton")
                .isEqualTo(expectedFirstProductInCart);

        ProductInCart expectedSecondProductInCart = ProductInCart.builder()
                .nameAsText("Men Tshirt")
                .category("Men > Tshirts")
                .price("Rs. 400")
                .quantity("1")
                .total("Rs. 400")
                .build();

        ProductInCart actualSecondProductInCart = cartPage.getProductByName(allProductsInCart, "Men Tshirt");

        assertThat(actualSecondProductInCart)
                .as("Second product in cart is not as expected.")
                .usingRecursiveComparison()
                .ignoringFields("image","name","deleteButton")
                .isEqualTo(expectedSecondProductInCart);

    }
}

