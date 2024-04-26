package pageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.ExcelUtils;

public class NewsPage extends BasePage{

	public WebDriver driver;
	public int headerscount=0;
	public int tooltipcount=0;
	public int tooltipFivecount=0;
	public LinkedHashSet<String> headerslist = new LinkedHashSet<String>(); 
    public LinkedHashSet<String> tooltipslist = new LinkedHashSet<String>();
    public LinkedHashSet<String> contentslist = new LinkedHashSet<String>();
	
	public NewsPage(WebDriver driver){
		super(driver);
	}
	
	@FindBy(xpath="//*[@data-automation-id='newsItemTitle']")List<WebElement> headers;     //Identifying List of all Headers
	@FindBy(xpath="//*[@data-automation-id='newsItemDescription']") List<WebElement> contents;   //Identifying List of all Contents
	
	@FindBy(xpath="//*[@data-automation-id='newsItemTitle']")List<WebElement> headersFive;     //Identifying List of all Headers
	@FindBy(xpath="//*[@data-automation-id='newsItemDescription']") List<WebElement> contentsFive;   //Identifying List of all Contents
	
	public void newsHeaders(WebDriver driver) throws InterruptedException{
		Thread.sleep(5000);
		boolean flag=true;
        while((headers.size()>0 && flag==true)){					//Collection of headers and tooltip till end of the page
        	WebElement seventh=headers.get((headers.size())-1);
    		for(int i=0;i<headers.size();i++){
        		
        		headerslist.add(headers.get(i).getText());
        		tooltipslist.add(headers.get(i).getAttribute("title"));
        		contentslist.add(contents.get(i).getText());
            }
    		js.executeScript("arguments[0].scrollIntoView();",seventh);      //Scroll till the last element visiblity
            Thread.sleep(5000);
            if(seventh.getAttribute("title").equals(p.getProperty("LastNews"))){       //close loop if we find last news
        		
        		flag=false;
        		continue;
        	}
            headers = driver.findElements(By.xpath("//*[@data-automation-id='newsItemTitle']"));
            contents = driver.findElements(By.xpath("//*[@data-automation-id='newsItemDescription']")); 
        }
	}
	
	
	
	public boolean headersCompare(ArrayList<String> firstPageHeader) throws IOException{
		
		for(int i=0;i<firstPageHeader.size();i++) {				//Compare First page Headers and second page headers
			
			if(headerslist.contains(firstPageHeader.get(i))){
				headerscount++;
			}
		}
		if(headerscount>0) {
			//(count)+ headers present in the second page
			
			if(browserName.equals("chrome")) {
				
				ExcelUtils.setCellData(filepath,"Sheet1",12,5,headerscount+" first page headers matches with the SecondPage headers");
				
				
			}
			if(browserName.equals("microsoftedge")) {
				
				ExcelUtils.setCellDataXls(filepath2,"Sheet1",12,5,headerscount+" first page headers matches with the SecondPage headers");
				
				
			}
			
			return true;
		}
		else {
			
			return false;
		}
	}
	
	public boolean headersTooltipComparep2() throws IOException{
		
		List<String> temp_headers=new ArrayList<String>(headerslist);
		List<String> temp_tooltip=new ArrayList<String>(tooltipslist);
		for(int i=0;i<temp_headers.size();i++){					//Compare Second page Headers and Tooltips
			
			if((temp_headers.get(i)).equals(temp_tooltip.get(i))){
				
				tooltipcount++;
			}
			
		}
		if(tooltipcount>0){
			
			if(browserName.equals("chrome")) {
				
				ExcelUtils.setCellData(filepath,"Sheet1",13,5,tooltipcount+" Second page headers matches with the SecondPage tooltips");
				
				
			}
			if(browserName.equals("microsoftedge")) {
				
				ExcelUtils.setCellDataXls(filepath2,"Sheet1",13,5,tooltipcount+" Second page headers matches with the SecondPage tooltips");
				
				
			}
			
			return true;
		}
		else {
			
			return false;
		}
	}
	
	public void newsDetailsPrint() throws IOException{
		int rowh=1;
        for(String h:headerslist) {						//Print the headers List datas to excel
        	
        	
        	if(browserName.equals("chrome")) {
				
        		ExcelUtils.setCellData(filepath, "Sheet3", rowh,0,h);
					
			}
			if(browserName.equals("microsoftedge")) {
				
				ExcelUtils.setCellDataXls(filepath2, "Sheet3", rowh,0,h);
					
			}
        	
        	rowh++;
        }
        int rowt=1;
        for(String t:tooltipslist) {				//Print the toolip List datas to excel
        	
        	if(browserName.equals("chrome")) {
				
        		ExcelUtils.setCellData(filepath, "Sheet3", rowt,1,t);
					
			}
			if(browserName.equals("microsoftedge")) {
				
				ExcelUtils.setCellDataXls(filepath2, "Sheet3", rowt,1,t);
					
			}

        	
        	rowt++;
        }
        int rowc=1; 
        for(String c:contentslist) {					//Print the Content List datas to excel
        	
        	if(browserName.equals("chrome")) {
				
        		ExcelUtils.setCellData(filepath, "Sheet3", rowc,2,c);
					
			}
			if(browserName.equals("emicrosoftedge")) {
				
				ExcelUtils.setCellDataXls(filepath2, "Sheet3", rowc,2,c);
					
			}

        	
        	rowc++;
        }
	}
	
	public boolean headersTooltipComparep2Five() throws IOException{
		
		
		for(int i=0;i<5;i++){	//Compare Second page Headers and Tooltips
			
			String hFive=headersFive.get(i).getText();
			String ttFive=headersFive.get(i).getAttribute("title");
			if(hFive.equals(ttFive)){
				
				tooltipFivecount++;
			}
			
		}
		if(tooltipFivecount>0){
			
			if(browserName.equals("chrome")) {
				
				ExcelUtils.setCellData(filepath,"Sheet1",14,5,tooltipFivecount+" Second page headers matches with the First Five SecondPage tooltips");
				
				
			}
			if(browserName.equals("microsoftedge")) {
				
				ExcelUtils.setCellDataXls(filepath2,"Sheet1",14,5,tooltipFivecount+" Second page headers matches with the First Five SecondPage tooltips");
				
				
			}
			
			return true;
		}
		else {
			
			return false;
		}
	}
	
	public void FivenewsDetailsPrint() throws IOException{
		
		int rowh=1;
        for(int i=0;i<5;i++) {		//Print the headers List datas to excel
        	WebElement h=headersFive.get(i);
        	
        	if(browserName.equals("chrome")) {
				
        		ExcelUtils.setCellData(filepath, "Sheet4", rowh,0,h.getText());
					
			}
			if(browserName.equals("microsoftedge")) {
				
				ExcelUtils.setCellDataXls(filepath2, "Sheet4", rowh,0,h.getText());
					
			}
        	
        	rowh++;
        }
        int rowt=1;
        for(int i=0;i<5;i++) {				//Print the toolip List datas to excel
        	WebElement t=headersFive.get(i);
        	if(browserName.equals("chrome")) {
				
        		ExcelUtils.setCellData(filepath, "Sheet4", rowt,1,t.getAttribute("title"));
					
			}
			if(browserName.equals("microsoftedge")) {
				
				ExcelUtils.setCellDataXls(filepath2, "Sheet4", rowt,1,t.getAttribute("title"));
					
			}

        	
        	rowt++;
        }
        int rowc=1; 
        for(int i=0;i<5;i++) {					//Print the Content List datas to excel
        	
        	WebElement c=contentsFive.get(i);
        	if(browserName.equals("chrome")) {
				
        		ExcelUtils.setCellData(filepath, "Sheet4", rowc,2,c.getText());
					
			}
			if(browserName.equals("microsoftedge")) {
				
				ExcelUtils.setCellDataXls(filepath2, "Sheet4", rowc,2,c.getText());
					
			}

        	
        	rowc++;
        }
		
	}
	
	
	
}
