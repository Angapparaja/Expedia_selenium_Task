package com.ba.Task.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//import com.ba.Task.listeners.TestAllureListener;
import com.ba.Task.pages.BookingPage;
import com.ba.Task.utils.Constants;
import com.ba.Task.utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


//@Epic("EPIC 100:Design Login page for Expedia application....")
//@Story("US 101: Login page with different features...")
//@Listeners(TestAllureListener.class)
public class TravelPageTest extends BaseTest{
	
	@Description("TravelPage title test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void TravelpageTitleTests() { 
		
		String travelTitle = travelpage.getTravelpageTitle(); 
	    System.out.println("the travelpageTile"+travelTitle);	
	    
	 
	    Assert.assertEquals(travelTitle,Constants.Travel_Page_Title,Errors.Error_Title_Message);  
	}
	
	@Description("Expedia location Page test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void TravlePageTest() throws Exception {
		
		BookingPage book=travelpage.doTravel(prop.getProperty("location").trim());
	
	}
	
	
	@Description("Expedia select First date test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void SelectFirstDate() throws Exception {
		BookingPage book=travelpage.doTravelDate();
	
		
	}
	

	@Description("Traveller page test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void selectTraveller() throws Exception  {
		BookingPage book=travelpage.Travellers();
		
	}
	

	@Description("Search button test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void searchbtn() {
		String search=travelpage.Searchhotel();
		System.out.println("the button name was :"+search);
		Assert.assertEquals(search,Constants.Search_btn,Errors.Error_Title_Message);
	}
	
	@Description("Traveller page click search  test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 5)
	public void searchClick() throws Exception {
		BookingPage book=travelpage.SearchBtn();
		
	
	}	
}
