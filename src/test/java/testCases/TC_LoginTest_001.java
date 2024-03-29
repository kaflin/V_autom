package testCases;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.BaseClass;
import pageObjects.LoginPage;
import java.util.List;

public class TC_LoginTest_001 extends BaseClass {

    @Test(priority = 1)
    public void loginTest() throws InterruptedException {
        logger.info("URL is opened");
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.setUser(user);
        logger.info("Entered Username");

        loginPage.setPassword(password);
        logger.info("Entered password");
        loginPage.clickSubmit();

        if (webDriver.getTitle().equals("Swag Labs")) {
            Assert.assertTrue(true);
            logger.info("Logging Test passed");
        } else {
            Assert.assertTrue(false);
            logger.info("Logging Test failed");
        }
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    public void addToCart() throws InterruptedException {
        logger.info("\n");
        logger.info("Adding Sauce Labs Backpack to cart");
        WebElement element =webDriver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']"));
        action=new Actions(webDriver);
        action.moveToElement(element).click().perform();
        Thread.sleep(2000);
    }

    @Test(priority = 3)
    public void assertQuantityAndItemDescription() throws InterruptedException {
        logger.info("\n");
        logger.info("Asserting the quantity and Item Description");
        String quantity = webDriver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();
        if (quantity.equals("1")) {
            logger.info("Only one item added to cart");
            Assert.assertTrue(true);
        } else {
            logger.info("More than one item added to cart");
            Assert.assertTrue(false);
        }
        logger.info("Clicking on Cart for item Description");
        //Instantiating Actions class
        action = new Actions(webDriver);
        WebElement cart = webDriver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        //To mouseover on cart
        action.moveToElement(cart).click().perform();
        Thread.sleep(2000);
        String itemDescription = webDriver.findElement(By.xpath("//div[@class='inventory_item_desc']")).getText();
        if (itemDescription.equals("carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.")) {
            logger.info("Item description of Sauce Labs Backpack");
            Assert.assertTrue(true);
        } else {
            logger.info("Not an Item description of Sauce Labs Backpack");
            Assert.assertTrue(false);
        }
        Thread.sleep(2000);
    }

    @Test(priority = 4)
    public void removeItemAndAssertToEmpty() throws InterruptedException {
        logger.info("\n");
        logger.info("Removing the Element from cart");
        WebElement element =webDriver.findElement(By.id("remove-sauce-labs-backpack"));
        action = new Actions(webDriver);
        action.moveToElement(element).click().perform();
        logger.info("Item Removed");
        Thread.sleep(2000);
        List<WebElement> quant = webDriver.findElements(By.xpath("//span[@class='shopping_cart_badge']"));
        if (quant.size() == 0) {
            logger.info("Cart is empty");
            Assert.assertTrue(true);
        } else {
            logger.info("Cart is not empty");
            Assert.assertTrue(false);
        }
        Thread.sleep(2000);
    }

    @Test(priority = 5)
    public void addAnotherItemToCart() throws InterruptedException {
        logger.info("\n");
        WebElement element=webDriver.findElement(By.id("continue-shopping"));
        action = new Actions(webDriver);
        action.moveToElement(element).click().perform();
        logger.info("Adding Another Item to cart");
        WebElement element_add=webDriver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)"));
        action = new Actions(webDriver);
        action.moveToElement(element_add).click().perform();
        Thread.sleep(2000);
        logger.info("Item Added");

    }

    @Test(priority = 6)
    public void checkOut() throws InterruptedException {
        logger.info("\n");
        action = new Actions(webDriver);
        WebElement cart = webDriver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        //To mouseover on cart
        action.moveToElement(cart).click().perform();
        Thread.sleep(2000);
        webDriver.findElement(By.id("checkout")).click();
        logger.info("Checked out");
    }
    @Test(priority = 7)
    public void clickOnContinue() throws InterruptedException {
        logger.info("\n");
        webDriver.findElement(By.id("continue")).click();
        String message = webDriver.findElement(By.xpath("//div[@class='error-message-container error']")).getText();
        if (message.equals("Error: First Name is required")) {
            Assert.assertTrue(true);
            logger.info("Error message asserted");
        } else {
            Assert.assertTrue(false);
            logger.info("Error message not asserted");
        }
    }

    @Test(priority = 8)
    public void fillTheFormAndToVerifyCheckOutComplete() throws InterruptedException {
        logger.info("\n");
        Faker faker = new Faker();
        webDriver.findElement(By.id("first-name")).sendKeys(faker.name().firstName());
        webDriver.findElement(By.id("last-name")).sendKeys(faker.name().lastName());
        webDriver.findElement(By.id("postal-code")).sendKeys(faker.address().zipCode());
        WebElement element=webDriver.findElement(By.id("continue"));
        action.moveToElement(element).click().perform();
        WebElement element_finish= webDriver.findElement(By.id("finish"));
        action.moveToElement(element_finish).click().perform();
        String message = webDriver.findElement(By.xpath("//span[@class='title']")).getText();
        if (message.equalsIgnoreCase("Checkout: Complete!")) {
            logger.info("CheckOut Complete");
        } else {
            logger.info("CheckOut InComplete");
        }
        Thread.sleep(2000);
    }

    @Test(priority = 9)
    public void toLogoutAndVerifyLoginPage() throws InterruptedException {
        logger.info("\n");
        webDriver.findElement(By.id("react-burger-menu-btn")).click();
        logger.info("Logging Out of Site");
        Thread.sleep(2000);
        WebElement element=webDriver.findElement(By.id("logout_sidebar_link"));
        action.moveToElement(element).click().perform();
        logger.info("Logged Out");
        List<WebElement> verify = webDriver.findElements(By.xpath("//input[@id='login-button']"));
        if (verify.size() == 1) {
            logger.info("We came to Login page");
        } else {
            logger.info("Error after Logout");
        }
        Thread.sleep(2000);
    }


}

