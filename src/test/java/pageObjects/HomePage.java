package pageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.ExcelUtils;

public class HomePage extends BasePage{

	public WebDriver driver;
	public int matchedtooltips=0;
	public ArrayList<String> list_headers=new ArrayList<String>();
	public ArrayList<String> list_tooltip=new ArrayList<String>();
	
	
	public HomePage(WebDriver driver)
	{
		super(driver);
		
	}

By helpbutton=By.xpath("//*[@id='O365_MainLink_Help']");  //Locating Help button (wait method)

By usernameb=By.xpath("//*[@id='mectrl_currentAccount_primary']"); //Locating username

By aroundcogb=By.xpath("//*[@class='fontSizeXLarge']/strong");     //Locating Around Cognizant 
 
By headers1=By.cssSelector("a[style='-webkit-line-clamp: 2;']");   //Locating Headers

@FindBy(xpath="//*[@id=\"O365_MainLink_Me\"]")
WebElement userinfo;

@FindBy(xpath="//*[@id='mectrl_currentAccount_primary']") 
WebElement username;

@FindBy(xpath="//*[@id='mectrl_currentAccount_secondary']") 
WebElement emailid;

@FindBy(xpath="//span[@class='fontSizeXLarge']/strong") 
WebElement aroundcog;

@FindBy(css="a[style='-webkit-line-clamp: 2;']") 
List<WebElement> headers;

@FindBy(xpath="//*[@class=\"fontSizeMedium\"]/strong")
WebElement seeAll;

	public void userInfo() throws InterruptedException, IOException{
		
		mywait.until(ExpectedConditions.elementToBeClickable(helpbutton));  //wait till help button displayed
		Thread.sleep(5000);
		userinfo.click();
		mywait.until(ExpectedConditions.visibilityOfElementLocated(usernameb));  //wait till user name displayed
		String Username=username.getText();
		String EmailId=emailid.getText();
		
		if(browserName.equals("chrome")){  //Store data into respective Excel sheet			
			ExcelUtils.setCellData(filepath, "Sheet1",8,5,Username);
			ExcelUtils.setCellData(filepath, "Sheet1",9,5,EmailId);
			
		}
		if(browserName.equals("microsoftedge")){
			
			ExcelUtils.setCellDataXls(filepath2, "Sheet1",8,5,Username);
			ExcelUtils.setCellDataXls(filepath2, "Sheet1",9,5,EmailId);
			
			
		}
		
	}
 
	public boolean aroundCognizant() throws InterruptedException, IOException{
		
		Thread.sleep(5000);
		mywait.until(ExpectedConditions.visibilityOfElementLocated(aroundcogb)); //Verification of Around Cognizant is Displayed
		boolean Result=aroundcog.isDisplayed();
		if(Result==true) {
			if(browserName.equals("chrome")) {
				
				ExcelUtils.setCellData(filepath, "Sheet1",10,5,"Present");   //Store data into respective Excel sheet			
				
				
			}
			if(browserName.equals("microsoftedge")) {
				
				ExcelUtils.setCellDataXls(filepath2, "Sheet1",10,5,"Present");
				
				
			} 
			
		}
		else {										//Around Cognizant not found 		
			
			if(browserName.equals("chrome")) {
				
				ExcelUtils.setCellData(filepath, "Sheet1",10,5,"Not Present");
				
				
			}
			if(browserName.equals("microsoftedge")) {
				
				ExcelUtils.setCellDataXls(filepath2, "Sheet1",10,5,"Not Present");
				
				
			}
			
		}
		
		js.executeScript("arguments[0].scrollIntoView();",aroundcog);  // Scroll down till around cognizant text
		Thread.sleep(5000);
		int row=1;
		
		for(WebElement heading:headers){    //Store headers and tooltips in respective Excel sheet			
			
			if(browserName.equals("chrome")) {
				
				ExcelUtils.setCellData(filepath, "Sheet2",row,0,heading.getText());
				ExcelUtils.setCellData(filepath, "Sheet2",row,1,heading.getAttribute("title"));
				
				
			}
			if(browserName.equals("microsoftedge")) {
				
				ExcelUtils.setCellDataXls(filepath2, "Sheet2",row,0,heading.getText());
				ExcelUtils.setCellDataXls(filepath2, "Sheet2",row,1,heading.getAttribute("title"));
				
			}
			list_headers.add(heading.getText());			//Store to List
			list_tooltip.add(heading.getAttribute("title"));
			row++;
			
			
		}
		return Result;
	}
	
	
	public boolean headersTooltipComparep1() throws IOException{
		
		for(int i=0;i<list_tooltip.size();i++){				//Compare the Headers with tooltip
			String tooltip=list_tooltip.get(i);
			if(list_headers.contains(tooltip)){
				matchedtooltips++;
			}
			
		}
		if(matchedtooltips>0){
														//(count)+ headers matches with the toolTip
			if(browserName.equals("chrome")) {
				
				ExcelUtils.setCellData(filepath,"Sheet1",11,5,matchedtooltips+" headers matches with the tooltips");
				
				
			}
			if(browserName.equals("microsoftedge")) {
				
				ExcelUtils.setCellDataXls(filepath2,"Sheet1",11,5,matchedtooltips+" headers matches with the tooltips");
				
				
			}
			return true;
			
		}
		else {
			
			return false;
		}
	}
	
	public void clickSeeAll(){
		
		mywait.until(ExpectedConditions.elementToBeClickable(seeAll));   //wait till Clickable of 'See All'
		seeAll.click();
	}

}
