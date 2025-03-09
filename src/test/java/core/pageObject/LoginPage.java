package core.pageObject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@name='usertxt']") WebElement userNm;
	@FindBy(xpath="//input[@id='passtxt']") WebElement passTxt;
	@FindBy(xpath="//input[@id='Submit']") WebElement loginBtn;
	
	
	public void txtUser(String uname) {
		userNm.sendKeys(uname);
	}
	
	public void txtPwd(String pwd) {
		passTxt.sendKeys(pwd);
	}
	
	public void btnClick() {
		loginBtn.click();
	}
}
