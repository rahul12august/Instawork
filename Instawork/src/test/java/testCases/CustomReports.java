package testCases;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CustomReports {
	protected static ExtentReports extent;
	 ExtentTest logger;
	
	
	static{
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat formater=new SimpleDateFormat("dd_mm_yyyy_hh_mm_ss");
		System.out.println(System.getProperty("user.dir"));
		extent=new ExtentReports(System.getProperty("user.dir")+"/src/test/java/extentReport/test" + formater.format(calendar.getTime())+".html",true );
	}
	
 
  @BeforeMethod
  public void beforeMethod(Method result) throws Exception {
	  logger=extent.startTest(result.getName());
	  logger.log(LogStatus.INFO, result.getName() + "Test Started");
	  
  }

  @AfterMethod
  public void afterMethod(ITestResult result) {
	  getresult(result);
  }

 
  
  public void getresult(ITestResult result){
	  if(result.getStatus()== ITestResult.SKIP){
		  logger.log(LogStatus.SKIP, result.getName()+ "The Test was Skipped and the reason is:" +result.getThrowable());
	  }
	  else if(result.getStatus()== ITestResult.SUCCESS){
		  logger.log(LogStatus.PASS, result.getName()+ "The Test was passed");
	  }
	  else if(result.getStatus()== ITestResult.FAILURE){
		  logger.log(LogStatus.FAIL, result.getName()+ "The Test was failed" + result.getThrowable());
	  }
  }
  @AfterClass(alwaysRun = true)
  public void endTest(){
	  extent.endTest(logger);
	  extent.flush();
  }
}
