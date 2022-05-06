package com.ba.Task.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.ba.Task.utils.Constants;
import com.ba.Task.utils.ElementUtil;



public class ResultPage {
	private WebDriver driver;
	private ElementUtil elementUtil;
		
	//private By Reserveroom=By.xpath("//button[contains(text(),'Reserve a room')]");
	private By Reserveroom=By.xpath("//*[@id=\"app-layer-base\"]/div/main/div/div/div/section/div[1]/div[2]/div[1]/div[2]/section/div/div/div[2]/div/div/button");
	private By Reserve =By.xpath("//div[@class='uitk-layout-flex uitk-layout-flex-flex-direction-column uitk-layout-flex-item-align-self-flex-end uitk-layout-flex-item uitk-layout-flex-item-flex-basis-third_width uitk-type-right']//button[1]");
	//private By pay =By.xpath("//form/following::button[4]");
	//private By paybtn=By.xpath("(//button[@data-stid='submit-hotel-reserve'])[4]");

	private By payment=By.xpath("(//button/span[text()='Pay now'])[1]");
		public ResultPage(WebDriver driver) {
			this.driver=driver;
			elementUtil=new ElementUtil(driver);
		}

		public void Reserveroom() throws Exception {
			
			Boolean doDisplay=elementUtil.doDisplayed(Reserveroom);
			if(doDisplay == true) {
				System.out.println(driver.getCurrentUrl()); 
				System.out.println("sucess");
				elementUtil.doClick(Reserveroom);
			}
			Thread.sleep(2000);	
		}
		
		public void Reserve() throws Exception{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1350)", "");
			Boolean doDisplay =elementUtil.doDisplayed(Reserve);
			if(doDisplay == true) {
				elementUtil.doClick(Reserve);
			}
			Thread.sleep(3000);	
		}
		
		public void Pay() throws Exception{
			Boolean doDisplay = elementUtil.doDisplayed(payment);
			if(doDisplay == true) {
			System.out.println("displayed");
				Thread.sleep(1000);
				elementUtil.doClick(payment);
				System.out.println("------------------------------------------------");
				System.out.println("the current payment page Title is:"+driver.getTitle());
				System.out.println("------------------------------------------------"); 
			}
			
		}
		
		public String getPaymentPageTitle() {
			return elementUtil.waitForTitleIs(Constants.Payment_page_Title, 5);
		}
	
}
