package core.pageObject;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
	
	
	public HomePage(WebDriver driver) {
		
		super(driver);
		
	}
	
	//@FindBy(id="appSelect") WebElement dropdownElement;
	@FindBy(xpath="//input[@id='menuSelect']") WebElement menuTxt;
	@FindBy(xpath="//button[@id='menuSearcherGo']") WebElement sercClk;
	
	public void CoreserverSelect(String dropDownSelect) {
		
		System.out.println("Entered Homepage" +dropDownSelect);
	
	//Select dropdown
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("appSelect")));
		System.out.println("Dropdown" +dropdownElement);
		
	    Select dropdown = new Select(dropdownElement);
	    dropdown.selectByValue(dropDownSelect);
	    
	    //Waiting for alert
	    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	    System.out.println("Alert text: " + alert.getText());
	    
	    //Accept tje alert
	    alert.accept();
	}
	
	public void MenuEntry(String menuItem) {
		
		menuTxt.sendKeys(menuItem);
		
	}
	public void SearchBtn() {
		sercClk.click();
	}
}
