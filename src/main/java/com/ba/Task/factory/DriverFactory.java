 package com.ba.Task.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public WebDriver driver;
	public static String highlight;
	private OptionsManager optionsManager; 
	private Properties prop;
		/**
		 * this method used to initialize the driver
		 * @param browserName
		 * @return
		 */
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
		public WebDriver initDriver(Properties prop) {
		this.prop =prop;
			String browserName = prop.getProperty("browser"); 
			highlight=prop.getProperty("highlight");
			
			System.out.println("browser name is :" + browserName);
			
			optionsManager =new OptionsManager(prop);
			
			
			if(browserName.equalsIgnoreCase("chrome")) {
				//WebDriverManager.chromedriver().setup();
				String osname = prop.getProperty("os.name");

				if(osname.startsWith("Linux")){
				    System.setProperty("webdriver.chrome.driver", "E:\\Java demo\\selenium_Task\\chromedriver") ;
				  }else if(osname.startsWith("Windows")){
				    System.setProperty("webdriver.chrome.driver", "E:\\Java demo\\selenium_Task\\chromedriver.exe") ;
				  }
				
				  
				if(Boolean.parseBoolean(prop.getProperty("remote"))){
					init_remoteDriver("chrome");
				}
				else {
//				driver =new ChromeDriver(optionsManager.getChromeOptions());
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
				}
			}
			else if(browserName.equalsIgnoreCase("firefox")) {
			//	WebDriverManager.firefoxdriver().setup();
				System.setProperty("webdriver.gecko.driver", "./src/test/resources/Driver/geckodriver.exe");
				if(Boolean.parseBoolean(prop.getProperty("remote"))){
					init_remoteDriver("firefox");
				}
				else {
					//driver =new FirefoxDriver();
					tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
				}
				
			}
			else {
				System.out.println("please pass the right browser name"+browserName);
			}
//			driver.manage().deleteAllCookies();
//	    	driver.manage().window().maximize();
		//	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//	driver.get(prop.getProperty("url")); 
	
		//	return driver;
			
			
			getDriver().manage().deleteAllCookies();
			getDriver().manage().window().maximize();
			//getDriver().manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS)
			getDriver().get(prop.getProperty("url"));
			
			return getDriver();
		}
		private void init_remoteDriver(String browserName) {
		System.out.println("running test on remote with browser"+ browserName);
		if(browserName.equals("chrome")) {
		DesiredCapabilities caps =DesiredCapabilities.chrome();	
		caps.setCapability(ChromeOptions.CAPABILITY, optionsManager.getChromeOptions());
		try {
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),caps));
		} catch (MalformedURLException e) {
		
			e.printStackTrace();
		}
		}
		else if(browserName.equals("firefox")) {
			DesiredCapabilities caps =DesiredCapabilities.firefox();	
			caps.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsManager.getFirefoxOptions());
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),caps));
			} catch (MalformedURLException e) {
			
				e.printStackTrace();
			}
			}
		}
		public WebDriver getDriver() {
			return tlDriver.get();
		}
		
		public Properties intiProperties() {  
			Properties prop =null; 
			FileInputStream ip =null;
			
			String env =System.getProperty("env");//mvn clean install -Denv="qa"
			try {
			if(env==null) {
				System.out.println("Running on Environment:PROD env...");
			
					ip =new FileInputStream(".//src//test//resources//config//config.properties");
				
			}
			else {
				System.out.println("Running on Environment:" +env);
				switch(env) {
				case "qa":
					ip =new FileInputStream(".//src//test//resources//config//qa.config.properties");
					break;
				case "dev":
					ip =new FileInputStream(".//src//test//resources//config//dev.config.properties");
					break;
				case "stage":
					ip =new FileInputStream(".//src//test//resources//config//stage.config.properties");
					break;
					default:
						System.out.println("NO ENV found...");
						throw new Exception("NOENVFOUNDEXCEPTION");
					
				}
			}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
			
				e.printStackTrace();
			}
			try {
				
				prop =new Properties();
				prop.load(ip); 
			} 
			
			
		
			
	 catch (IOException e) {
				e.printStackTrace();
			}
			
			return prop;
		}
		
		/**
		 * TAKE A SCREENSHOT
		 */
		public String getScreenshot() {
			File srcFile =((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
			String path= System.getProperty("user.dir")+"/screenshot/"+ System.currentTimeMillis()+".png";
			
			File destination =new File(path);
			
			try {
				FileUtils.copyFile(srcFile, destination);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return path;
			
		}
	
}
