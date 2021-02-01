package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {

	WebDriver driver;
	public AddCustomerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText ="New Customer")
	WebElement lnkNewCust;
	
	@FindBy(css = "[name='name']")
	WebElement txtCName;
	
	@FindBy(css = "[name='rad1']")
	WebElement rdCGender;
	
	@FindBy(id = "dob")
	WebElement cDOB;
	
	@FindBy(css="[name='addr']")
	WebElement txtAddress;
	
	@FindBy(css="[name='city']")
	WebElement txtCity;
	
	@FindBy(css="[name='state']")
	WebElement txtState;
	
	@FindBy(css="[name='pinno']")
	WebElement txtPin;
	
	@FindBy(css="[name='telephoneno']")
	WebElement txtTeleNo;	
	
	@FindBy(css="[name='emailid']")
	WebElement txtEmail;
	
	@FindBy(css="[name='sub']")
	WebElement btnSubmit;
	
	public void clickAddNewCustomer() {
		lnkNewCust.click();
	}
	public void custName(String cname) {
		txtCName.sendKeys(cname);
	}
	public void custGender(String cgender) {
		 rdCGender.click();
	}
	public void custDob(String mm, String dd, String yy) {
		cDOB.sendKeys(mm);
		cDOB.sendKeys(dd);
		cDOB.sendKeys(yy);
	}
	public void custAddress(String caddress) {
		txtAddress.sendKeys(caddress);
	}
	public void custCity(String ccity) {
		txtCity.sendKeys(ccity);
	}
	public void custState(String cstate) {
		txtState.sendKeys(cstate);
	}
	public void custPinNo(int cpinno) {
		txtPin.sendKeys(String.valueOf(cpinno));
	}
	public void custTelephoneNo(int ctelephoneno) {
		txtTeleNo.sendKeys(String.valueOf(ctelephoneno));
	}
	public void custEmailID(String cemailid) {
		txtEmail.sendKeys(cemailid);
	}
	public void custSubmit() {
		btnSubmit.click();
	}
}
