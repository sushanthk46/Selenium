package core.pageObject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Hoaacvtd extends BasePage {
	
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	
	public Hoaacvtd(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//select[@id='verifyCancel']") WebElement functCode;
	@FindBy(xpath="//input[@id='tempForacid']") WebElement tempacID;

}
