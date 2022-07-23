package testCases;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utilities.ReadConfig;

public class BaseClass {

    ReadConfig readConfig = new ReadConfig();
    public String baseURL = readConfig.getApplicationURL();
    public String user = readConfig.getUser();
    public String password = readConfig.getPassword();
    public static WebDriver webDriver;
    public static Logger logger;

    @Parameters("browser")
    @BeforeClass
    public void setup(@Optional("chrome") String br) {
        if (br.equals("chrome")) {
            String driverPath="/home/suraj/Downloads/selenium/chromedriver_linux64/chromedriver";
            System.setProperty("webdriver.chrome.driver", driverPath);
            webDriver = new ChromeDriver();
            webDriver.manage().window().maximize();
        }
        webDriver.get(baseURL);

    }

    @AfterClass
    public void tearDown() {
        webDriver.quit();
    }
}
