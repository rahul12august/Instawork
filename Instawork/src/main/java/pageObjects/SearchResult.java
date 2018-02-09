package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResult extends BaseClass{
	private static WebElement element; 
	private static List <WebElement> elements;
	public SearchResult(WebDriver driver) {
		super(driver);
	}
	public static List<WebElement> lnkSearchResult(){
		try{
			elements=getDriver().findElements(By.xpath("//*[@class='r']"));
		}catch(Exception e){
			System.out.println("Unable to find Search Results Headers || Method lnkSearchResult");
			throw e;
		}
		return elements;
	}
	
	public static List<WebElement> listOfURL(){
		try{
			elements=getDriver().findElements(By.xpath("//cite"));
		}catch(Exception e){
			System.out.println("Unable to find exact match of Instawork URL || Method urlInstawork");
			throw e;
		}
		return elements;
	}
	public static WebElement lnkNext(){
		try{
			element=getDriver().findElement(By.xpath("//span[contains(text(),'Next')]"));
		}catch(Exception e){
			System.out.println("Unable to find exact match of Instawork URL || Method urlInstawork");
			throw e;
		}
		return element;
	}
}
