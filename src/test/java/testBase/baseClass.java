package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

public class baseClass 
{
	
	public static WebDriver driver;
	public static Logger logger;
	public static Properties p;
	
	@BeforeClass(groups= {"Smoke","Regression"})
	@Parameters({"os","browser"})
	public void setUp(@Optional("windows") String os, @Optional("chrome") String br) throws IOException {
		if(getProperties().getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities cap=new DesiredCapabilities();
			if(os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("No matching os.....");
				return;
			}
			switch(br.toLowerCase()) {
			case "chrome":cap.setBrowserName("chrome");
						break;
			case "edge":cap.setBrowserName("MicrosoftEdge");
						break;
			default:System.out.println("No matching browser....");
						return;
			}
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		}
		else{
			switch(br.toLowerCase()) 
			{
			case "chrome": driver=new ChromeDriver();
			                break;
			case "edge": driver=new EdgeDriver();
			                 break;
			case "firefox":driver=new FirefoxDriver();
			                 break;
			}
		}
		//Loading log4j file
	    logger = LogManager.getLogger(this.getClass());
		//driver.get(getProperties().getProperty("appURL"));
	    driver.get("https://www.coursera.org/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	@AfterStep
    public void addScreenshot(Scenario scenario) {
    	// this is for cucumber junit report
//        if(scenario.isFailed()) {
        	TakesScreenshot ts=(TakesScreenshot) driver;
        	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
        	scenario.attach(screenshot, "image/png",scenario.getName());
//        }
    }
	
	
	@AfterClass(groups= {"Smoke","Regression"})
	public void tearDown() 
	{
		driver.quit();
	}
	public Properties getProperties() throws IOException {
		FileReader file=new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		p=new Properties();
		p.load(file);
		return p;
	}
	public String captureScreen(String name) {
	    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + name + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}

