package com.ba.Task.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import com.ba.Task.utils.Constants;
import com.ba.Task.utils.ElementUtil;



public class BookingPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By isCheckAmount=By.xpath("//span[text()='Rs4,000 to Rs8,000']");
	private By isCheckRating=By.xpath("//span[text()='Wonderful 4.5+']");
	private By isCheckLunchincluded=By.xpath("//span[text()='Lunch included']");
	private By isCheckDinnerincluded=By.xpath("//span[text()='Dinner included']");
	private By Checkamount =By.id("price-2-2");
	private By GuestRating =By.id("radio-guestRating-45");
	private By Lunchincluded=By.cssSelector("input#mealPlan-1-HALF_BOARD");
	private By Dinnerincluded =By.id("mealPlan-2-FULL_BOARD");
	private By dropdowndetails =By.xpath("//select[@id='sort']");
	private By ListofHotels =By.xpath("//ol//li/h3[text()]");
	private By cheapestHotels =By.xpath("(//h3[@class='uitk-heading-5 truncate-lines-2 all-b-padding-half pwa-theme--grey-900 uitk-type-heading-500'])[1]");

	private By clickCheapestHotel=By.xpath("//div[@class='uitk-card uitk-card-roundcorner-all uitk-card-has-primary-theme']/a[1]");
	
	public BookingPage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(driver);
	}
	
	public String getBookingpageTitle() {
		return elementUtil.waitForTitleContains(Constants.Booking_Page_Title, 5);
	}
	
	public String getBookingpageUrl() {
		return elementUtil.waitForUrlContains(Constants.Booking_Page_Url_Fraction, 5); 
	}
	
	public Boolean ischeckAmount(){
		return elementUtil.doDisplayed(isCheckAmount);
	}
	
	public void checkAmount() throws Exception {
		Boolean doDisplay=elementUtil.isSelected(Checkamount);
		if(doDisplay == false) {
			Thread.sleep(2000);
			elementUtil.doClick(Checkamount);
		}
		Thread.sleep(5000);	
	}
	
	public Boolean ischeckRating(){
		return elementUtil.doDisplayed(isCheckRating);
	}
	public void Ratingreview() throws Exception {
		Boolean doDisplay=elementUtil.isSelected(GuestRating);
		if(doDisplay == false) {
		elementUtil.doClick(GuestRating);
		}
		Thread.sleep(5000);	
	}
	public Boolean isCheckLunchincluded(){
		return elementUtil.doDisplayed(isCheckLunchincluded);
	}
	public void selectLunch() throws Exception {
		Boolean doDisplay=elementUtil.isSelected(Lunchincluded);
		if(doDisplay == false) {
	elementUtil.doClick(Lunchincluded);
		}
	Thread.sleep(5000);
	}
	public Boolean isCheckDinnerincluded(){
		return elementUtil.doDisplayed(isCheckDinnerincluded);
	}
	public void selectDinner() throws Exception{
		
		Boolean doDisplay=elementUtil.isSelected(Dinnerincluded);
		if(doDisplay == false) {
		elementUtil.doClick(Dinnerincluded);
		}
		Thread.sleep(5000);
	}
	
	public void selectdropdownList() throws Exception {	
	Select dropdown = new Select(elementUtil.getElement(dropdowndetails));
	Boolean doDisplay=elementUtil.doDisplayed(dropdowndetails);
	if(doDisplay == true) {
	 	dropdown.selectByVisibleText("Price");
	}
	Thread.sleep(5000);
	}
	
	public void selectCheapestHotelList() throws Exception{
		List<WebElement> listofhotels =elementUtil.getElements(ListofHotels);
		 for(WebElement lists:listofhotels) {
			 String name = lists.getText();

		     System.out.println("the list of hotel names was :"+name);
		 }
			
		 System.out.println("-------------------------------------");
		 System.out.println("-------------------------------------");
		
	}
	
	
	public String CheapestHotel() {
		
	 WebElement l =elementUtil.getElement(cheapestHotels);
	  String value1 = l.getText();
	
	return value1;
	}
	
	public ResultPage clickCheaphotel() {
		try {
			elementUtil.doClick(clickCheapestHotel);
			Thread.sleep(20000);
			Set<String> s1 = driver.getWindowHandles();
			Iterator itr = s1.iterator();
			String parent_window = (String) itr.next();
			String child_window = (String) itr.next();
			driver.switchTo().window(child_window);
			driver.getCurrentUrl();	
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 return new ResultPage(driver); 
	}
	
}
