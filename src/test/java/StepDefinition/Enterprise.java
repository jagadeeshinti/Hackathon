package StepDefinition;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.courseraPage;
import pageObjects.enterprisePage;
import utilities.ExcelUtilities;

public class Enterprise {

	Hooks h=new Hooks();
	WebDriver driver=h.getDriver();
	
	courseraPage cp = new courseraPage(driver);
	enterprisePage form=new enterprisePage(driver);
	
	@Given("navigate to the Coursera home page")
	public void navigate_to_the_coursera_home_page() throws InterruptedException, IOException {
		cp.closeAdd(driver);
		Thread.sleep(2000);
		if(cp.headerValidation(driver)) {
			System.out.println("Coursera title is Validated successfully");
		}
		else {
			System.out.println("Coursera title is Invalid");
		}
		System.out.println("");
	}
	
	@When("the user clicks on For Enterprises")
	public void the_user_clicks_on_for_enterprises() {
		form.forEnterprise();
			
	}
	
	@Then("enter the invalid details of email in the form")
	public void enter_the_details_in_the_form1() throws IOException, InterruptedException {
		String[] data=ExcelUtilities.readingExcel("Sheet1");
		form.invalidFormFillingEmail(data);
		Thread.sleep(2000);
		form.submitForm();
	}
	
	@Then("verify the first invalid error message")
	public void verify_the_error_message_1() throws IOException {
		String error_msg=form.captureMessageEmail();
		form.printMsg(error_msg);
		form.clearForm();
	}
	
	@Then("enter the invalid details of phone in the form")
	public void enter_the_details_in_the_form2() throws IOException, InterruptedException {
		String[] data=ExcelUtilities.readingExcel("Sheet2");
		form.invalidFormFillingPhone(data);
		Thread.sleep(4000);
		form.submitForm();
	}
	
	@Then("verify the second invalid error message")
	public void verify_the_error_message_2() throws IOException {
		String error_msg=form.captureMessagePhone();
		form.printMsg(error_msg);
		form.clearForm();
	}
	
	@Then("enter the valid details in the form")
	public void enter_the_details_in_the_form3() throws IOException, InterruptedException {
		String[] data=ExcelUtilities.readingExcel("Sheet3");
		form.validFormFilling(data);
		Thread.sleep(4000);
		form.submitForm();
	}
	
	@Then("verify the third valid message")
	public void verify_the_message_3() throws IOException {
		String success_msg=form.captureSuccessMessage();
		form.printMsg(success_msg);
	}
	
	
	
}
