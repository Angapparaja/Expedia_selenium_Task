package com.ba.Task.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ba.Task.factory.DriverFactory;



public class ElementUtil {
	private WebDriver driver; //static aa driver la use panna parellel execution la problem occur aagum
	//because all the parallel execution having same driver but static use panna athu CMA la store aagum
	//all the object having a same driver in parellel execution static la one thread panna after another thread la use panna mudiyathu so static method not satisfy the parallel execution
	
	private JavaScriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver) { //constructor 
		this.driver=driver; //this keyword represent the WebDriver
		jsUtil = new JavaScriptUtil(driver);
	}
	
	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight.trim())) {  //parseBoolean is convert the String to Boolean
			jsUtil.flash(element);
		}
		return element;
	}
//	public WebElement getElement(By locator) {//entha method private aa erutha bottom la erukura method la use panna mudiyathu
//		return driver.findElement(locator);
//	}
	
	public WebElement getElemented(WebElement locator) {//entha method private aa erutha bottom la erukura method la use panna mudiyathu
		return driver.findElement((By) locator);
	}
	public  List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	public void doSendkeys(By locator,String value) {
		WebElement ele= getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}
	public void doSendkey(By locator) {
		getElement(locator).sendKeys(Keys.ENTER);
	}
	
	public  String doGettext(By locator) {
		return getElement(locator).getText(); //locator are passing  getelements method and driver findelements are find the locator and webelement locator are using gettext();
	}//it return the string value so change the replace of void to string
	
	public  void doClick(By locator) {
		getElement(locator).click();
	}
	public  String doGetAttribute(By locator,String attrname) { //string attrname la passing attributename
		return getElement(locator).getAttribute(attrname);
		}
	public boolean doDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	public boolean isSelected(By locator) {
		return getElement(locator).isSelected();
	}
	public boolean doEnable(By locator) {
		return getElement(locator).isEnabled();
	}
	public void clickOnElement(By locator,String value) {
		List<WebElement>eleList=getElements(locator);
		System.out.println(eleList.size());
		for(WebElement e:eleList) {
			if(e.getText().equals(value)) {
				e.click();
				break;
			}
		}
	}
	
	public  List<String> getLinksTextList(By locator) {
		//this list empty list
		List<String> eleTextList =new ArrayList<String>();//it is using topcasting -->child class object is referred by parent interface refvariable
		List<WebElement> elelist= getElements(locator);
		System.out.println("element count :"+elelist.size());
		
		for(WebElement e:elelist) {
			String text =e.getText();
			if(!text.isEmpty()) {
				eleTextList.add(text);
			}
		}
		return eleTextList;
	}

	
	public  boolean isElementsExits(By locator) {
		List<WebElement>listEle =getElements(locator);//one locator aa pass panni findelements use panna anga conform List<WebElement> use pannanum
		if(listEle.size()>0) {
			System.out.println("elements is present");
			return true;
		}
		
		System.out.println("element is not present");
		return false;
	}
	
	public void getDropdownOptionsList(By locator, int value) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		System.out.println(optionsList.size());

		for (WebElement e : optionsList) {
			String text = e.getText();
			System.out.println(text);
			if (value == 5) {
				e.click();
				break;
			}
		}

	}
	
//	public  void getDropdownOptionsList(By locator, int value) {
//		Select select = new Select(getElement(locator));
//		List<WebElement> optionsList = select.getOptions();
//		System.out.println(optionsList.size());
//
//		for (WebElement e : optionsList) {
//			String text = e.getText();
//			System.out.println(text);
//			if (value == 5) {
//				e.click();
//				break;
//			}
//		}
//
//	}
	public String waitForTitleContains(String titleValue,int timeOut) {
		WebDriverWait wait =new WebDriverWait(driver,timeOut);
		if(wait.until(ExpectedConditions.titleContains(titleValue))) {
			return driver.getTitle();
		}
		return null;
	}
	
	public String waitForTitleIs(String fullTitle,int timeOut) { 
		WebDriverWait wait =new WebDriverWait(driver,timeOut); 
		if(wait.until(ExpectedConditions.titleIs(fullTitle))) { //entire full title is visible so return the page title
			return driver.getTitle();	
		}
		return null;
	}

	public String waitForUrlContains(String urlFraction,int timeOut) {
		WebDriverWait wait =new WebDriverWait(driver,timeOut);
		if(wait.until(ExpectedConditions.urlContains(urlFraction))) {
			return driver.getCurrentUrl();
		}
		return null;
	}
	
	public String waitForFullUrl(String urlValue,int timeOut) {
		WebDriverWait wait =new WebDriverWait(driver,timeOut);
		if(wait.until(ExpectedConditions.urlToBe(urlValue))) {
			return driver.getCurrentUrl();
		}
		return null;
	}
}
