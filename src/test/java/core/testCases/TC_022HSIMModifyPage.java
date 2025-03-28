package core.testCases;

import java.io.FileReader;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import core.pageObject.HSIMHomePage;
import core.pageObject.HomePage;
import core.pageObject.LoginPage;

public class TC_022HSIMModifyPage extends BaseClass {
	
	@Test

	public void modifyStart(String functional, String menu, String srlNo, String modifyId, String modifyValue) {
	try {
		System.out.println("Verify Started");
		
			System.out.println("Session ID in HSIM: " +menu);
			System.out.println("Functional" +functional);

		
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);

		String authUser = p.getProperty("uname");
		String authPwd = p.getProperty("pwd");
		LoginPage lp = new LoginPage(driver);
		System.out.println("uname");
		lp.txtUser(authUser);
		lp.txtPwd(authPwd);
		lp.btnClick();

		Thread.sleep(1000);

			HomePage hp = new HomePage(driver);
			hp.MenuEntry(menu);
			System.out.println("Menu in Modify" + menu);
			Thread.sleep(3000);
			hp.SearchBtn();
			//hp.CoreserverSelect("CoreServer");
			//isExecuted = true;
		
		Thread.sleep(1000);
		HSIMHomePage hsp = new HSIMHomePage(driver);
		
		Thread.sleep(1000);
		driver.switchTo().frame("CoreServer");
		driver.switchTo().frame("FINW");			
		String select = "funcCode";
		System.out.println("Functional" +functional);
		hsp.funcSelect(functional, select);
		
		 Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		    System.out.println("Alert text: " + alert.getText());
		    
		    //Accept tje alert
		    alert.accept();
		
		String srlNoId="siSrlNo";
		hsp.InputCIF(srlNoId, srlNo);
		
		String goButton = "//input[@id='Accept']";
		hsp.SubmitBtn(goButton);
		
		hsp.funcSelect(modifyId, modifyValue );
		
		
		
		String secondPage = "//a[@id='isim']";
		hsp.SubmitBtn(secondPage);
		
		String thirdPage = "//a[@id='sLnk8']";
		hsp.SubmitBtn(thirdPage);
		
		hsp.SubmitBtn(goButton);
		
		String fourthPage = "//img[@id='isimDet_NextRec']";
		hsp.SubmitBtn(fourthPage);
		
		hsp.SubmitBtn(thirdPage);
		
		hsp.SubmitBtn(goButton);
		
		String submitButton = "//input[@id='Submit']";
		hsp.SubmitBtn(submitButton);
		
		hsp.SubmitBtn(goButton);
		
	}
	catch(Exception e) {
		System.out.println(e.getMessage());
	}
	}

}
