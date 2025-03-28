package core.testCases;

import java.io.FileReader;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import core.pageObject.HSIMHomePage;
import core.pageObject.HomePage;
import core.pageObject.LoginPage;
import core.utilities.Dataproviders;
import core.utilities.ExcelUtility;

public class TC002_HSIMHomePage extends BaseClass {

	private static boolean isExecuted = false;

	@Test(dependsOnMethods = "core.testCases.TC001_LoginPage.verifyLogin", dataProvider = "HSIMData", dataProviderClass = Dataproviders.class)
	
	public void runLoopTest(String menuName, String[] testData) {
		String funcCol= testData[0];
		TC_021HSIMVerifyPage vp= new TC_021HSIMVerifyPage();
		TC_022HSIMModifyPage mp= new TC_022HSIMModifyPage();
		TC_023HSIMInspectPage ip= new TC_023HSIMInspectPage();
		
		System.out.println("Entered switch"+funcCol);
		
		switch(funcCol) {
		case "A":SelectFunction(testData,menuName);
		break;
		case "V":vp.VerifyStart(testData[0],menuName,testData[2]);
		break;
		case "M":mp.modifyStart(testData[0],menuName,testData[2],testData[3],testData[4]);
		break;
		case "I":ip.inspectStart(testData[0],menuName,testData[2]);
		break;
		}
	}
	public void SelectFunction(String[] columns,String menuName) {
		try {
			

			 //Routing to Menu
			if (!isExecuted) {
				HomePage hp = new HomePage(driver);
				hp.MenuEntry(menuName);
				System.out.println("Menu Name" + menuName);
				Thread.sleep(3000);
				hp.SearchBtn();
				isExecuted = true;
			}

			 //Redirecting to add page
			HSIMHomePage hsp = new HSIMHomePage(driver);
			String select = "funcCode";
			System.out.println("Function" + select + columns[0]);
			hsp.funcSelect(columns[0], select);

			hsp.Checked();

			// Listing of the Elements prsent in active page
			/*
			 * List<WebElement> allElements = driver.findElements(By.xpath("*")); for
			 * (WebElement element : allElements) { System.out.println("Tag: " +
			 * element.getTagName() + " | Text: " + element.getDomAttribute("name")); }
			 */
			// First Page inputs

			// Dropdown id's and values passed
			String ids[] = { "siFreqType", "siFreqStartDD", "siFreqHldyStat", "execTime", };
			String values[] = { columns[3], columns[4], columns[5], columns[6] };

			for (int i = 0; i < ids.length; i++) {
				hsp.funcSelect(values[i], ids[i]);
			}

		//	 Input id's and values passed
			String inputId[] = { "cifID", "nextExecDate_ui" };
			String inputValues[] = { columns[2], columns[7] };

			for (int i = 0; i < inputId.length; i++) {
				hsp.InputCIF(inputId[i], inputValues[i]);
			}

			JavascriptExecutor js = (JavascriptExecutor) driver;

			// Radio Button id's and values passed
			String radioId[] = { "validateCrncyHoliday", "autoPost", "carryFrwdIfFailed" };
			String radioValue[] = { "Y", "Y", "Y" };
			for (int i = 0; i < radioId.length; i++) {
				hsp.radioCheck(radioId[i], radioValue[i]);
			}
			
			Thread.sleep(1000);
			

			String validateButton = "//input[@id='Validate']";
			hsp.SubmitBtn(validateButton);

			 //First Page Execution completed

			 //Second Page execution started
			System.out.println("Entered 2nd page");
			String sid = "//a[@id='isim']";
			hsp.SubmitBtn(sid);
			Thread.sleep(3000);
			hsp.funcSelect("F", "mAmtInd");

			String inputIdpg2[] = { "mRefCrncy", "mAmount" };
			String inputValuepg2[] = { "INR", "1000" };
			for (int i = 0; i < inputIdpg2.length; i++) {
				hsp.InputCIF(inputIdpg2[i], inputValuepg2[i]);
			}
			
			/* WebElement dropdownOptionmAmtInd = wait.until(ExpectedConditions.elementToBeClickable(By.id("mAmtInd")));
				wait.until(ExpectedConditions.elementToBeClickable(dropdownOptionmAmtInd)).click();
				System.out.println("dropdownOption");
				
			    Select dropdownmAmtInd = new Select(dropdownOptionmAmtInd);
			    dropdownmAmtInd.selectByValue("F");*/
			Thread.sleep(1000);

			hsp.radioCheck("mPtranType", "D");

			    /*WebElement currency = wait.until(ExpectedConditions.elementToBeClickable(By.id("mRefCrncy")));
			    currency.sendKeys("INR");
			    
			    WebElement amt = wait.until(ExpectedConditions.elementToBeClickable(By.id("mAmount")));
			    amt.sendKeys("1000");*/

			String popUpAccount = "//a[@id='sLnk8']";
			hsp.SubmitBtn(popUpAccount);

			/*String submitButton = "//a[@id='sLnk1']";
			 hsp.SubmitBtn(submitButton);*/

			WebElement acnt = wait.until(ExpectedConditions.elementToBeClickable(By.id("acctId")));
			js.executeScript("arguments[0].value='003120100000072';arguments[0].dispatchEvent(new Event('change'));",
					acnt);
			js.executeScript("window.onbeforeunload = function() {};");
			// driver.switchTo().frame("FINW");
			Thread.sleep(1000);

			WebElement dropdownOptionamtInd = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("amtInd")));
			/*
			 * wait.until(ExpectedConditions.elementToBeClickable(dropdownOptionamtInd)).
			 * click(); System.out.println("dropdownOption");
			 * 
			 * Select dropdownamtInd = new Select(dropdownOptionamtInd);
			 * dropdownamtInd.selectByValue("F");
			 */

			JavascriptExecutor js4 = (JavascriptExecutor) driver;
			js4.executeScript("arguments[0].value='F';", dropdownOptionamtInd);

			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("document.getElementById('amount').value='1000';");

			String accept = "//input[@id='Accept']";
			 hsp.SubmitBtn(accept);

			WebElement addition = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='isimDet_AddNew']")));
			js1.executeScript("arguments[0].click();", addition);
			
			hsp.funcSelect("F", "mAmtInd");

			/*WebElement dropdownOptionmAmtInd1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("mAmtInd")));
			wait.until(ExpectedConditions.elementToBeClickable(dropdownOptionmAmtInd1)).click();
			System.out.println("dropdownOption");

			Select dropdownmAmtInd1 = new Select(dropdownOptionmAmtInd1);
			dropdownmAmtInd1.selectByValue("F");*/

			WebElement radioButton = driver.findElement(By.xpath("//input[@type='radio' and @value='C']"));
			js1.executeScript("arguments[0].click();", radioButton);

			WebElement currency1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("mRefCrncy")));
			currency1.sendKeys("INR");

			WebElement amt1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("mAmount")));
			amt1.sendKeys("1000");

			String submitButton1 = "//a[@id='sLnk8']";
			hsp.SubmitBtn(submitButton1);

			Thread.sleep(3000);
			WebElement acnt1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("acctId")));
			js1.executeScript("arguments[0].value='0003130024';arguments[0].dispatchEvent(new Event('change'));", acnt1);
			js1.executeScript("window.onbeforeunload = function() {};");

			WebElement dropdownOptionamtInd1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("amtInd")));
			JavascriptExecutor js2 = (JavascriptExecutor) driver;
			js2.executeScript("arguments[0].value='F';", dropdownOptionamtInd1);

			js1.executeScript("document.getElementById('amount').value='1000';");

			WebElement accept1 = driver.findElement(By.xpath("//input[@id='Accept']"));
			js1.executeScript("arguments[0].click();", accept1);

			Thread.sleep(3000);

			WebElement formSubmit = driver.findElement(By.xpath("//input[@id='Submit']"));
			js1.executeScript("arguments[0].click();", formSubmit);

			WebElement labelElement = driver.findElement(By.xpath("//label[@id='compField']"));
			String tranSent = labelElement.getText();

			System.out.println("Label sentence" + tranSent);
			Pattern pattern = Pattern.compile("Srl\\.\\s*No\\.\\s*-?\\s*([A-Za-z0-9]+)", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(tranSent);

			if (matcher.find()) {
				String extractedCode = matcher.group(1).trim();
				System.out.println("tran ID" + extractedCode);
				String path = ".\\testdata\\HSIMData.xlsx";
				ExcelUtility eu = new ExcelUtility(path);

				eu.setCellData("Sheet1", 2, 3, extractedCode);
			}

			Thread.sleep(3000);

			driver.switchTo().defaultContent();

			driver.switchTo().frame("loginFrame");

			WebElement formSubmit1 = driver.findElement(By.xpath("//img[@title='Logout']"));
			js1.executeScript("arguments[0].click();", formSubmit1);

			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			System.out.println("Alert text: " + alert.getText());

			alert.accept();

			WebElement formSubmit2 = driver.findElement(By.xpath("//input[@name='Submit2']"));
			js1.executeScript("arguments[0].click();", formSubmit2);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
