package appModules;

import java.awt.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import pageObjects.BaseClass;
import pageObjects.SearchResult;
import utility.Constant;
import utility.ExcelUtils;
import utility.Utils;


public class SearchResultAction {
	static java.util.List<WebElement> list;
	public static void verifyInstaworkAppearFirst(int iTestCaseRow, String browser) throws Exception{
		try{
			Thread.sleep(5000);
			Utils.waitForElement(SearchResult.listOfURL().get(0));
			String URL=SearchResult.listOfURL().get(0).getText().toString();
			if(URL.equals(ExcelUtils.getCellData(iTestCaseRow, Constant.testURL))){
				System.out.println("Instawork website is at first position");
			}else{
				throw new Exception("Instawork website is not in first position");
			}
		}catch(Exception e){
			printPositionOfInstawork(iTestCaseRow);
			System.out.println(browser);
			System.out.println("Instawork URL is not listed first");
			throw e;
		}
		
	}
	public static void printPositionOfInstawork(int iTestCaseRow) throws Exception{
		try{
			boolean present=false;
			String testURL=ExcelUtils.getCellData(iTestCaseRow, Constant.testURL);
			list=SearchResult.listOfURL();
			int size=list.size();
			for(int i=0;i<size;i++){
				BaseClass.position=BaseClass.position+1;
				if(list.get(i).getText().equals(testURL)){
					System.out.println("On Google Search Page Position for Instawork URL is: " +BaseClass.position);
					present=true;
					return;
				}
			}
			if(present==false){
				Utils.scrollingToPageBottom(SearchResult.lnkNext());
				Utils.jsClick(SearchResult.lnkNext());
				printPositionOfInstawork(iTestCaseRow);
			}
		}catch(Exception e){
			throw e;
		}
	}

}
