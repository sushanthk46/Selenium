package core.pageObject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HSIMHomePage extends BasePage {
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	private static boolean isExecuted = false;

	public HSIMHomePage(WebDriver driver) {
		super(driver);
		
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//input[@id='Accept']") WebElement funcTxt;
	@FindBy(xpath="//select[@id='funcCode']") WebElement funcCode;
	@FindBy(xpath="//input[@id='siType'][2]") WebElement siType;
	//@FindBy(xpath="//img[@class='img']") WebElement imgClass;
	//@FindBy(xpath="//img[@id='Ok']") WebElement submit;
	@FindBy(xpath="//input[@id='validateCrncyHoliday']") WebElement validChck;
	

	public void funcSelect(String dropDownSelect, String id) {
		
		//
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		if(!isExecuted) {
			System.out.println("Inside driver switch");
		driver.switchTo().frame("CoreServer");
		driver.switchTo().frame("FINW");
		 isExecuted=true;
		}
		
		System.out.println("Drop option and ID" +id +dropDownSelect);
		
		WebElement dropdownOption = wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
		wait.until(ExpectedConditions.elementToBeClickable(dropdownOption)).click();
		System.out.println("dropdownOption" +funcCode);
		
	    Select dropdown = new Select(dropdownOption);
	    dropdown.selectByValue(dropDownSelect);

	    if(id=="funcCode") {
	    System.out.println("Inside submit Click");
	    funcTxt.click();
	    }
	}
	
	public void Checked() {
		
		siType.click();
	}
	
	public void InputCIF(String id, String keys) {
		
		WebElement wb= wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
		wb.sendKeys(keys);
	}
	
	public void SubmitBtn(String frameElement) {
//		System.out.println("Submit Button" +frameElement);
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		System.out.println("Entered Click");
//		 js.executeScript("arguments[0].click();", frameElement);
//		 System.out.println("clicked" +frameElement);
		WebElement sid= wait.until(ExpectedConditions.elementToBeClickable(By.xpath(frameElement)));
		sid.click();
		 
		
	}
	public void radioCheck(String radioID, String radioValue) {
		List<WebElement> radioButtons = driver.findElements(By.id(radioID));
		for (WebElement radio : radioButtons) {
		    if (radio.getDomAttribute("value").equals(radioValue)) { // Select the correct one
		        radio.click();
		        break;
		    }
		}
	}
}
