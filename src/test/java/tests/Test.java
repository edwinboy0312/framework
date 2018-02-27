package tests;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.*;
import utilities.*;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.HashMap;
import java.util.Map;


public class Test {

    WebDriver driver;
    ExtentReports report;
    ExtentTest test;
    Map<String, String> testdata = new HashMap<String,String>();
    static int number =1;
    
    
    @BeforeSuite
    void initReport(){
        report = new ExtentReports("./reports/ExecutionReport.html", true, DisplayOrder.OLDEST_FIRST);
        testdata = ExcelUtility.loadData();
        
    }

    @BeforeTest
    public void init(){
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
        driver = new ChromeDriver();
        test = report.startTest("test case " + number++);
    }
   
    @org.testng.annotations.Test
    public void test1() throws InterruptedException {
        driver.get(testdata.get("Url"));
        Thread.sleep(3000);
        test.log(LogStatus.PASS, "Launch app", testdata.get("Url"));
    }

   
    @AfterMethod
    
    public void tear(){
        report.endTest(test);
        driver.quit();
    }

    @AfterSuite
    public void publish(){
        report.flush();
        report.close();
    }



    public boolean waitForElement(By by) {
        boolean isElementPresent = false;
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(30, SECONDS)
                    .pollingEvery(50, TimeUnit.MILLISECONDS)
                    .ignoring(WebDriverException.class, java.util.NoSuchElementException.class);
            wait.until(driver -> driver.findElement(by));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
