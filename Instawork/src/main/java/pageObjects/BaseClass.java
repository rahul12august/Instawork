package pageObjects;

import org.openqa.selenium.WebDriver;

public class BaseClass {
	//public static WebDriver driver;
	public static boolean bResult;
	public static String errorValidation;
	public static String snapshotFolderPath;
	public static String failedSnapshotFolderPath;
	public static ThreadLocal<WebDriver> driver =  new ThreadLocal<WebDriver>();
	public static int position=0;
	public static WebDriver getDriver() {
		return driver.get();
	}
	public BaseClass(WebDriver driver) {
		BaseClass.driver.set(driver);
		BaseClass.bResult = true;
		BaseClass.errorValidation = "";
		BaseClass.failedSnapshotFolderPath = "";
	}
	
}
