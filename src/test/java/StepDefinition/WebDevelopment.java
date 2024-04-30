package StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageObjects.courseraPage;
import pageObjects.firstCoursePage;
import pageObjects.secondCoursePage;
import StepDefinition.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WebDevelopment {

	Hooks h=new Hooks();
	WebDriver driver=h.getDriver();
	
	courseraPage cp = new courseraPage(driver);
	firstCoursePage fcp = new firstCoursePage(driver);
	secondCoursePage scp = new secondCoursePage(driver);
	
	List<WebElement> languages_list;
	
	@Given("Navigate to the coursera home page")
	public void navigate_to_the_coursera_home_page() throws IOException, InterruptedException {
		
		Thread.sleep(2000);
		if(cp.headerValidation(driver)) {
			System.out.println("Coursera title is Validated successfully");
		}
		else {
			System.out.println("Coursera title is Invalid");
		}
		System.out.println("");
	}
	
	@When("the user clicks on the inputBox")
	public void the_user_clicks_on_the_input_box() throws IOException, InterruptedException {
	    cp.closeAdd(driver);
	    Thread.sleep(4000);
	    cp.searchValue(driver);
	    Thread.sleep(4000);
	}
	
	@When("the user selects the checkBox")
	public void the_user_selects_the_check_box() throws IOException, InterruptedException {
//		w.selectLevel();
//		w.selectLanguage();
		languages_list = cp.languagesValidation(driver);
		cp.languageSelection(driver, languages_list);
		Thread.sleep(4000);
		cp.selectLevel(driver);
	}
	
	@Then("verify the name of the first course")
	public void verify_the_name_of_the_first_course() throws InterruptedException, IOException {
//		String[] course1=w.courseDetails(0,w.coursenames.get(0));
		cp.clickOnFirstCourse(driver);
		Thread.sleep(4000);
		cp.mainToFirstCourseWindowHandling(driver);
		Thread.sleep(4000);
		String firstCourseName = fcp.firstCourseName(driver);
		System.out.println("-----First Course Name = "+firstCourseName);
		
	}
	
	@Then("verify the rating of the first course")
	public void verify_the_rating_of_the_first_course() throws InterruptedException, IOException {
//		String[] course1=w.courseDetails(0,w.coursenames.get(0));
		String firstCourseRating = fcp.firstCourseRating(driver);
		System.out.println("-----First Course Rating = "+firstCourseRating);
		
	}
	
	@Then("verify the duration of the first course")
	public void verify_the_duration_of_the_first_course() throws InterruptedException, IOException {
//		String[] course1=w.courseDetails(0,w.coursenames.get(0));
		String firstCourseDuration = fcp.firstCourseDuration(driver);
		System.out.println("-----First Course Duration = "+firstCourseDuration);
		Thread.sleep(1000);
		fcp.firstCourseToMainWindowHandling(driver);
		
	}
	
	
	@Then("verify the name of the second course")
	public void verify_the_name_of_the_second_course() throws InterruptedException, IOException {
		//String[] course2=w.courseDetails(1,w.coursenames.get(1));
		cp.clickOnSecondCourse(driver);
		Thread.sleep(4000);
		cp.mainToSecondCourseWindowHandling(driver);
		Thread.sleep(4000);
		String secondCourseName = scp.SecondCourseName(driver);
		System.out.println("-----Second Course Name = "+secondCourseName);
	}
	
	
	
	@Then("verify the rating of the second course")
	public void verify_the_rating_of_the_second_course() throws InterruptedException, IOException {
		//String[] course2=w.courseDetails(1,w.coursenames.get(1));
		String secondCourseRating = scp.SecondCourseRating(driver);
		System.out.println("-----Second Course Rating = "+secondCourseRating);
	}
	
	@Then("verify the duration of the second course")
	public void verify_the_duration_of_the_second_course() throws InterruptedException, IOException {
		//String[] course2=w.courseDetails(1,w.coursenames.get(1));
		String secondCourseDuration = scp.SecondCourseDuration(driver);
		System.out.println("-----Second Course Duration = "+secondCourseDuration);
		scp.SecondCourseToMainWindowHandling(driver);
	}
	
	
}
