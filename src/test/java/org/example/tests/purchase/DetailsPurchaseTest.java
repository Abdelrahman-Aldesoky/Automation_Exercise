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
import org.example.utils.TestData;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class DetailsPurchaseTest extends BaseTest {

    @Test(description = "Test purchase from product details page")
    public void testAddToCartFromDetailsPageAndCheckout() {
        // Log the start of the test
        Reporter.log("Starting product details purchase test", true);

        // Login first
        Navigation navigation = new Navigation(driver);
        LoginPage loginPage = navigation.navigateToLoginSignup();
        Reporter.log("Navigated to login page", true);

        HomePage homePage = loginPage.login(TestData.getEmail(), TestData.getPassword());
        Reporter.log("Logged in successfully", true);

        // Verify login was successful
        Assert.assertTrue(homePage.isLoggedIn(), "User should be logged in");
        Reporter.log("Verified user is logged in", true);

        // Navigate to products page
        ProductsPage productsPage = navigation.navigateToProducts();
        Reporter.log("Navigated to products page", true);

        // Rest of the test remains unchanged
        ProductDetailsPage productDetailsPage = productsPage.navigateToProductDetailsPage(TestData.getProductId());
        Reporter.log("Navigated to product details page for product 1", true);

        // Add to cart from product details page
        productDetailsPage.addToCart();
        Reporter.log("Added product to cart from details page", true);

        // Go to cart
        CartPage cartPage = productDetailsPage.viewCart();
        Reporter.log("Navigated to cart page", true);

        // Verify product is in cart
        Assert.assertTrue(cartPage.isProductInCart(TestData.getProductId()), "Product should be in cart");
        Reporter.log("Verified product is in cart", true);

        // Proceed to checkout
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();
        Reporter.log("Proceeded to checkout", true);

        // Verify product is in checkout
        Assert.assertTrue(checkoutPage.isProductInCheckout(TestData.getProductId()), "Product should be in checkout");
        Reporter.log("Verified product is in checkout", true);

        // Place order to reach payment page
        PaymentPage paymentPage = checkoutPage.placeOrder();
        Reporter.log("Placed order to reach payment page", true);

        // Verify we're on the payment page
        Assert.assertTrue(paymentPage.isOnPaymentPage(), "Should be on payment page");
        Reporter.log("Verified we are on payment page", true);
    }
}