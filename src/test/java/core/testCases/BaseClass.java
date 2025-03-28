package core.testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;



public class BaseClass {

public static WebDriver driver;
protected static WebDriverWait wait;
public Logger logger;  //Log4j
public Properties p;


@FindBy(xpath="//button[@id='details-button']") WebElement detailsBtn;
@FindBy(xpath="//a[@id='proceed-link']") WebElement proceedBtn;
	
	@BeforeClass
	public void setup() throws IOException
	{
		
		if (driver == null) {
		//Loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
				
		logger=LogManager.getLogger(this.getClass()); //lOG4J2
		
		logger.debug("Debug logs");
		System.setProperty("webdriver.edge.driver", "C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedgedriver.exe");

		
		driver= new EdgeDriver();
		EdgeOptions options = new EdgeOptions();
		
		options.addArguments("--start-maximized");
			
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL")); // reading url from properties file.
		System.out.println(p.getProperty("appURL"));
		driver.manage().window().maximize();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("details-button"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("proceed-link"))).click();
		 driver.switchTo().frame("loginFrame");
		 
		 System.out.println("WebDriver initialized. Session ID: " + ((RemoteWebDriver) driver).getSessionId());
    } else {
        System.out.println("Reusing existing WebDriver. Session ID: " + ((RemoteWebDriver) driver).getSessionId());
    }
        
	}
	
	/*@AfterClass
	public void tearDown()
	{
		driver.quit();
	}*/
	
	public String randomeString()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	public String randomeNumber()
	{
		String generatednumber=RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
	
	public String randomeAlphaNumberic()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(3);
		String generatednumber=RandomStringUtils.randomNumeric(3);
		return (generatedstring+"@"+generatednumber);
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
	
	
}
