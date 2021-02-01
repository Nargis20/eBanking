package com.inetbanking.testCases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.resources.BaseClass;

public class TC_AddCustomerTest_003 extends BaseClass{
	@Test
	public void addNewCustomer() throws InterruptedException, IOException {
		LoginPage lp =new LoginPage(driver);
		lp.setUserName(username);
		lp.setPassword(password);
		lp.clickLogin();
		
		Thread.sleep(3000);
		
		logger.info("Providing New Customer information");
		AddCustomerPage addcust = new AddCustomerPage(driver);
		addcust.clickAddNewCustomer();
		addcust.custName("Waheeda");
		addcust.custGender("Female");
		addcust.custDob("10","15", "1985");
		Thread.sleep(2000);
		addcust.custAddress("USA");
		addcust.custCity("Pflugerville");
		addcust.custState("Texas");
		addcust.custPinNo(123456);
		addcust.custTelephoneNo(520-678-9469);
		
		String email = randomString()+"@gmail.com";
		addcust.custEmailID(email);
		addcust.custSubmit();
		Thread.sleep(2000);
		
		logger.info("Validation Started.....");
		boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");
		if(res ==true) {
			Assert.assertTrue(true);
			logger.info("New Customer Added successfully");
		}else {
			logger.info("Failed to Add new Customer.");
			getScreenShotPath("addNewCustomer", driver);
			Assert.assertTrue(false);
		}
	}
}
