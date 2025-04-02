package core.pageObject;


import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.utilities.ExcelUtility;

public class HoaactdPage extends BasePage {
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	
	public HoaactdPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//select[@id='templateFunction']") WebElement functCode;
	@FindBy(xpath="//input[@id='cifId']") WebElement idCif;
	@FindBy(xpath="//input[@id='schmCode']") WebElement schemeCode;
	@FindBy(xpath="//input[@id='Accept']") WebElement goButton;
	@FindBy(xpath="//input[@id='glSubHeadCode']") WebElement generalCode;
	@FindBy(xpath="//a[@id='tdint']") WebElement ItTab;
	@FindBy(xpath="//a[@id='tdsch']") WebElement schemeTab;
	@FindBy(xpath="//input[@id='depPerdMths']") WebElement depPeriod;
	@FindBy(xpath="//input[@id='depAmt']") WebElement depAmount;
	@FindBy(xpath="//input[@id='maturityDate_ui']") WebElement matDate;
	@FindBy(xpath="//input[@id='nomineeFlg']") private List<WebElement> nomineeRadioButtons;
	@FindBy(xpath="//a[@id='tdflw']") WebElement flowTab;
	@FindBy(xpath="//a[@id='tdren']") WebElement rncTab;
	@FindBy(xpath="//a[@id='relatedpartydetails']") WebElement rpdTab;
	@FindBy(xpath="//input[@id='Submit']") WebElement submitButton;
	@FindBy(xpath="//label[@id='acctNum']") WebElement accNum;
	@FindBy(xpath="//img[@title='Logout']") WebElement logout;
	@FindBy(xpath="//input[@name='Submit2']") WebElement outSubmit;
	
	
	public void functionSelect(String keys) {dropdownSelect(functCode,keys);}
	public void enterCif(String keys) {sendKeys(idCif, keys);}
	public void enterScheme(String keys) {sendKeys(schemeCode, keys);}
	public void genrlCode(String keys) {sendKeys(generalCode, keys);}
	public void goButton() {buttonPress(goButton);}
	public void Ittab() {buttonPress(ItTab);}
	public void scheme() {buttonPress(schemeTab);}
	public void depositPeriod(String keys) {sendKeys(depPeriod, keys);}
	public void depositAmount(String keys) {sendKeys(depAmount, keys);}
	public void maturityDate(String keys) {sendKeys(matDate, keys);}
	public void radioButton(String keys) {radioSelect(keys);}
	public void flowTab() {buttonPress(flowTab);}
	public void rncTab() {buttonPress(rncTab);}
	public void rpdTab() {buttonPress(rpdTab);}
	public void submitButton() {buttonPress(submitButton);}
	public void accountNo() {getLabeltext(accNum);}
	
	
	
	
	public void dropdownSelect(WebElement id, String value) {
		
		wait.until(ExpectedConditions.visibilityOf(id));
		
		Select select =new Select(id);
		select.selectByContainsVisibleText(value);
	}
	
	public void sendKeys(WebElement id, String keys) {
		
		wait.until(ExpectedConditions.visibilityOf(id)).clear();
		id.sendKeys(keys);
		
	}
	
	public void buttonPress(WebElement id) {
		
		wait.until(ExpectedConditions.visibilityOf(id));
		id.click();
		
	}
	public void menuSelect(String menuName) {
		HomePage hp = new HomePage(driver);
		hp.MenuEntry(menuName);
		System.out.println("Menu Name" + menuName);
		hp.SearchBtn();
		driver.switchTo().frame("CoreServer");
		driver.switchTo().frame("FINW");
	}
	
	public void alertFunc() {
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	    System.out.println("Alert text: " + alert.getText());
	    
	    alert.accept();
	}
	
	public void radioSelect(String value) {
		for (WebElement radio : nomineeRadioButtons) {
		    if (radio.getDomAttribute("value").equals(value)) { // Select the correct one
		        radio.click();
		        break;
		    }
		}
	}
	
	public void getLabeltext(WebElement id) {
		
		String acNo= id.getText();
		System.out.println("Account No" +acNo);
		
		String path = ".\\testdata\\HOAACTDData.xlsx";
		ExcelUtility eu = new ExcelUtility(path);

		try {
			eu.setCellData("Sheet1", 2, 2, acNo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	 public void logout() {
		 driver.switchTo().defaultContent();

			driver.switchTo().frame("loginFrame");
			logout.click();
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			System.out.println("Alert text: " + alert.getText());

			alert.accept();
			outSubmit.click();
	 }

}
