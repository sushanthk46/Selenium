package core.testCases;


import java.util.List;
import java.util.Set;

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
import core.utilities.Dataproviders;

public class TC002_HSIMHomePage extends BaseClass {
	
	private static boolean isExecuted = false;
	
	@Test(dependsOnMethods = "core.testCases.TC001_LoginPage.verifyLogin",dataProvider="HSIMData", dataProviderClass= Dataproviders.class)
	public void SelectFunction(String menuName, String col1, String col2, String col3, String col4, String col5,String col6, String col7, String col8, String col9, String col10, String col11,String col12,String col13, String col14) {
		try {
			
			System.out.println("Session ID in HSIM: " + ((RemoteWebDriver) driver).getSessionId());
			
			//Routing to Menu
			if(!isExecuted) {
			HomePage hp= new HomePage(driver);
			hp.MenuEntry(menuName);
			System.out.println("Menu Name" +menuName);
			Thread.sleep(3000);
			hp.SearchBtn();
			isExecuted=true;
			}
			
			
			
			
			//Redirecting to add page
			HSIMHomePage hsp= new HSIMHomePage(driver);
			String select="funcCode";
			System.out.println("Function" +select +col1);
			hsp.funcSelect(col1,select);
			
			if(col1=="A") {
			hsp.Checked();
			
			 //Listing of the Elements prsent in active page
				/*List<WebElement> allElements = driver.findElements(By.xpath("//*"));
				for (WebElement element : allElements) {
				    System.out.println("Tag: " + element.getTagName() + " | Text: " + element.getDomAttribute("name"));
				}*/
			//First Page inputs
			
			//Dropdown id's and values passed
			
			String ids[]= {"siFreqType","siFreqStartDD","siFreqHldyStat","execTime",};
			String values[]= {col4,col5,col6,col7};
			
			for(int i=0; i<ids.length;i++) {
				hsp.funcSelect(values[i],ids[i]);
			}
			
			//Input id's and values passed
			String inputId[]= {"cifID","nextExecDate_ui"};
			String inputValues[]= {col3,col8};
			
			for(int i=0; i<inputId.length;i++) {
				hsp.InputCIF(inputId[i],inputValues[i]);
			}
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			
			//Radio Button id's and values passed
		    String radioId[]= {"validateCrncyHoliday","autoPost","carryFrwdIfFailed"};
		    String radioValue[]= {"Y","Y","Y"};
		    for(int i=0; i<radioId.length;i++) {
				hsp.radioCheck(radioId[i],radioValue[i]);
			}
		    
		   
		    
		   String validate= "//input[@id='Validate']";
			hsp.SubmitBtn(validate);
			
		//First Page Execution completed
			
			//Second Page execution started
			
			String sid="//a[@id='isim']";
			hsp.SubmitBtn(sid);
			hsp.funcSelect(col12,"mAmtInd");
			
			String inputIdpg2[]= {"mRefCrncy","mAmount"};
			String inputValuepg2[]= {col13,col14};
			for(int i=0;i<inputIdpg2.length;i++) {
				hsp.InputCIF(inputIdpg2[i],inputValuepg2[i]);
			}
//			
//			 WebElement dropdownOptionmAmtInd = wait.until(ExpectedConditions.elementToBeClickable(By.id("mAmtInd")));
//				wait.until(ExpectedConditions.elementToBeClickable(dropdownOptionmAmtInd)).click();
//				System.out.println("dropdownOption");
//				
//			    Select dropdownmAmtInd = new Select(dropdownOptionmAmtInd);
//			    dropdownmAmtInd.selectByValue("F");
			Thread.sleep(1000);
			    
			    hsp.radioCheck("mPtranType","D");
			    
//			    WebElement currency = wait.until(ExpectedConditions.elementToBeClickable(By.id("mRefCrncy")));
//			    currency.sendKeys("INR");
//			    
//			    WebElement amt = wait.until(ExpectedConditions.elementToBeClickable(By.id("mAmount")));
//			    amt.sendKeys("1000");
			    
			    String popUpAccount= "//a[@id='sLnk8']";
				hsp.SubmitBtn(popUpAccount);
				
				//WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='sLnk1']")));
				//hsp.SubmitBtn(submitButton);
				
				
				WebElement acnt = wait.until(ExpectedConditions.elementToBeClickable(By.id("acctId")));
				js.executeScript("arguments[0].value='003120100000072';arguments[0].dispatchEvent(new Event('change'));", acnt);
				js.executeScript("window.onbeforeunload = function() {};");
				//driver.switchTo().frame("FINW");	
				 Thread.sleep(1000);
				 
				 
				 
				WebElement dropdownOptionamtInd = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("amtInd")));
				/*wait.until(ExpectedConditions.elementToBeClickable(dropdownOptionamtInd)).click();
					System.out.println("dropdownOption");
					
				    Select dropdownamtInd = new Select(dropdownOptionamtInd);
				    dropdownamtInd.selectByValue("F");*/
				    
				    JavascriptExecutor js4 = (JavascriptExecutor) driver;
				    js4.executeScript("arguments[0].value='F';", dropdownOptionamtInd);
				    
				   
				    
				    JavascriptExecutor js1 = (JavascriptExecutor) driver;
				    js.executeScript("document.getElementById('amount').value='1000';");
				    
				    
				    
				    WebElement accept= driver.findElement(By.xpath("//input[@id='Accept']"));
				    js.executeScript("arguments[0].click();", accept);
					//hsp.SubmitBtn(accept);
				    
				    WebElement addition= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='isimDet_AddNew']")));
				    js.executeScript("arguments[0].click();", addition);
				    
				    WebElement dropdownOptionmAmtInd1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("mAmtInd")));
					wait.until(ExpectedConditions.elementToBeClickable(dropdownOptionmAmtInd1)).click();
					System.out.println("dropdownOption");
					
					 Select dropdownmAmtInd1 = new Select(dropdownOptionmAmtInd1);
					    dropdownmAmtInd1.selectByValue("F");
					    
					    WebElement radioButton = driver.findElement(By.xpath("//input[@type='radio' and @value='C']"));
					    js.executeScript("arguments[0].click();", radioButton);
					    
					    WebElement currency1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("mRefCrncy")));
					    currency1.sendKeys("INR");
					    
					   
					   WebElement amt1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("mAmount")));
					    amt1.sendKeys("1000");
					    
					    String submitButton1 = "//a[@id='sLnk8']";
					    hsp.SubmitBtn(submitButton1);
					    
					    Thread.sleep(1000);				    
					    WebElement acnt1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("acctId")));
					    js.executeScript("arguments[0].value='0003130024';arguments[0].dispatchEvent(new Event('change'));", acnt1);
					    js.executeScript("window.onbeforeunload = function() {};");
						
						WebElement dropdownOptionamtInd1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("amtInd")));
						JavascriptExecutor js2 = (JavascriptExecutor) driver;
					    js2.executeScript("arguments[0].value='F';", dropdownOptionamtInd1);
					    
					    js.executeScript("document.getElementById('amount').value='1000';");
					    
					    WebElement accept1= driver.findElement(By.xpath("//input[@id='Accept']"));
					    js.executeScript("arguments[0].click();", accept1);
					    
					    WebElement formSubmit= driver.findElement(By.xpath("//input[@id='Submit']"));
					    js.executeScript("arguments[0].click();", formSubmit);
					    
					    WebElement formSubmit1= driver.findElement(By.xpath("//input[@id='Accept']"));
					    js.executeScript("arguments[0].click();", formSubmit1);
		}else if(col1=="V") {
			TC_021HSIMVerifyPage vp= new TC_021HSIMVerifyPage();
			
			vp.VerifyStart();
			
		}
		
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
	}

}
