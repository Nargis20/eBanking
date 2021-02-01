package com.inetbanking.testCases;


import org.testng.Assert;
import org.testng.annotations.Test;
import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.resources.BaseClass;

public class TC_LoginTest_001 extends BaseClass {

	@Test
	public void loginTest() {
		LoginPage lp =new LoginPage(driver);
		lp.setUserName(username);
		logger.info("********Entered UserName*********");
		
		lp.setPassword(password);
		logger.info("********Entered Password*********");
		
		lp.clickLogin();
		
		if(driver.getTitle().equals("GTPL Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			logger.info("********Login test passed*********");
		}else
		{
			Assert.assertTrue(false);
			logger.info("********Login test failed*********");
		}
	}
}
