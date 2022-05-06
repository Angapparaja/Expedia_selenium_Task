package com.ba.Task.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;

import com.ba.Task.factory.DriverFactory;
import com.ba.Task.pages.BookingPage;
import com.ba.Task.pages.ResultPage;
import com.ba.Task.pages.TravelPage;



public class BaseTest {
	WebDriver driver;
	DriverFactory df;
	Properties prop;
	TravelPage travelpage;
	BookingPage book;
	ResultPage resultpage;


@BeforeTest
public void setup() {
	df = new DriverFactory();
	prop = df.intiProperties();
	driver = df.initDriver(prop);
	travelpage =new TravelPage(driver);

}
//@AfterTest
//public void teardown() {
//	driver.quit();
//}
}
