package com.rachid.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.rachid.utilities.ExcelReader;
import com.rachid.utilities.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Properties config = new Properties();;
	public static Properties OR = new Properties();;
	public static FileInputStream fis;
	public static ExtentReports rep=ExtentManager.getInstance();
	public static ExtentTest test;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel=new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\testdata.xlsx");

	@BeforeSuite
	public static void setUp() {

		if (driver == null) {

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				config.load(fis);
				 log.debug("config file Loaded");
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				OR.load(fis);
			 log.debug("OR file Loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (config.getProperty("browser").equals("chrome")) {
				System.getProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				 log.debug("Launched chrome browser");
			} else if (config.getProperty("browser").equals("ie")) {
				System.getProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\ie.exe");
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();

				 log.debug("Launched ie browser");
			}

			driver.get(config.getProperty("testsiteurl"));
			 log.debug("Navigated to the url");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit_wait")),
					TimeUnit.SECONDS);
			if (driver.findElement(By.xpath(OR.getProperty("popup_XPATH"))).isDisplayed()) {
				driver.findElement(By.xpath(OR.getProperty("popup_XPATH"))).click();
			}

			wait = new WebDriverWait(driver, 5);
		}

	}

	@AfterSuite
	public static void tearDown() {
		if (!(driver == null)) {

			driver.quit();
		}
		log.debug("test execution completed");

	}

	// Method to click on elements
	public static void click(String locator) {

		if (locator.endsWith("CSS")) {

			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("XPATH")) {

			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("ID")) {

			driver.findElement(By.id(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_PRLINK")) {

			driver.findElement(By.partialLinkText(OR.getProperty(locator))).click();
		}
		
		test.log(LogStatus.INFO, "Clicking on : "+ locator);

	}

	// Method to click on links & open them in new tabs
	public static void clickOnLinks(String locator) {

		if (locator.endsWith("CSS")) {

			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
		} else if (locator.endsWith("XPATH")) {

			driver.findElement

			(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("ID")) {

			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
		} else if (locator.endsWith("LINKTXT")) {

			driver.findElement(By.linkText(OR.getProperty(locator))).sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
		}
		test.log(LogStatus.INFO,"Clicking on link: "+locator);

	}

	// Method to send keys
	public static void type(String locator, String value) {

		if (locator.endsWith("CSS")) {

			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("XPATH")) {

			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("ID")) {

			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("LINKTXT")) {

			driver.findElement(By.linkText(OR.getProperty(locator))).sendKeys(value);
		}
		
		test.log(LogStatus.INFO,"Typing : "+locator+" and entering value as: "+value);

	}

	// dropdown
	static WebElement dropdown;

	public static void select(String locator, String value) {

		if (locator.endsWith("CSS")) {

			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("XPATH")) {

			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("ID")) {

			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		} else if (locator.endsWith("LINKTXT")) {

			dropdown = driver.findElement(By.linkText(OR.getProperty(locator)));

			Select select = new Select(dropdown);
			select.selectByVisibleText(value);
		
		}
		
		test.log(LogStatus.INFO,"Selecting : "+locator+" entering value as: "+value);

	}

	// return Webelement
	public static List<WebElement> element1;

	public List<WebElement> element(String locator) {

		if (locator.endsWith("CSS")) {
			element1 = driver.findElements(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("XPATH")) {

			element1=driver.findElements(By.xpath(OR.getProperty(locator)));
			
		} else if (locator.endsWith("ID")) {

			element1=driver.findElements(By.id(OR.getProperty(locator)));
		} else if (locator.endsWith("LINKTXT")) {

			element1=driver.findElements(By.linkText(OR.getProperty(locator)));

	}
		return element1;
	}

	// Get links count
	public static void elementTarget(String locator) {

		if (locator.endsWith("CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("XPATH")) {

			driver.findElement

			(By.xpath(OR.getProperty(locator))).sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
			;
		} else if (locator.endsWith("ID")) {

			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
		} else if (locator.endsWith("LINKTXT")) {

			driver.findElement(By.linkText(OR.getProperty(locator))).sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
		}

	}

	public WebElement element;

	public int linksCount(String locator) {

		int size = 0;

		if (locator.endsWith("CSS")) {
			element = driver.findElement(By.cssSelector(OR.getProperty(locator)));

			size = element.findElements(By.tagName("a")).size();

		} else if (locator.endsWith("XPATH")) {

			element = driver.findElement(By.xpath(OR.getProperty(locator)));

			size = element.findElements(By.tagName("a")).size();

		} else if (locator.endsWith("ID")) {

			element = driver.findElement(By.id(OR.getProperty(locator)));

			size = element.findElements(By.tagName("a")).size();

		}
		return size;
	}

	// check if element is present
	public boolean isElementPresent(By by) {
		try {

			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;

		}

	}
}
