package com.inetbanking.resources;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Extentrepoter {

	static ExtentReports extent;
	
	public static ExtentReports getReportObject()
	{
		//Create report name with test execution time
		String timeStamp = new SimpleDateFormat("MM.dd.yyyy-hh.mm.ss").format(new Date());
		String repName ="Test-Report-"+ timeStamp + ".html";
		String path = System.getProperty("user.dir")+"\\Reports\\"+repName;
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Internet Banking - Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Tester", "Nargis");
		return extent;
	}
}
