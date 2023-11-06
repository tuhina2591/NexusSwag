package tests;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import static utils.StringConstants.*;

public class LetsWriteTest {
 WebDriver driver;
 WebDriverWait wait;
    @Before
    public void initiate() {

        System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    @After
    public void CloseBrowser(){
        driver.quit();
    }

    @Test()
    public void checkBrowserInit() {
        Assert.assertEquals(driver.getTitle(),"Swag Labs");
    }

     @Test
    /*
    Login with problem_user, password : secret_sauce
    Add items to cart and if any items fails to get added in cart print the name
    as an example we have 6 items in inventory,
    then only 2 items fails to get added in cart then print the name of those 2 items
    * */
    public void addItemsToCartVerify() {

         login(PROBLEM_USER, PASSWORD);

         List<WebElement> buttonList = driver.findElements(CART_BUTTONS);
         //Adding Items to the List
         buttonList.forEach(WebElement::click);

         //Identifying and printing failed elements
         List<WebElement> notAddedItems = driver.findElements(FAILED_PRODUCTS);
         //List<WebElement> notAddedItems =  driver.findElements(By.xpath("//button[contains(text(), 'Add to cart')]/../..//div[@class='inventory_item_name ']"));

         List<String> notAddedItemList = getItemNames(notAddedItems);
         System.out.println("Items that could not be added are "+notAddedItemList);
    }
 
    @Test
     /* Login with --> standard_user,  password : secret_sauce
        Verify that user successfully logged in
        sort the list - choose any option in DropDown.
        verify the list is sorted correctly
    * */
    public void verifyIsLoginSuccessful() {
        login(USER_NAME, PASSWORD);

        //expected list
        List<WebElement> nameList = driver.findElements(PRODUCT_LIST);

        List<String> expectedList = getItemNames(nameList);

        List<String> expectedIemList = new ArrayList<>(expectedList);
        expectedIemList.sort(Comparator.comparing(Object::toString).reversed());

        //Click button
        Select se = new Select(driver.findElement(FILTER));
        se.selectByIndex(1);

        List<WebElement> nameListActual = driver.findElements(PRODUCT_LIST);
        List<String> actualItemsList = getItemNames(nameListActual);

        Assert.assertEquals(expectedIemList, actualItemsList);
   }

   private  void login(String userName, String password) {

       WebElement uName = driver.findElement(USER_NAME_PATH);
       WebElement pWord = driver.findElement(PASSWORD_PATH);
       WebElement loginButton = driver.findElement(LOGIN_BUTTON);

       uName.sendKeys(userName);
       pWord.sendKeys(password);
       loginButton.click();

       wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCTS_TITLE));
       String welcomeText = driver.findElement(PRODUCTS_TITLE).getText();
       Assert.assertEquals(welcomeText, "Products");
   }

   private List<String> getItemNames(List<WebElement> item){

       return item.stream()
               .map(WebElement::getText)
               .toList();
   }

}
