package testCases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.Dataprovider;
import pageObjects.HomePage;
import pageObjects.NewsPage;
import testBase.BaseClass;

public class TC_001 extends BaseClass{
	
	
	
	@Test(priority=0,groups= {"regression"})
	public void firstPage(){
		 
		try { 
			captureScreen("1.start");
			logger.info("**** starting TC_001 *****");
			logger.debug("application logs started......");
			logger.info("First Page is Opened");
			
			HomePage hp=new HomePage(driver); 
			logger.info("View User Info Window");
			hp.userInfo();           //To get the User info
			captureScreen("2.user_info");
			logger.info("User Informations are stored in excel");
			
			logger.info("Checking aroundCognizant section");
			boolean result1=hp.aroundCognizant();  //Validates the presence of Around Cognizant
			captureScreen("3.around_cognizant");
			Assert.assertEquals(result1,true);
			
			logger.info("Comparing first page headers with tooltips");
			boolean result2=hp.headersTooltipComparep1();  //Compare the first page headers with its tooltips
			Assert.assertEquals(result2,true);
			
			templist=hp.list_headers;
			
			logger.info("Click SeeAll");
			hp.clickSeeAll();         //Navigate to next page by clicking SeeAll
			captureScreen("4.see_all");
		}
		catch(Exception e) {
			
			logger.error("First Page test failed");
			e.printStackTrace();
			Assert.fail();
			
		}	
	}
	
	@Test(priority=1,groups= {"regression"})
	public void secondPage() throws InterruptedException, IOException {
		
		try {
			logger.info("Second Page is Opened");
			NewsPage np=new NewsPage(driver);
			captureScreen("5.second_page_opened");
			
			logger.info("Reading all the news");
			np.newsHeaders(driver);    //Capturing the Second page headers and tooltips 
			 
			
			logger.info("Comparison of first and second page headers");
			boolean headcompare= np.headersCompare(templist);		      //Comparing first page headers and Second page headers
			Assert.assertEquals(headcompare,true);
			
			logger.info("Comparison of second page headers with tooltips");
			captureScreen("6.Second_Page_End");
			boolean headtipcompare= np.headersTooltipComparep2();		//Comparing Second page headers and tooltips
			Assert.assertEquals(headtipcompare,true);
			
			logger.info("Comparison of second page first five headers with tooltips");
			boolean fiveheaders=np.headersTooltipComparep2Five();           //Comparing First Five headers and tooltips
			Assert.assertEquals(fiveheaders,true);
			
			logger.info("Printing all news details to excel");
			np.newsDetailsPrint();      //Printing all News Details in Excel
			
			logger.info("Printing all First Five news details to excel");
			np.FivenewsDetailsPrint();      //Printing first five News Details in Excel
		}
		catch(Exception e) {
			
			logger.error("Second Page test failed");
			e.printStackTrace();
			Assert.fail();
		}
		
		logger.debug("application logs end.......");
		logger.info("**** finished TC_001 *****");
		
		System.out.println("THE END");
		
	}
	
	
}
