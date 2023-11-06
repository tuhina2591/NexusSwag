package utils;

import org.openqa.selenium.By;

public class StringConstants {

    public static String BROWSER_NAME = "Chrome";
    public static final String URL =" https://www.saucedemo.com/ ";
    public static final String USER_NAME = "standard_user";
    public static final String PROBLEM_USER = "problem_user";
    public static final String PASSWORD = "secret_sauce";
    public static final By USER_NAME_PATH = By.id("user-name");
    public static final By PASSWORD_PATH = By.id("password");
    public static final By LOGIN_BUTTON = By.id("login-button");
    public static final By CART_BUTTONS = By.xpath("//button[@class='btn btn_primary btn_small btn_inventory ']");
    public static final By PRODUCTS_TITLE= By.xpath("//span[@class='title']");
    public static final By FAILED_PRODUCTS= By.xpath("//button[contains(text(), 'Add to cart')]/../preceding-sibling::div//div[@class= 'inventory_item_name ']");
    public static final By PRODUCT_LIST = By.xpath("//div[@class='inventory_item_name ']");
    public static final By FILTER = By.xpath("//select[@class='product_sort_container']");
}
