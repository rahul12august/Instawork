package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

import pageObjects.BaseClass;
public class Utils {
	//public static WebDriver driver = null;
	public static ThreadLocal<WebDriver> driver =  new ThreadLocal<WebDriver>();
	public static WebElement elem = null;

	static String StaticPageName = null;
	public static boolean presence=false;

	public static WebDriver openBrowser(int iTestCaseRow, String browser) throws Exception {
		try {
			//sBrowserName = ExcelUtils.getCellData(iTestCaseRow, Constant.browser);
				if (browser.equals("Mozilla")) {
					System.setProperty("webdriver.gecko.driver",
							Utils.ReadProperties(Constant.Path_ConfigProperties).getProperty("Path_Source")
									+ "//geckodriver.exe");
					//driver = new FirefoxDriver();
					driver.set(new FirefoxDriver());
				} else if (browser.equals("Chrome")) {
					System.setProperty("webdriver.chrome.driver",
							Utils.ReadProperties(Constant.Path_ConfigProperties).getProperty("Path_Source")
									+ "//chromedriver.exe");
					//driver = new ChromeDriver();
					driver.set(new ChromeDriver());
				} else if (browser.equals("IE")) {
					DesiredCapabilities capabilities = new DesiredCapabilities();
					capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					System.setProperty("webdriver.ie.driver",
							Utils.ReadProperties(Constant.Path_ConfigProperties).getProperty("Path_Source")
									+ "//IEDriverServer.exe");
					/*driver = new InternetExplorerDriver(capabilities);
					Utils.waitForLoad(browser);*/
				} 
				/*//getdriver().manage().window().maximize();	
				driver.get(Constant.URL);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);*/
		} catch (Exception e) {
			throw e;
		}
		return driver.get();
	}
	public static void jsClick(WebElement element){
		try{
			JavascriptExecutor executor = (JavascriptExecutor)BaseClass.getDriver();
			executor.executeScript("arguments[0].click();", element);
		}catch(Exception e){
			System.out.println("Unable to click element " +element);
		}
		
	}
	public static String getTestCaseName(String sTestCase) throws Exception {
		String value = sTestCase;
		try {
			int posi = value.indexOf("@");
			value = value.substring(0, posi);
			posi = value.lastIndexOf(".");
			value = value.substring(posi + 1);
			return value;
		} catch (Exception e) {
			throw (e);
		}
	}
	public static void waitForElement(WebElement element) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(BaseClass.getDriver(), 40);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			throw e;
		}

	}
	public static String getApplicationName(String sTestCase) throws Exception {
		String value = sTestCase;
		try {
			int posi = value.indexOf("@");
			value = value.substring(0, posi);
			posi = value.lastIndexOf(".");
			value = value.substring(posi + 1);
			posi = value.lastIndexOf("_");
			value = value.substring(0, posi);
			return value;
		} catch (Exception e) {
			throw (e);
		}
	}

	public static Properties ReadProperties(String path) throws Exception {

		Properties config = new Properties();
		try {

			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\" + path);
			config.load(fis);

		} catch (IOException e){
			throw e;
		}

		return config;

	}

	

	public static void captureScreenshot(String sTestCaseName, String snapshotMessage)
			throws Exception {

		try {
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(
						System.getProperty("user.dir") +"\\Projectscreenshot\\" + sTestCaseName + "_" + snapshotMessage + ".jpg"));

		} catch (Exception e) {
			throw e;
		}
	}

	public static void setSnapshotFolder()throws Exception {
		try{
		String path = Utils.ReadProperties(Constant.Path_ConfigProperties).getProperty("Path_ScreenShot")
				+ "Test Results\\Run_" + Utils.getTimeStamp();
		BaseClass.snapshotFolderPath = path;
		}catch(Exception e){
			throw e;
		}

	}

	public static String getTimeStamp() {
		Date d = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String currentDate = sdf.format(d);
		return currentDate;
	}
	public static void takeScreenshot(WebDriver webdriver, String fileWithPath) throws Exception {
		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		// Move image file to new destination
		File DestFile = new File(fileWithPath);
		// Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);

	}

	public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		// Move image file to new destination
		File DestFile = new File(BaseClass.snapshotFolderPath + "\\Fail");
		// Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);

	}
	public static void scrollingToPageBottom(WebElement element) throws InterruptedException {
		((JavascriptExecutor) BaseClass.getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight || document.documentElement.scrollHeight)", element);
		Thread.sleep(5000);
	}

	
	}
