package testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import pageObjects.courseraPage;
import pageObjects.enterprisePage;
import testBase.baseClass;
import utilities.ExcelUtilities;

public class TestCase_003_EnterpriseValidation extends baseClass{
	
	
	@Test(priority=1)
	public void SiteValidation() throws InterruptedException, IOException {
		
		courseraPage cp = new courseraPage(driver);
		Thread.sleep(4000);
		cp.closeAdd(driver);
		Thread.sleep(2000);
		if(cp.headerValidation(driver)) {
			System.out.println("Coursera title is Validated successfully");
			logger.info("------Coursera home page validated successfully------");
		}
		else {
			System.out.println("Coursera title is Invalid");
			logger.info("------Coursera home page is invalid------");
		}
		System.out.println("");
	}
	@Test(priority=2,groups= {"Regression"})
	public void Enterprise() throws InterruptedException, IOException {
		enterprisePage form=new enterprisePage(driver);
		form.forEnterprise();
		captureScreen("EnterPrise");
		logger.info("------Enterprise page is opened successfully------");
	}
	@Test(priority=3,groups= {"Regression"})
	public void Formfilling() throws InterruptedException, IOException {
		enterprisePage form=new enterprisePage(driver);
		String[] data=ExcelUtilities.readingExcel("Sheet1");
		form.invalidFormFillingEmail(data);
		logger.info("------Courses for Campus form with invalid details (email) are filled successfully------");
		Thread.sleep(4000);
	}
	@Test(priority=4,groups= {"Regression"})
	public void Submitform() throws InterruptedException, IOException {
		enterprisePage form=new enterprisePage(driver);
		
		form.submitForm();
	}
	@Test(priority=5,groups= {"Regression"})
	public void VerifyMsg() throws InterruptedException, IOException {
		enterprisePage form=new enterprisePage(driver);
		String error_msg=form.captureMessageEmail();
		captureScreen("Invalid_Email_Form");
		form.printMsg(error_msg);
		form.clearForm();
		logger.info("------Verify message for invalid details thrown successfully------");
	}
	
	@Test(priority=6,groups= {"Regression"})
	public void Formfilling2() throws InterruptedException, IOException {
		enterprisePage form=new enterprisePage(driver);
		String[] data=ExcelUtilities.readingExcel("Sheet2");
		form.invalidFormFillingPhone(data);
		logger.info("------Courses for Campus form with invalid details(phone) are filled successfully------");
		Thread.sleep(10000);
	}
	@Test(priority=7,groups= {"Regression"})
	public void Submitform2() throws InterruptedException, IOException {
		enterprisePage form=new enterprisePage(driver);
		
		form.submitForm();
	}
	@Test(priority=8,groups= {"Regression"})
	public void VerifyMsg2() throws InterruptedException, IOException {
		enterprisePage form=new enterprisePage(driver);
		String error_msg=form.captureMessagePhone();
		form.printMsg(error_msg);
		captureScreen("Invalid_Phone_Form");
		form.clearForm();
		logger.info("------Verify message for invalid details thrown successfully------");
	}
	
	@Test(priority=9,groups= {"Regression"})
	public void Formfilling3() throws InterruptedException, IOException {
		enterprisePage form=new enterprisePage(driver);
		String[] data=ExcelUtilities.readingExcel("Sheet3");
		form.validFormFilling(data);
		logger.info("------Courses for Campus form with valid details are filled successfully------");
		Thread.sleep(4000);
	}
	@Test(priority=10,groups= {"Regression"})
	public void Submitform3() throws InterruptedException, IOException {
		enterprisePage form=new enterprisePage(driver);
		
		form.submitForm();
	}
	@Test(priority=11,groups= {"Regression"})
	public void VerifyMsg3() throws InterruptedException, IOException {
		enterprisePage form=new enterprisePage(driver);
		String success_msg=form.captureMessagePhone();
		captureScreen("Valid_Form");
		form.printMsg(success_msg);
		logger.info("------Verify message for valid details are captured successfully------");
	}

}
