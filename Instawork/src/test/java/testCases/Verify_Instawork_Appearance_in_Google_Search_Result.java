package testCases;

import org.testng.annotations.Test;

import appModules.GooglePageAction;
import appModules.SearchResultAction;
import pageObjects.BaseClass;
import utility.Constant;
import utility.ExcelUtils;
import utility.Utils;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class Verify_Instawork_Appearance_in_Google_Search_Result extends CustomReports{
	public WebDriver driver;
	//public ThreadLocal<WebDriver> driver;
	private static String sTestCaseName;
	private int iTestCaseRow;
	@Parameters("browser")
	@BeforeClass
	public void setup(String browser)throws Exception{
		System.out.println("Before Setup Started");
		sTestCaseName=Utils.getTestCaseName(this.toString());
		ExcelUtils.setExcelFile(Utils.ReadProperties(Constant.Path_ConfigProperties).getProperty("Path_TestData")
				+ Constant.File_TestData, "Sheet1");
		System.out.println(sTestCaseName);
		iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName, Constant.testCaseName);
		driver=Utils.openBrowser(iTestCaseRow,browser);
		new BaseClass(driver);
		System.out.println(iTestCaseRow);
		driver.get(Constant.URL);
	}
	@Parameters("browser")
	@Test
	public void main1(String browser) throws Exception{
		try{
			GooglePageAction.searchSomethingInGoogle(iTestCaseRow,browser);
			SearchResultAction.verifyInstaworkAppearFirst(iTestCaseRow,browser);
		}catch(Exception e){
			SearchResultAction.printPositionOfInstawork(iTestCaseRow);
			throw e;
		}

	}
	/*@Test
	public void main2() throws Exception{
		try{
			SearchResultAction.printPositionOfInstawork(iTestCaseRow);
		}catch(Exception e){
			throw e;
		}

	}*/
	
	@AfterMethod
	public void tearDown(){
		System.out.println(BaseClass.position);
		driver.quit();
	}

}
