package org.example.tests.purchase;

import org.example.tests.BaseTest;
import org.example.pages.account.LoginPage;
import org.example.pages.checkout.CartPage;
import org.example.pages.checkout.CheckoutPage;
import org.example.pages.checkout.PaymentPage;
import org.example.pages.common.HomePage;
import org.example.pages.common.Navigation;
import org.example.pages.product.ProductDetailsPage;
import org.example.pages.product.ProductsPage;
import org.example.utils.TestDataProvider;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Map;

public class DetailsPurchaseTest extends BaseTest {

    @Test(dataProvider = "purchaseTestData", dataProviderClass = TestDataProvider.class,
            description = "Test purchase from product details page")
    public void testAddToCartFromDetailsPageAndCheckout(Map<String, String> data) {
        Reporter.log("Starting product details purchase test with ID: " + data.get("id"), true);
        Reporter.log("Test description: " + data.get("testDescription"), true);
        Reporter.log("Test data: " + data, true);

        Navigation navigation = new Navigation(driver);
        Reporter.log("Navigation object created", true);

        Reporter.log("Navigating to login page", true);
        navigation.navigateToLoginSignup();

        LoginPage loginPage = new LoginPage(driver);
        Reporter.log("Login page object created", true);

        Reporter.log("Attempting login with email: " + data.get("email"), true);
        loginPage.login(data.get("email"), data.get("password"));

        HomePage homePage = new HomePage(driver);
        Reporter.log("Home page object created", true);

        boolean isLoggedIn = homePage.isLoggedIn();
        Reporter.log("Login status: " + isLoggedIn, true);
        Assert.assertTrue(isLoggedIn, "User should be logged in");

        Reporter.log("Navigating to products page", true);
        navigation.navigateToProducts();

        ProductsPage productsPage = new ProductsPage(driver);
        Reporter.log("Products page object created", true);

        int productId = Integer.parseInt(data.get("productId"));
        Reporter.log("Navigating to product details page for product ID: " + productId, true);
        productsPage.navigateToProductDetailsPage(productId);

        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        Reporter.log("Product details page object created", true);

        Reporter.log("Adding product to cart from details page", true);
        productDetailsPage.addToCart();

        Reporter.log("Clicking view cart", true);
        productDetailsPage.viewCart();

        CartPage cartPage = new CartPage(driver);
        Reporter.log("Cart page object created", true);

        boolean isProductInCart = cartPage.isProductInCart(productId);
        Reporter.log("Product in cart status: " + isProductInCart + " for product ID: " + productId, true);
        Assert.assertTrue(isProductInCart, "Product should be in cart");

        Reporter.log("Proceeding to checkout", true);
        cartPage.proceedToCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        Reporter.log("Checkout page object created", true);

        boolean isProductInCheckout = checkoutPage.isProductInCheckout(productId);
        Reporter.log("Product in checkout status: " + isProductInCheckout + " for product ID: " + productId, true);
        Assert.assertTrue(isProductInCheckout, "Product should be in checkout");

        Reporter.log("Placing order", true);
        checkoutPage.placeOrder();

        PaymentPage paymentPage = new PaymentPage(driver);
        Reporter.log("Payment page object initialized", true);

        boolean shouldSucceed = Boolean.parseBoolean(data.get("shouldSucceed"));
        Reporter.log("Expected payment result - shouldSucceed: " + shouldSucceed, true);

        boolean isOnPaymentPage = paymentPage.isOnPaymentPage();
        Reporter.log("Current payment page status: " + isOnPaymentPage, true);

        if (shouldSucceed) {
            Assert.assertTrue(isOnPaymentPage, "Should be on payment page");
            Reporter.log("Verified payment page displayed as expected", true);
        } else {
            Assert.assertFalse(isOnPaymentPage, "Should not be on payment page");
            Reporter.log("Verified payment failed as expected", true);
        }

        Reporter.log("Details purchase test completed for: " + data.get("id"), true);
    }
}