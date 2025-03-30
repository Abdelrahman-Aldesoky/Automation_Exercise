package org.example.utils;

public class TestData {
    // User data as direct variables
    private static final String firstName = "Abdelrahman";
    private static final String lastName = "Aldesoky";
    private static final String fullName = firstName+" "+lastName;
    private static final String email = "abdelrahman.mohamed.aldesoky@gmail.com";
    //private static final String dynamic_email = System.currentTimeMillis()+"@example.com";
    private static final String password = "helloDepi!";
    private static final String address = "14 Alghawas Abdelaziz shehata";
    private static final String state = "Cairo";
    private static final String city = "Nasr City";
    private static final String zipCode = "11727";
    private static final String mobileNumber = "01007016760";
    private static final String country = "Canada";
    private static final String title = "Mr";
    private static final String dayOfBirth = "10";
    private static final String monthOfBirth = "8";
    private static final String yearOfBirth = "1990";

    // Product data as direct variable
    private static final int productId = 1;

    // Getters for user data
    public static String getFirstName() {
        return firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static String getFullName() {
        return fullName;
    }

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }

    public static String getAddress() {
        return address;
    }

    public static String getState() {
        return state;
    }

    public static String getCity() {
        return city;
    }

    public static String getZipCode() {
        return zipCode;
    }

    public static String getMobileNumber() {
        return mobileNumber;
    }

    public static String getCountry() {
        return country;
    }

    public static String getTitle() {
        return title;
    }

    public static String getDayOfBirth() {
        return dayOfBirth;
    }

    public static String getMonthOfBirth() {
        return monthOfBirth;
    }

    public static String getYearOfBirth() {
        return yearOfBirth;
    }

    // Getter for product ID
    public static int getProductId() {
        return productId;
    }
}