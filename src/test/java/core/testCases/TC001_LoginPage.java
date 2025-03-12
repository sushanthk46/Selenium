package core.testCases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import core.pageObject.HomePage;
import core.pageObject.LoginPage;
import core.utilities.Dataproviders;

public class TC001_LoginPage extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass= Dataproviders.class)
	public void verifyLogin(String uname, String pwd,String res) {
	
	try {
		
        System.out.println("Session ID in verifyLogin: " + ((RemoteWebDriver) driver).getSessionId());

		
	LoginPage lp= new LoginPage(driver);
	System.out.println("uname" +uname);
	lp.txtUser(uname);
	lp.txtPwd(pwd);
	lp.btnClick();
	Thread.sleep(1000);
	HomePage hp= new HomePage(driver);
	hp.CoreserverSelect("CoreServer");
	
	//Select dropdown
	/*WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("appSelect")));
    Select dropdown = new Select(dropdownElement);
    dropdown.selectByValue("CoreServer");
    
    //Waiting for alert
    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
    System.out.println("Alert text: " + alert.getText());
    
    //Accept tje alert
    alert.accept();*/
	}
	catch(Exception e) {
		System.out.println(e);
	}
}
}
