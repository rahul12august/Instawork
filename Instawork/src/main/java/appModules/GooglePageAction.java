package appModules;

import org.openqa.selenium.Keys;

import pageObjects.GooglePage;
import utility.Constant;
import utility.ExcelUtils;

public class GooglePageAction {
	public static void searchSomethingInGoogle(int iTestCaseRow,String browser) throws Exception{
		try{
			GooglePage.txtbxGoogleSearch().sendKeys(ExcelUtils.getCellData(iTestCaseRow, Constant.searchKeyword));
			GooglePage.txtbxGoogleSearch().sendKeys(Keys.ENTER);
		}catch(Exception e){
			System.out.println("Searching Failed on Browser: " +browser);
			throw e;
		}
		
		
	}
}
