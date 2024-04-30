package testCases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import pageObjects.courseraPage;
import testBase.baseClass;
import utilities.ExcelUtilities;

public class TestCase_002_LanguageLearningValidation extends baseClass {

	List<WebElement> languages_list;
	List<WebElement> levels_list;
	
	@Test(priority=1,groups= {"Smoke"})
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
	
	@Test(priority=2, dependsOnMethods= {"SiteValidation"},groups= {"Regression"})
	public void searchValidation() throws InterruptedException, IOException {
		
		courseraPage cp = new courseraPage(driver);
		Thread.sleep(4000);
		cp.searchValue(driver);
		logger.info("------Entered value WebDevelopment successfully------");
		
	}
	
	@Test(priority=3, dependsOnMethods= {"searchValidation"},groups= {"Regression"})
	public void languagesListValidation() throws InterruptedException, IOException {
		courseraPage cp = new courseraPage(driver);
		Thread.sleep(4000);
		languages_list = cp.languagesValidation(driver);
		System.out.println("-----Languages List-----");
		int i=1;
		int row = 0;
		for(WebElement ll : languages_list) {
			System.out.println("Language - "+i+" = "+ll.getText());
			ExcelUtilities.writeExcelData("Languages list",row , 0, "Language - "+i+" = "+ll.getText());
			i++;
			row++;
		}
		System.out.println("");
		logger.info("------Languages list fetched successfully------");
	}
	
	@Test(priority=4, dependsOnMethods= {"searchValidation"},groups= {"Regression"})
	public void levelsListValidation() throws InterruptedException, IOException {
		courseraPage cp = new courseraPage(driver);
		Thread.sleep(4000);
		cp.levelsValidation(driver);
		System.out.println("");
		logger.info("------Levels list fetched successfully------");
	}
	
	
}
