package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.resources.BaseClass;
import com.inetbanking.utilities.XLSUtils;

public class TC_LoginDDT_002 extends BaseClass{
	@Test(dataProvider = "Login Data")
	public void loginDDT(String user, String pwd) {
		LoginPage lp =new LoginPage(driver);
		lp.setUserName(user);
		logger.info("User name provided");
		lp.setPassword(pwd);
		logger.info("Password provided");
		lp.clickLogin();
		
		//call Alert present method
		if(isAlertPresent()==true) {
			driver.switchTo().alert().accept(); //close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.info("Login failed");
		}
		else {
			Assert.assertTrue(true);
			logger.info("Login Passed");
			/*lp.clickLogout();
			driver.switchTo().alert().accept();//close logout alert
			driver.switchTo().defaultContent();*/
		}
	}
	
	//User Defined method created to check Alert is present or not
	public boolean isAlertPresent() {
		try
		{
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
	}
	
	@DataProvider(name ="Login Data")
	public String[][] getdata() throws IOException{
		String path = System.getProperty("user.dir")+ "/src/test/java/com/inetbanking/testData/LoginDataDDT.xlsx";
		int rownum = XLSUtils.getRowCount(path, "Sheet1");
		int colcount = XLSUtils.getCellCount(path, "Sheet1", 1);
		
		String logindata[][] = new String[rownum][colcount];
		
		for (int i=1; i<=rownum; i++)
		{
			//if j start with 0 we need to write < only but if start with 1 we need to write <=
			for(int j =0; j<colcount; j++)
			{
				logindata[i-1][j]=XLSUtils.getCellData(path, "Sheet1", i, j);
				}
		}
		return logindata;
		
	}
}
