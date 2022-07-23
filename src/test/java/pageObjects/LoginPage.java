package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage{
    WebDriver webDriver;

    public LoginPage(WebDriver webDriver){
        this.webDriver=webDriver;
        PageFactory.initElements(webDriver,this);
    }
    @FindBy(name = "user-name")
    @CacheLookup
    WebElement user;

    @FindBy(name = "password")
    @CacheLookup
    WebElement password;

    @FindBy(name = "login-button")
    @CacheLookup
    WebElement login;

    public void setUser(String userP) {
        user.sendKeys(userP);

    }

    public void setPassword(String passwordP) {
        password.sendKeys(passwordP);
    }

    public void clickSubmit(){
        login.click();
    }
}
