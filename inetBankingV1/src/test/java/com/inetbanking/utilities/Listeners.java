package com.inetbanking.utilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.inetbanking.resources.BaseClass;
import com.inetbanking.resources.Extentrepoter;


public class Listeners extends BaseClass implements ITestListener{
	ExtentTest test;
	ExtentReports extent = Extentrepoter.getReportObject();
	
	//for paralla test execution we need to do Threadlocal by that each test case should not override the test object.
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		logger.info("*******Test is started********");
		test = extent.createTest(result.getMethod().getMethodName());
		
		//Paralla test execution
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info("*******Test is Successfully Execute********");
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		
		//Need This code for screen shoot
		this.driver = ((BaseClass)result.getInstance()).driver;
		
		String testMethodName = result.getMethod().getMethodName();
		try {
			extentTest.get().addScreenCaptureFromPath(getScreenShotPath(testMethodName, driver));
			logger.info("*******Failure page ScreenShoot took********");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.SKIP, "Test Skipped");
		logger.info("*******Due to previous testcase failure, this testcase is skipped********");
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Test is Started");
	}

	@Override
	public void onFinish(ITestContext context) {
	extent.flush();
	logger.info("*******Test is Finished********");
	}

}
