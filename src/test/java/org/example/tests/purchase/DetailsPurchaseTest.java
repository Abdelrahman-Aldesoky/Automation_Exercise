package org.example.tests.purchase;

import org.example.base.BaseTest;
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

    @Test(groups = "purchase", dependsOnGroups = "login", dataProvider = "purchaseTestData", dataProviderClass = TestDataProvider.class,
            description = "Test purchase from product details page")
    public void testAddToCartFromDetailsPageAndCheckout(Map<String, String> data) {
        Reporter.log("Starting product details purchase test: " + data.get("testDescription"), true);

        Navigation navigation = new Navigation(driver);
        navigation.navigateToLoginSignup();
        LoginPage loginPage = new LoginPage(driver);
        Reporter.log("Navigated to login page", true);

        loginPage.login(data.get("email"), data.get("password"));
        HomePage homePage = new HomePage(driver);
        Reporter.log("Logged in successfully", true);

        Assert.assertTrue(homePage.isLoggedIn(), "User should be logged in");
        Reporter.log("Verified user is logged in", true);

        navigation.navigateToProducts();
        ProductsPage productsPage = new ProductsPage(driver);
        Reporter.log("Navigated to products page", true);

        int productId = Integer.parseInt(data.get("productId"));
        productsPage.navigateToProductDetailsPage(productId);
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        Reporter.log("Navigated to product details page for product " + productId, true);

        productDetailsPage.addToCart();
        Reporter.log("Added product to cart from details page", true);

        productDetailsPage.viewCart();
        CartPage cartPage = new CartPage(driver);
        Reporter.log("Navigated to cart page", true);

        Assert.assertTrue(cartPage.isProductInCart(productId), "Product should be in cart");
        Reporter.log("Verified product is in cart", true);

        cartPage.proceedToCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        Reporter.log("Proceeded to checkout", true);

        Assert.assertTrue(checkoutPage.isProductInCheckout(productId), "Product should be in checkout");
        Reporter.log("Verified product is in checkout", true);

        checkoutPage.placeOrder();
        PaymentPage paymentPage = new PaymentPage(driver);
        Reporter.log("Placed order to reach payment page", true);

        boolean shouldSucceed = Boolean.parseBoolean(data.get("shouldSucceed"));
        if (shouldSucceed) {
            Assert.assertTrue(paymentPage.isOnPaymentPage(), "Should be on payment page");
            Reporter.log("Verified we are on payment page", true);
        } else {
            Assert.assertFalse(paymentPage.isOnPaymentPage(), "Should not be on payment page");
            Reporter.log("Verified payment failed as expected", true);
        }
    }
}