package pageObjects;

import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage{
	
	public WebDriver driver;
	public WebDriverWait mywait;
	public JavascriptExecutor js;
	public Properties p;
	public String browserName;
	public Capabilities cap;
	public String filepath="C:\\Users\\2318702\\eclipse-workspace\\Main_Project_HiFive\\Main_Project_HiFive\\testDataChrome\\DataChrome.xlsx";
	public String filepath2="C:\\Users\\2318702\\eclipse-workspace\\Main_Project_HiFive\\Main_Project_HiFive\\testDataEdge\\DataEdge.xls";
	
	
	public BasePage(WebDriver driver)
	{
		this.driver=driver; 
		mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
		js=(JavascriptExecutor) driver;
		PageFactory.initElements(driver,this);     // Initializing Page Factory Model
		 
		 cap = ((RemoteWebDriver) driver).getCapabilities();  //To get the Current Browser details 
		 browserName= cap.getBrowserName().toLowerCase();
		
		
		FileReader file;
		try {
			file = new FileReader(".//src//test//resources//Prop.properties"); //Import values from Property File
			p=new Properties();
			p.load(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
