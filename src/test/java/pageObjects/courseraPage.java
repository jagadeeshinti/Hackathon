package pageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.baseClass;
import utilities.ExcelUtilities;

public class courseraPage extends basePage {

	
	WebDriver driver;
	baseClass bc;
	
		
	// parameterized constructor
	public courseraPage(WebDriver driver) 
	{
		super(driver);
	
	}
	
	String pageTitle = "Coursera | Degrees, Certificates, & Free Online Courses";
	
	@FindBy(xpath="//*[@id='rendered-content']/div/div[1]/div/div[2]/div[3]/a")
	WebElement thanksBox;
	
	@FindBy(xpath="//*[@class='react-autosuggest__input']")
	WebElement searchBar;
	
	@FindBy(xpath="//*[@class='nostyle search-button']")
	WebElement searchIcon;
	
	@FindBy(xpath="//*[@data-testid='search-filter-group-Language']//*[@data-testid='English-false']")
	WebElement englishFilter;
	
	@FindBy(xpath = "(//span[text()='Show more'])[2]")
	WebElement level;
	
	@FindBy(xpath = "//span[text()='Beginner']")
	WebElement levelclick;
	
	@FindBy(xpath="//*[@data-testid='search-filter-group-Level']//*[@data-testid='Beginner-false']")
	WebElement beginnerFilter;
	
	@FindBy(xpath="//h3[@class='cds-CommonCard-title css-6ecy9b']")
	List<WebElement> coursesList;
	
	@FindBy(xpath="//*[@id='rendered-content']/div/main/section[2]/div/div/div[2]/div/div/section/div[2]/div[1]/div[1]")
	WebElement ratingElement;
	
	@FindBy(xpath="//*[@id='rendered-content']/div/main/section[2]/div/div/div[2]/div/div/section/div[2]/div[3]/div[1]")
	WebElement timeRequiredElement;
	
	@FindBy(xpath="//*[@id='search-results-header-wrapper']/div/div/div/div[2]/div[2]/button/span")
	WebElement showMore;
	
//	@FindBy(xpath="/html/body/div[7]/div[3]/div/div/div[2]/div[2]/div[2]/div")
//	List<WebElement> lang_list;
	
	@FindBy(xpath="/html/body/div[7]/div[3]/div/div/div[1]/button/span")
	WebElement langClose;
	
	@FindBy(xpath="/html/body/div[2]/div/div/main/div[1]/div/div/div/div/div[1]/div/div/div/div[4]/div/div/div")
	List<WebElement> levelsList;
	
	// //span[text()="Apply"]
	@FindBy(xpath="//span[text()='Apply']")
	WebElement Apply;
	
	// 
	public void closeAdd(WebDriver driver) {
		try {
			// closing add
			thanksBox.click();
			System.out.println("-----thanks box closed-----");
		}
		catch(Exception e) {
			
		}
	}
	
	
	//
	public boolean headerValidation(WebDriver driver) throws IOException {
		System.out.println(driver.getTitle());
		if(driver.getTitle().equals(pageTitle)) {
			ExcelUtilities.writeExcelData("Web Development Validation", 0, 0, "Title is validated");
			return true;
		}
		return false;
	}
	
	
	//
	public void searchValue(WebDriver driver) throws IOException {
		bc=new baseClass();
		String val = bc.getProperties().getProperty("Course");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		// input for search bar
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		searchBar.sendKeys(val);
		// search button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		js.executeScript("arguments[0].click();",searchIcon);
		System.out.println("-----Search input is given for search bar-----");
		System.out.println("");
	}
		
	// 
	public List<WebElement> languagesValidation(WebDriver driver){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		js.executeScript("arguments[0].click();",showMore);
		List<WebElement> lang_list = driver.findElements(By.xpath("/html/body/div[7]/div[3]/div/div/div[2]/div[2]/div[2]/div"));
		lang_list.remove(0);
		return lang_list;
	}
	
	
	//
	public void languageSelection(WebDriver driver, List<WebElement> lang_list) throws IOException {
		bc=new baseClass();
		String lang_name = bc.getProperties().getProperty("LanguageFilter");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		
		// clicking on english language
		boolean lang_found = false ;
		for(WebElement we : lang_list) {
			if(we.getText().contains(lang_name)) {
				we.click();
				lang_found=true;
			}
		}
		if(lang_found == true) {
			System.out.println("-----"+lang_name+" clicked-----");
			//bc.captureScreen("Languages");
			ExcelUtilities.writeExcelData("Web Development Validation", 1, 0, lang_name+" is clicked successfully");
			Apply.click();
		}
		else {
			System.out.println("------"+lang_name+" not found-----");
			js.executeScript("arguments[0].click();",langClose);
		}
		
		//langClose.click();
		System.out.println("");
	}
	
	
	//
	public List<String> levelsValidation(WebDriver driver) throws IOException {
		List<String> allLevelsList = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();",beginnerFilter);
		int row = 0;
		int i=1;
		for(WebElement we : levelsList) {
			System.out.println(we.getText());
			ExcelUtilities.writeExcelData("Levels list",row , 0, "Level - "+i+" = "+we.getText());
			allLevelsList.add(we.getText());
			i++;
			row++;
		}
		return allLevelsList;
	}
	
	
	//
	public void selectLevel(WebDriver driver) throws IOException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("arguments[0].scrollIntoView();",level);
		levelclick.click();
		//bc.captureScreen("Levels");
		
		bc=new baseClass();
		String level_name = bc.getProperties().getProperty("Level");
		
		if(levelclick.getText().contains(level_name)) {
			System.out.println("-----"+level_name+" clicked-----");
			ExcelUtilities.writeExcelData("Web Development Validation", 2, 0, level_name+" is clicked");
		}
		else {
			System.out.println("------"+level_name+" not found-----");
		}
		
		System.out.println("");
	}
	
	
	// clicking on first course
	public void clickOnFirstCourse(WebDriver driver) throws InterruptedException {
		Thread.sleep(4000);
		WebElement firstCourse = coursesList.get(0);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",firstCourse);
	}
	
	// window handling to first course
	public void mainToFirstCourseWindowHandling(WebDriver driver) {
		
		Set<String> mainToFirstSet = driver.getWindowHandles();
		List<String> mainToFirstList = new ArrayList<String>(mainToFirstSet);
		driver.switchTo().window(mainToFirstList.get(1));
	}
	
	// clicking on second course
		public void clickOnSecondCourse(WebDriver driver) throws InterruptedException {
			Thread.sleep(4000);
			WebElement SecondCourse = coursesList.get(1);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();",SecondCourse);
		}
	
	// window handling to first course
	public void mainToSecondCourseWindowHandling(WebDriver driver) {
		
		Set<String> mainToSecondSet = driver.getWindowHandles();
		List<String> mainToSecondList = new ArrayList<String>(mainToSecondSet);
		driver.switchTo().window(mainToSecondList.get(1));
	}
	
	
	
}
