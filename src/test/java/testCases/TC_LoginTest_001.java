package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;

import java.io.IOException;
import java.util.List;

public class TC_LoginTest_001 extends BaseClass {

    @Test(priority = 1)
    public void loginTest() throws InterruptedException {
        System.out.println("URL is opened");
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.setUser(user);
        System.out.println("Entered UserName");

        loginPage.setPassword(password);
        System.out.println("Entered password");
        loginPage.clickSubmit();

        if (webDriver.getTitle().equals("Swag Labs")) {
            Assert.assertTrue(true);
            System.out.println("Logging Test passed");
        } else {
            Assert.assertTrue(false);
            System.out.println("Logging Test failed");
        }
        Thread.sleep(5000);
    }

    @Test(priority = 2)
    public void addToCart() throws InterruptedException {
        System.out.println("Adding Item to cart");
        webDriver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(3000);

    }

    @Test(priority = 3)
    public void assertQuantityAndItemDescription() throws InterruptedException {
        System.out.println("Asserting the quantity and Item Description");
        String quantity = webDriver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();
        if (quantity.equals("1")) {
            System.out.println("Only one item added to cart");
            Assert.assertTrue(true);
        } else {
            System.out.println("More the one item added to cart");
            Assert.assertTrue(false);
        }
        System.out.println("Clicking on Cart for item Description");
        //Instantiating Actions class
        Actions actions = new Actions(webDriver);
        WebElement cart = webDriver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        //To mouseover on cart
        actions.moveToElement(cart);

        //build()- used to compile all the actions into a single step
        actions.click().build().perform();
        Thread.sleep(3000);

        String itemDescription = webDriver.findElement(By.xpath("//div[@class='inventory_item_desc']")).getText();
        if (itemDescription.equals("carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.")) {
            System.out.println("Item description of Sauce Labs Backpack");
            Assert.assertTrue(true);
        } else {
            System.out.println("Not an Item description of Sauce Labs Backpack");
            Assert.assertTrue(false);
        }
        Thread.sleep(3000);


    }

    @Test(priority = 4)
    public  void removeItemAndAssertToEmpty() throws InterruptedException {
        System.out.println("Removing the Element from cart");
        webDriver.findElement(By.id("remove-sauce-labs-backpack")).click();
        System.out.println("Item Removed");
        Thread.sleep(2000);
        List<WebElement> quant=webDriver.findElements(By.xpath("//span[@class='shopping_cart_badge']"));
        if(quant.size()==0){
            System.out.println("Cart is empty");
            Assert.assertTrue(true);
        }else{
            System.out.println("Cart is not empty");
            Assert.assertTrue(false);
        }
        Thread.sleep(2000);
    }
    @Test(priority = 5)
    public void addAnotherItemToCart() throws InterruptedException {
        webDriver.findElement(By.id("continue-shopping")).click();
        System.out.println("Adding Another Item to cart");
        webDriver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
        Thread.sleep(3000);
        System.out.println("Item Added");

    }
    @Test(priority = 6)
    public void checkOut() throws InterruptedException {
        Actions actions = new Actions(webDriver);
        WebElement cart = webDriver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        //To mouseover on cart
        actions.moveToElement(cart);

        //build()- used to compile all the actions into a single step
        actions.click().build().perform();
        Thread.sleep(2000);
        webDriver.findElement(By.id("checkout")).click();
        System.out.println("Checked out");

    }
    @Test(priority = 7)
    public void clickOnContinue(){
        webDriver.findElement(By.id("continue")).click();
        String message=webDriver.findElement(By.xpath("//div[@class='error-message-container error']")).getText();
        if(message.equals("Error: First Name is required")){
            Assert.assertTrue(true);
            System.out.println("Error message asserted");
        }else{
            Assert.assertTrue(false);
            System.out.println("Error message not asserted");
        }
    }


}

