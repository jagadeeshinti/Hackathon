package pageObjects;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import utilities.ExcelUtilities;

public class enterprisePage extends basePage {
	
	public enterprisePage(WebDriver driver) {
			super(driver);
			// TODO Auto-generated constructor stub
		}
	public static JavascriptExecutor js;
	String msg;

	Actions ac = new Actions(driver);
	
	@FindBy(xpath = "//div[@class='css-19qryfx']//img[@class='rc-CourseraLogo']")
	WebElement LogoClick;
	
	@FindBy(xpath = "//p[text()='Coursera']")
	WebElement ScrollCoursera;
	
	@FindBy(xpath = "//a[text()='For Enterprise']")
	WebElement EnterpriseClick;
	
	@FindBy(xpath = "//a[text()='Solutions']")
	WebElement SolutionClick;

	@FindBy(xpath = "//p[text()='Coursera for Campus']")
	WebElement CampusClick;
	
	@FindBy(xpath = "//div[@class='css-9c36bz']")
	WebElement Scrollform;
	
	@FindBy(xpath = "//input[@id='FirstName']")
	WebElement FirstName;
	
	@FindBy(xpath = "//input[@id='LastName']")
	WebElement LastName;
	
	@FindBy(xpath = "//input[@id='Email']")
	WebElement Email;
	
	@FindBy(xpath = "//input[@id='Phone']")
	WebElement Phone;
	
	@FindBy(id = "Institution_Type__c")
	WebElement InstitutionType;
	
	@FindBy(xpath = "//input[@id='Company']")
	WebElement InstitutionName;
	
	@FindBy(id = "Title")
	WebElement jobtitle;
	
	@FindBy(id = "What_the_lead_asked_for_on_the_website__c")
	WebElement describe;
	

	@FindBy(id = "Country")
	WebElement country;
	
	@FindBy(id = "State")
	public
	WebElement state;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement submit;

	@FindBy(xpath = "//div[@id='ValidMsgEmail']")
	WebElement Error_message_email;
	
	@FindBy(xpath="//h1")
	WebElement Success_msg;
	
	@FindBy(xpath="//*[@id='ValidMsgPhone']")
	WebElement Error_msg_Phone;
	
	public void homePage() throws InterruptedException {
		LogoClick.click();
		Thread.sleep(3000);
		scrolling(ScrollCoursera);
	}
	public void forEnterprise() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();",EnterpriseClick);
		EnterpriseClick.click();
		SolutionClick.click();
		CampusClick.click();
	}
	public void invalidFormFillingEmail(String[] data) {
		scrolling(Scrollform);
		FirstName.sendKeys(data[0]);
		LastName.sendKeys(data[1]);
		Email.sendKeys(data[2]);
		Phone.sendKeys(data[3]);
		dropDown(InstitutionType,data[4]);
		InstitutionName.sendKeys(data[5]);
		scrolling(InstitutionName);
		dropDown(jobtitle,data[6]);
		dropDown(describe,data[7]);
		dropDown(country,data[8]);
		dropDown(state,data[9]);
	}
	public void invalidFormFillingPhone(String[] data) {
		scrolling(Scrollform);
		FirstName.sendKeys(data[0]);
		LastName.sendKeys(data[1]);
		Email.sendKeys(data[2]);
		Phone.sendKeys(data[3]);
		dropDown(InstitutionType,data[4]);
		InstitutionName.sendKeys(data[5]);
		scrolling(InstitutionName);
		dropDown(jobtitle,data[6]);
		dropDown(describe,data[7]);
		dropDown(country,data[8]);
		dropDown(state,data[9]);
	}
	public void validFormFilling(String[] data) {
		scrolling(Scrollform);
		FirstName.sendKeys(data[0]);
		LastName.sendKeys(data[1]);
		Email.sendKeys(data[2]);
		Phone.sendKeys(data[3]);
		dropDown(InstitutionType,data[4]);
		InstitutionName.sendKeys(data[5]);
		scrolling(InstitutionName);
		dropDown(jobtitle,data[6]);
		dropDown(describe,data[7]);
		dropDown(country,data[8]);
		dropDown(state,data[9]);
	}
	
	public void clearForm() {
		scrolling(Scrollform);
		FirstName.clear();
		LastName.clear();
		Email.clear();
		Phone.clear();
		InstitutionName.clear();
	}
	
	public void dropDown(WebElement ele,String s) {
		Select select=new Select(ele);
		select.selectByVisibleText(s);
	}
	public void scrolling(WebElement element) {
		js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();",element);
	}
	public void submitForm() {
		js.executeScript("arguments[0].click();",submit);
	}
	public String captureMessageEmail() throws IOException {
		
				
		try{
			msg = Error_message_email.getText();
			ExcelUtilities.writeExcelData("Form Details", 1, 0, msg);
		}
		catch(Exception e) {
			msg = Success_msg.getText();
			ExcelUtilities.writeExcelData("Form Details", 2, 0, msg);
		}
		System.out.println(msg);
		return msg;
	}
	public String captureMessagePhone() throws IOException {
		
		
		try{
			msg = Error_msg_Phone.getText();
			ExcelUtilities.writeExcelData("Form Details", 3, 0, msg);
		}
		catch(Exception e) {
			msg = Success_msg.getText();
			ExcelUtilities.writeExcelData("Form Details", 4, 0, msg);
		}
		System.out.println(msg);
		return msg;
	}
public String captureSuccessMessage() throws IOException {
		
		
		try{
			msg = Success_msg.getText();
			ExcelUtilities.writeExcelData("Form Details", 4, 0, msg);
		}
		catch(Exception e) {
			msg = e.getMessage();
			ExcelUtilities.writeExcelData("Form Details", 5, 0, msg);
		}
		System.out.println(msg);
		return msg;
	}
	public void printMsg(String error) throws IOException {
		ExcelUtilities.writeExcelData("Form Details", 0, 0, msg);
	}

}
