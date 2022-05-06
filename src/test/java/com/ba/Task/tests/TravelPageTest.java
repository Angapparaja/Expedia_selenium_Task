package com.ba.Task.tests;

import org.testng.Assert;
import org.testng.annotations.Test;


import com.ba.Task.pages.BookingPage;
import com.ba.Task.utils.Constants;
import com.ba.Task.utils.Errors;



public class TravelPageTest extends BaseTest{
	@Test(priority = 1)
	public void TravelpageTitleTests() { 
		
		String travelTitle = travelpage.getTravelpageTitle(); 
	    System.out.println("the travelpageTile"+travelTitle);	
	    
	 
	    Assert.assertEquals(travelTitle,Constants.Travel_Page_Title,Errors.Error_Title_Message);  
	}
	

	@Test(priority = 1)
	public void TravlePageTest() throws Exception {
		
		BookingPage book=travelpage.doTravel(prop.getProperty("location").trim());
		
		
	
	}
	
	
	
	@Test(priority = 2)
	public void SelectFirstDate() throws Exception {
		BookingPage book=travelpage.doTravelDate();
	
		
	}
	

	
	@Test(priority = 3)
	public void selectTraveller() throws Exception  {
		BookingPage book=travelpage.Travellers();
		
	}
	

	
	@Test(priority = 4)
	public void searchbtn() {
		String search=travelpage.Searchhotel();
		System.out.println("the button name was :"+search);
		Assert.assertEquals(search,Constants.Search_btn,Errors.Error_Title_Message);
	}
	
	@Test(priority = 5)
	public void searchClick() throws Exception {
		BookingPage book=travelpage.SearchBtn();
		
	
	}	
}
