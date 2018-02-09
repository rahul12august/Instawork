package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GooglePage extends BaseClass{
	private static WebElement element; 
	public GooglePage(WebDriver driver) {
		super(driver);
	}
	public static WebElement txtbxGoogleSearch(){
		try{
			element=getDriver().findElement(By.xpath(".//*[@title='Search']"));
		}catch(Exception e){
			System.out.println("Unable to find Google Search TextBox|| Method txtbxGoogleSearch");
			throw e;
		}
		return element;
	}
	
}
