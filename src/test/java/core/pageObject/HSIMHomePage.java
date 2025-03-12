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

	public HSIMHomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//input[@id='Accept']") WebElement funcTxt;
	@FindBy(xpath="//select[@id='funcCode']") WebElement funcCode;
	@FindBy(xpath="//input[@id='siType'][2]") WebElement siType;
	@FindBy(xpath="//img[@class='img']") WebElement imgClass;
	//@FindBy(xpath="//img[@id='Ok']") WebElement submit;
	

	public void funcSelect(String dropDownSelect) {
		
		//
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
		for (WebElement iframe : iframes) {
			System.out.println("Iframe ID: " + iframe.getDomProperty("id"));
		    System.out.println("Iframe Name: " + iframe.getDomProperty("name"));
		    System.out.println("Iframe Src: " + iframe.getDomProperty("src"));
		}
		
		
		driver.switchTo().frame("CoreServer");
		
		
		driver.switchTo().frame("FINW");
		/*List<WebElement> allElements = driver.findElements(By.xpath("//*"));
		for (WebElement element : allElements) {
		    System.out.println("Tag: " + element.getTagName() + " | Text: " + element.getText());
		}*/
		
		
		WebElement dropdownOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("funcCode")));
		wait.until(ExpectedConditions.elementToBeClickable(dropdownOption)).click();
		System.out.println("dropdownOption" +funcCode);
		
	    Select dropdown = new Select(dropdownOption);
	    dropdown.selectByValue(dropDownSelect);

	    
	    funcTxt.click();
	}
	
	public void Checked() {
		
		siType.click();
	}
	public void ImgClick() {
		imgClass.click();
	}
	
	public void SubmitBtn(WebElement frameElement) {
		System.out.println("Submit Button" +frameElement);
		frameElement.click();
		
	}
}
