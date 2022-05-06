package com.ba.Task.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import com.ba.Task.utils.Constants;
import com.ba.Task.utils.ElementUtil;



public class TravelPage {

	private WebDriver driver; 
	private ElementUtil elementUtil;
	
	private By  enterlocation = By.xpath("//button[@type='button'][@aria-label='Going to']");
	private By  enterPlace =By.id("location-field-destination");
	private By  selectDate =By.xpath("//*[@id=\"d1-btn\"]");
	
	private By selectonedate=By.xpath("//h2[text()='May 2022']//following::table[1]//following::button[@aria-label='15 May 2022']");
	private By selectEndDate =By.xpath("//*[@id=\"wizard-hotel-pwa-v2-1\"]/div[1]/div[2]/div/div/div[2]/div/div");
	private By selectsecondDate =By.xpath("//h2[text()='May 2022']//following::table[1]//following::button[@aria-label='19 May 2022']");
	private By selectDonebtn =By.xpath("//button[@type='button'][contains(text(),'Done')]");
	
	

	private By selectTraveller =By.xpath("//button[text()='1 room, 2 travellers']");
	private By incrementperson =By.xpath("//*[@class=\"uitk-cell all-x-padding-one all-t-padding-half all-b-padding-twelve uitk-scroll-vertical\"]//div//div[3]//div//button[2]");
	private By dropdownage =By.id("child-age-input-0-0");
	private By doneBtn =By.xpath("//button[@data-testid=\"guests-done-button\"][text()='Done']");
	private By searchBtn=By.xpath("//button[@data-testid='submit-button'][text()='Search']");
	
	
	
	//constructor
	public TravelPage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(driver);//same driver will be pass
	}
	
	public String getTravelpageTitle() {
		return elementUtil.waitForTitleIs(Constants.Travel_Page_Title, 5);
	}
	
	public BookingPage doTravel(String place)  {
		elementUtil.doClick(enterlocation);
		elementUtil.doSendkeys(enterPlace, place);
		elementUtil.doSendkey(enterPlace);
		return new BookingPage(driver); 
		
	}
	public BookingPage doTravelDate() throws InterruptedException  {
		elementUtil.doClick(selectDate);
		Thread.sleep(1000);
		elementUtil.doClick(selectonedate);
		elementUtil.doClick(selectDonebtn);
		Thread.sleep(1000);
		elementUtil.doClick(selectEndDate);
		Thread.sleep(1000);
		elementUtil.doClick(selectsecondDate);
		elementUtil.doClick(selectDonebtn);	
		return new BookingPage(driver);
		
	}
	public BookingPage Travellers() {
		elementUtil.doClick(selectTraveller);
		elementUtil.doClick(incrementperson);
		elementUtil.doClick(dropdownage);
		Select child =new Select(elementUtil.getElement(dropdownage));
		child.selectByIndex(6);
		elementUtil.doClick(doneBtn);
		return new BookingPage(driver);
	}
	public String Searchhotel() {
		WebElement l=elementUtil.getElement(searchBtn);
		String value1=l.getText();
		return value1;
	}
public BookingPage SearchBtn()   { 
			elementUtil.doClick(searchBtn);
			try {
				Thread.sleep(50000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return new BookingPage(driver);
	}
	
}
