package com.ba.Task.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//import com.ba.Task.listeners.TestAllureListener;
import com.ba.Task.utils.Constants;
import com.ba.Task.utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


//@Epic("EPIC 101:Design Booking for Expedia application....")
//@Story("US 102: Booking page with different features...")
//@Listeners(TestAllureListener.class)
public class BookingPageTest extends BaseTest{
	
	@BeforeClass
	public void bookingPageSetup() throws Exception {  //only precondition is used for booking page
		book = travelpage.doTravel(prop.getProperty("location"));
		book= travelpage.doTravelDate();
		book= travelpage.Travellers();
		book= travelpage.SearchBtn();
	}
	
	@Description("Booking page selectAmount test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=1)
	public void selectAmount() throws Exception {
	book.checkAmount();		
	}
	
	@Description("Booking page isCheckAmount test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void ischeckaAmountExist() {
		Assert.assertTrue(book.ischeckAmount());
	}
	
	@Description("Booking page isCheckRating test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=3)
	public void ischeckaRatingExist() {
		Assert.assertTrue(book.ischeckRating());
	}
	
	@Description("Booking page selectGuestingRating test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=4)
	public void selectGuestRating() throws Exception {
		book.Ratingreview();
	}
	
	@Description("Booking page isCheckLunch test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=5)
	public void isCheckLunchincluded() {
		Assert.assertTrue(book.isCheckLunchincluded());
	}
	
	@Description("Booking page selectLunch test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=6)
	public void selectLunch() throws Exception{
		book.selectLunch();
	}
	
	@Description("Booking page isCheckDinnerincluded test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=7)
	public void isCheckDinnerincluded() {
		Assert.assertTrue(book.isCheckDinnerincluded());
	}
	
	@Description("Booking page selectDinner test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=8)
	public void selectDinner() throws Exception{
		book.selectDinner();
	}
	
	@Description("Booking page selectDropdown test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=9)
	public void selectDropdown() throws Exception{
		book.selectdropdownList();
	
	}
	
	@Description("Booking page selectCheapestHotel test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=10)
	public void selectHotelList() throws Exception {
		book.selectCheapestHotelList();
	

	}
	
	@Description("Booking page selectCheapestHotelList test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=11)
	public void cheapesthotels() throws Exception {
		String value1=book.CheapestHotel();
		  System.out.println("-------------------------------------");
		   System.out.println("The cheapest hotel name is : "+ value1);
		   System.out.println("-------------------------------------");
		   Assert.assertEquals(value1,Constants.Cheapest_Hotel_Name,Errors.Error_CheapestHotel_Message);
		
	}
	
	@Description("Booking page clickCheapestHotel test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=12)
	public void clickCheapestHotel() throws Exception{
		resultpage=book.clickCheaphotel();
       // resultpage.Reserveroom();
		resultpage.Reserve();
		resultpage.Pay();	
	
		String paymentPageTitle = resultpage.getPaymentPageTitle(); 
		Assert.assertEquals(paymentPageTitle, Constants.Payment_page_Title,Errors.Error_Payment_Message_Title);
	
	}
}
