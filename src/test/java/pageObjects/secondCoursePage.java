package pageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.ExcelUtilities;

public class secondCoursePage extends basePage {

	public secondCoursePage(WebDriver driver) {
		super(driver);
	}
	
	
	String courseName;
	String courseRating;
	String courseDuration;
	
	@FindBy(xpath = "//h1[@data-e2e='hero-title']")
	WebElement title;
	
	////*[@id=\"rendered-content\"]/div/main/section[2]/div/div/div[2]/div/div/section/div[2]/div[1]/div[1]
	@FindBy(xpath="//*[@id='rendered-content']/div/main/section[2]/div/div/div[2]/div/div/section/div[2]/div[1]/div[1]")
	WebElement rating1;
	
	
	// //*[@id=\"rendered-content\"]/div/main/section[2]/div/div/div[2]/section/div/div[1]/div/div/div[1]
	@FindBy(xpath="//*[@id='rendered-content']/div/main/section[2]/div/div/div[2]/section/div/div[1]/div/div/div[1]")
	WebElement rating2;
	
	
	// //*[@id='rendered-content']/div/main/section[2]/div/div/div[2]/div/div/section/div[2]/div[3]/div[1]
	@FindBy(xpath="//*[@id='rendered-content']/div/main/section[2]/div/div/div[2]/div/div/section/div[2]/div[3]/div[1]")
	WebElement timeDuration1;
	
	// //*[@id="rendered-content"]/div/main/section[2]/div/div/div[2]/section/div/div[3]/div/div/div[1]
	@FindBy(xpath="//*[@id='rendered-content']/div/main/section[2]/div/div/div[2]/section/div/div[3]/div/div/div[1]")
	WebElement timeDuration2;
	
	
	//
	public String SecondCourseName(WebDriver driver) throws IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		courseName = title.getText();
		ExcelUtilities.writeExcelData("Second Course Data", 0, 0, "Second Course name = "+courseName);
		return courseName;
	}
	
	
	//
	public String SecondCourseRating(WebDriver driver) throws IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			js.executeScript("arguments[0].scrollIntoView();",rating1);
			courseRating = rating1.getText();
			ExcelUtilities.writeExcelData("Second Course Data", 1, 0, "Second Course rating = "+courseRating);
		}
		catch(Exception e) {
			js.executeScript("arguments[0].scrollIntoView();",rating2);
			courseRating = rating2.getText();
			ExcelUtilities.writeExcelData("Second Course Data", 1, 0, "Second Course rating = "+courseRating);
		}
		return courseRating;
	}
	
	
	//
	public String SecondCourseDuration(WebDriver driver) throws IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			js.executeScript("arguments[0].scrollIntoView();",timeDuration1);
			courseDuration = timeDuration1.getText();
			ExcelUtilities.writeExcelData("Second Course Data", 2, 0, "Second Course Duration = "+courseDuration);
		}
		catch(Exception e) {
			js.executeScript("arguments[0].scrollIntoView();",timeDuration2);
			courseDuration = timeDuration2.getText();
			ExcelUtilities.writeExcelData("Second Course Data", 2, 0, "Second Course Duration = "+courseDuration);
		}
		
		return courseDuration;
		
	}
	
	// switching back to first window
	public void SecondCourseToMainWindowHandling(WebDriver driver) {
		Set<String> firstToMainSet = driver.getWindowHandles();
		List<String> firstToMainList = new ArrayList<String>(firstToMainSet);
		driver.close();
		driver.switchTo().window(firstToMainList.get(0));
	}
	
	
	
	
	
	

}
