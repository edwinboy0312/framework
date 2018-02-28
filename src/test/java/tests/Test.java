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
	}

	@org.testng.annotations.Test
	public void test1() throws InterruptedException {
		test = report.startTest("test case 1");
		driver.get("http://ec2-54-174-213-136.compute-1.amazonaws.com:3000/");
		Thread.sleep(3000);
		test.log(LogStatus.PASS, "Launch app", "test");
	}


	@org.testng.annotations.Test
	public void test2() throws InterruptedException {
		test = report.startTest("Get for Comments");
		HashMap <String,String> map = new HashMap<String,String>();
		map.put("Content-Type" , "application/json");
		String response = ApiUtilities.getEntity("http://ec2-54-174-213-136.compute-1.amazonaws.com:3000/comments/1", map);
		test.log(LogStatus.PASS, "Response : ", response);
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
