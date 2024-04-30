package StepDefinition;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.Scenario;
import testBase.baseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;

public class Hooks {
	 public  static WebDriver driver;
	 public static baseClass b=new baseClass();
	@Before
    public static void setup() throws IOException {
		driver=new ChromeDriver();
	   	//driver.get(b.getProperties().getProperty("appUrl"));
		driver.get("https://www.coursera.org/");
	   	driver.manage().window().maximize();
	   	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

  @After
  public void tearDown() {
     driver.quit();
  }
  
  @AfterStep
  public void addScreenshot(Scenario scenario) {

      if(!scenario.isFailed()) {
      	TakesScreenshot ts=(TakesScreenshot) driver;
      	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
      	scenario.attach(screenshot, "image/png",scenario.getName());
      }
  }
  public WebDriver getDriver() {
	  return driver;
  }
}

