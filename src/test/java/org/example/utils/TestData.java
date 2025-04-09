package org.example.utils;

import java.util.Map;

public class TestData {
    private static final String SIGNUP_FILE = "signup_data.xlsx";
    private static final String LOGIN_FILE = "login_data.xlsx";
    private static final String PRODUCT_FILE = "purchase_test_data.xlsx";

    // Default test data ID
    private static final String DEFAULT_DATA_ID = "default";

    // Get user signup data
    public static Map<String, String> getSignupData() {
        return getSignupData(DEFAULT_DATA_ID);
    }

    public static Map<String, String> getSignupData(String dataId) {

        return ExcelUtils.getTestDataByRowId(SIGNUP_FILE, "UserData", "id", dataId);
    }

    // Get login credentials
    public static Map<String, String> getLoginData() {
        return getLoginData(DEFAULT_DATA_ID);
    }

    public static Map<String, String> getLoginData(String dataId) {
        return ExcelUtils.getTestDataByRowId(LOGIN_FILE, "Credentials", "id", dataId);
    }

    // Get product data
    public static Map<String, String> getProductData() {
        return getProductData(DEFAULT_DATA_ID);
    }

    public static Map<String, String> getProductData(String dataId) {
        return ExcelUtils.getTestDataByRowId(PRODUCT_FILE, "Products", "id", dataId);
    }

    // Convenience methods for login data
    public static String getEmail() {
        Map<String, String> data = getLoginData();
        return data.get("email");
    }

    public static String getPassword() {
        Map<String, String> data = getLoginData();
        return data.get("password");
    }

    // Convenience methods for signup data
    public static String getFirstName() {
        Map<String, String> data = getSignupData();
        return data.get("firstName");
    }

    public static String getLastName() {
        Map<String, String> data = getSignupData();
        return data.get("lastName");
    }

    public static String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public static String getAddress() {
        Map<String, String> data = getSignupData();
        return data.get("address");
    }

    public static String getState() {
        Map<String, String> data = getSignupData();
        return data.get("state");
    }

    public static String getCity() {
        Map<String, String> data = getSignupData();
        return data.get("city");
    }

    public static String getZipCode() {
        Map<String, String> data = getSignupData();
        return data.get("zipCode");
    }

    public static String getMobileNumber() {
        Map<String, String> data = getSignupData();
        return data.get("mobileNumber");
    }

    public static String getCountry() {
        Map<String, String> data = getSignupData();
        return data.get("country");
    }

    public static String getTitle() {
        Map<String, String> data = getSignupData();
        return data.get("title");
    }

    public static String getDayOfBirth() {
        Map<String, String> data = getSignupData();
        return data.get("dayOfBirth");
    }

    public static String getMonthOfBirth() {
        Map<String, String> data = getSignupData();
        return data.get("monthOfBirth");
    }

    public static String getYearOfBirth() {
        Map<String, String> data = getSignupData();
        return data.get("yearOfBirth");
    }

    // Convenience methods for product data
    public static int getProductId() {
        Map<String, String> data = getProductData();
        return Integer.parseInt(data.get("productId"));
    }
}