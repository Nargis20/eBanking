package com.inetbanking.resources;



import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig readconfig = new ReadConfig();
	public String baseURL= readconfig.getApplicationURL();
	public String username= readconfig.getUsername();
	public String password= readconfig.getPassword();
	
	public WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void initializeDriver(String br) {
		logger = Logger.getLogger("ebanking");
		PropertyConfigurator.configure("log4j.properties");
		
		//invoke desired browser
		if(br.equals("chrome"))
		{
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
		driver = new ChromeDriver();
		logger.info("********Invoked Chrome Browser*********");
		}
		else if (br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
			driver =new FirefoxDriver();
			logger.info("********Invoked Firefox Browser*********");
		}
		else if(br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver", readconfig.getIEPath());
			driver = new InternetExplorerDriver();
			logger.info("********Invoked Internet Explorer Browser*********");
		}
		else if(br.equals("msedge"))
		{
			System.setProperty("webdriver.edge.driver", readconfig.getMsEdgePath());
			driver = new EdgeDriver();
			logger.info("********Invoked MsEdge Browser*********");
		}
		
		driver.get(baseURL);
		logger.info("********Navigated to Home page*********");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
		//Capture the system date & time
		Date date_time =new Date();
		//System.out.println(date_time);
		String Convert_date_time = date_time.toString().replace(" ", "_").replace(":", "_");
		//System.out.println(Convert_date_time);
		
		File shots = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		String destinationFile = System.getProperty("user.dir")+ "\\ScreenShots\\"+testCaseName + "_" + Convert_date_time + ".png";
		FileHandler.copy(shots, new File(destinationFile));
		
		return destinationFile;
	}
	
	public String randomString() {
		String generatesString = RandomStringUtils.randomAlphabetic(8);
		return generatesString;	
	}
	
	public String randomNum() {
		String generatesNum = RandomStringUtils.randomNumeric(6);
		return generatesNum;	
	}
	@AfterClass
	public void tearDown() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}
}
