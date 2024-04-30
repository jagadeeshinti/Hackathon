package testCases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.courseraPage;
import pageObjects.firstCoursePage;
import pageObjects.secondCoursePage;
import testBase.baseClass;

public class TestCase_001_WebDevelopmentValidation extends baseClass{

	List<WebElement> languages_list;
	List<WebElement> levels_list;
	String firstCourseName;
	String firstCourseRating;
	String firstCourseDuration;
	String secondCourseName;
	String secondCourseRating;
	String secondCourseDuration;
	
	@Test(priority=1,groups= {"Regression"})
	public void SiteValidation() throws InterruptedException, IOException {
		
		courseraPage cp = new courseraPage(driver);
		Thread.sleep(4000);
		cp.closeAdd(driver);
		Thread.sleep(2000);
		if(cp.headerValidation(driver)) {
			System.out.println("Coursera title is Validated successfully");
			captureScreen("Coursera_Title_Page");
			logger.info("------Coursera home page successfully------");
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
		captureScreen("Search_Validation");
		logger.info("------Entered value WebDevelopment successfully------");
		
	}
	
	
	@Test(priority=3, dependsOnMethods= {"searchValidation"},groups= {"Regression"})
	public void languageValidation() throws InterruptedException, IOException {
		courseraPage cp = new courseraPage(driver);
		Thread.sleep(4000);
		languages_list = cp.languagesValidation(driver);
		cp.languageSelection(driver, languages_list);
		//captureScreen("Languages");
		logger.info("------Successfully clicked Language------");
	}
	
	@Test(priority=4, dependsOnMethods= {"languageValidation"},groups= {"Regression"})
	public void levelsValidation() throws InterruptedException, IOException {
		courseraPage cp = new courseraPage(driver);
		Thread.sleep(4000);
		
		cp.selectLevel(driver);
		//captureScreen("Levels");
		logger.info("------Successfully clicked Level------");
	}
	
	@Test(priority=5, dependsOnMethods= {"levelsValidation"},groups= {"Regression"})
	public void firstCourseOpening() throws InterruptedException {
		courseraPage cp = new courseraPage(driver);
		Thread.sleep(4000);
		cp.clickOnFirstCourse(driver);
		Thread.sleep(4000);
		cp.mainToFirstCourseWindowHandling(driver);
		logger.info("------Driver switched from Main page to First Course Page successfully------");
	}

	@Test(priority=6,groups= {"Regression"})
	public void firstCourseTitle() throws InterruptedException, IOException {
		firstCoursePage fcp = new firstCoursePage(driver);
		Thread.sleep(4000);
		firstCourseName = fcp.firstCourseName(driver);
		System.out.println("-----First Course Name = "+firstCourseName+"-----");
		System.out.println("");
		logger.info("------First course name fetched successfully------");
	}
	
	@Test(priority=7, dependsOnMethods= {"firstCourseTitle"},groups= {"Regression"})
	public void firstCourseRating() throws InterruptedException, IOException {
		firstCoursePage fcp = new firstCoursePage(driver);
		Thread.sleep(1000);
		firstCourseRating = fcp.firstCourseRating(driver);
		captureScreen("First_Course");
		System.out.println("-----First Course Rating = "+firstCourseRating+"-----");
		System.out.println("");
		logger.info("------First Course Rating fetched successfully------");
	}
	
	
	@Test(priority=8, dependsOnMethods= {"firstCourseRating"},groups= {"Regression"})
	public void firstCourseDuration() throws InterruptedException, IOException {
		firstCoursePage fcp = new firstCoursePage(driver);
		Thread.sleep(1000);
		firstCourseDuration = fcp.firstCourseDuration(driver);
		System.out.println("-----First Course Duration = "+firstCourseDuration+"-----");
		System.out.println("");
		logger.info("------First Course Duration fetched successfully------");
	}
	
	@Test(priority=9, dependsOnMethods= {"firstCourseTitle"},groups= {"Regression"})
	public void backToMain() throws InterruptedException {
		firstCoursePage fcp = new firstCoursePage(driver);
		Thread.sleep(1000);
		fcp.firstCourseToMainWindowHandling(driver);
		logger.info("------Driver switched from First Course page to Main Page and closed First course page closed successfully------");
	}
	
	
	@Test(priority=10,groups= {"Regression"})
	public void SecondCourseOpening() throws InterruptedException {
		courseraPage cp = new courseraPage(driver);
		Thread.sleep(4000);
		cp.clickOnSecondCourse(driver);
		Thread.sleep(4000);
		cp.mainToSecondCourseWindowHandling(driver);
		logger.info("------Driver switched from Main page to Second Course Page successfully------");
		
	}
	
	
	@Test(priority=11,groups= {"Regression"})
	public void SecondCourseTitle() throws InterruptedException, IOException {
		secondCoursePage scp = new secondCoursePage(driver);
		Thread.sleep(4000);
		secondCourseName = scp.SecondCourseName(driver);
		System.out.println("-----Second Course Name = "+secondCourseName+"-----");
		System.out.println("");
		logger.info("------Second course name fetched successfully------");
	}
	
	@Test(priority=12, dependsOnMethods= {"SecondCourseTitle"},groups= {"Regression"})
	public void SecondCourseRating() throws InterruptedException, IOException {
		secondCoursePage scp = new secondCoursePage(driver);
		Thread.sleep(1000);
		secondCourseRating = scp.SecondCourseRating(driver);
		captureScreen("Second_Course");
		System.out.println("-----Second Course Rating = "+secondCourseRating+"-----");
		System.out.println("");
		logger.info("------Second course rating fetched successfully------");
	}
	
	
	@Test(priority=13, dependsOnMethods= {"firstCourseRating"},groups= {"Regression"})
	public void SecondCourseDuration() throws InterruptedException, IOException {
		secondCoursePage scp = new secondCoursePage(driver);
		Thread.sleep(1000);
		secondCourseDuration = scp.SecondCourseDuration(driver);
		System.out.println("-----Second Course Duration = "+secondCourseDuration+"-----");
		System.out.println("");
		logger.info("------Second course duration fetched successfully------");
	}
	
	@Test(priority=14, dependsOnMethods= {"firstCourseTitle"},groups= {"Regression"})
	public void backToMainPage() throws InterruptedException {
		secondCoursePage scp = new secondCoursePage(driver);
		Thread.sleep(1000);
		scp.SecondCourseToMainWindowHandling(driver);
		logger.info("------Driver switched from Second Course page to Main Page and closed First course page closed successfully------");
	}
	
	
	
	
	
	
}
