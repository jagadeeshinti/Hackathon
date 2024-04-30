package StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObjects.courseraPage;
import pageObjects.firstCoursePage;
import pageObjects.secondCoursePage;
import utilities.ExcelUtilities;

public class LanguageLearning {

	Hooks h=new Hooks();
	WebDriver driver=h.getDriver();
	
	courseraPage cp = new courseraPage(driver);
	firstCoursePage fcp = new firstCoursePage(driver);
	secondCoursePage scp = new secondCoursePage(driver);
	
	List<WebElement> languages_list;
	List<WebElement> levels_list;
	
	
	@Given("navigate to the learning page")
	public void navigate_to_the_learning_page() throws IOException, InterruptedException {
		cp.closeAdd(driver);
		Thread.sleep(2000);
		if(cp.headerValidation(driver)) {
			System.out.println("Coursera title is Validated successfully");
		}
		else {
			System.out.println("Coursera title is Invalid");
		}
		System.out.println("");
	    cp.searchValue(driver);
	}
	
	@Then("get the languages and their count")
	public void get_the_languages_and_their_count() throws InterruptedException, IOException {
		courseraPage cp = new courseraPage(driver);
		Thread.sleep(4000);
		languages_list = cp.languagesValidation(driver);
		System.out.println("-----Languages List-----");
		System.out.println("-----No of Languages = "+languages_list.size());
		int i=1;
		int row = 0;
		for(WebElement ll : languages_list) {
			System.out.println("Language - "+i+" = "+ll.getText());
			ExcelUtilities.writeExcelData("Languages list",row , 0, "Language - "+i+" = "+ll.getText());
			i++;
			row++;
		}
		
	}
	
	@Then("get the levels and their count")
	public void get_the_levels_and_their_count() throws IOException, InterruptedException {
		Thread.sleep(4000);
		List<String> levels=cp.levelsValidation(driver);
	    System.out.println("-----No of levels = "+levels.size());
	}
	
	
	
	
	
	
}
