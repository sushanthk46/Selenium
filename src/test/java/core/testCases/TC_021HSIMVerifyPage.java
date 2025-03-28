package core.testCases;

import java.io.FileReader;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import core.pageObject.HSIMHomePage;
import core.pageObject.HomePage;
import core.pageObject.LoginPage;
import core.utilities.Dataproviders;

public class TC_021HSIMVerifyPage extends BaseClass {

	private static boolean isExecuted = false;
	
	
@Test
	public void VerifyStart(String functional, String menu, String srlNo) {

		try {
			System.out.println("Verify Started");
			
				System.out.println("Session ID in HSIM: " +menu);
				System.out.println("Functional" +functional);

			
			FileReader file = new FileReader("./src//test//resources//config.properties");
			p = new Properties();
			p.load(file);

			String authUser = p.getProperty("authUname");
			String authPwd = p.getProperty("authPwd");
			LoginPage lp = new LoginPage(driver);
			System.out.println("uname");
			lp.txtUser(authUser);
			lp.txtPwd(authPwd);
			lp.btnClick();

			Thread.sleep(1000);

				HomePage hp = new HomePage(driver);
				hp.MenuEntry(menu);
				System.out.println("Menu in Verify" + menu);
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
			System.out.println("SRL NO:"+srlNo);
			
			 Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			    System.out.println("Alert text: " + alert.getText());
			    
			    //Accept tje alert
			    alert.accept();
			    
			String srlNoId="siSrlNo";
			hsp.InputCIF(srlNoId, srlNo);
			
			String goButton = "//input[@id='Accept']";
			hsp.SubmitBtn(goButton);
			
			String secondPage = "//a[@id='isim']";
			hsp.SubmitBtn(secondPage);
			
			String thirdPage = "//a[@id='sLnk8']";
			hsp.SubmitBtn(thirdPage);
			
			String backButton = "//input[@id='BackToParent']";
			hsp.SubmitBtn(backButton);
			//hsp.Checked();
			
			String fourthPage = "//img[@id='isimDet_NextRec']";
			hsp.SubmitBtn(fourthPage);
			
			hsp.SubmitBtn(thirdPage);
			
			hsp.SubmitBtn(backButton);
			
			String submitButton = "//input[@id='Submit']";
			hsp.SubmitBtn(submitButton);
			
			hsp.SubmitBtn(goButton);
			
			System.out.println("column 3" + functional);
			
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			
			Thread.sleep(3000);

			driver.switchTo().defaultContent();

			driver.switchTo().frame("loginFrame");

			WebElement formSubmit1 = driver.findElement(By.xpath("//img[@title='Logout']"));
			js1.executeScript("arguments[0].click();", formSubmit1);

			Alert alert1 = wait.until(ExpectedConditions.alertIsPresent());
			System.out.println("Alert text: " + alert1.getText());

			alert1.accept();
			

			WebElement formSubmit2 = driver.findElement(By.xpath("//input[@name='Submit2']"));
			js1.executeScript("arguments[0].click();", formSubmit2);
			
			
			
		}

		catch (Exception e) {

			System.out.println(e.getMessage());
		}
	}

}
