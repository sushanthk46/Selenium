package core.testCases;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import core.pageObject.HSIMHomePage;
import core.pageObject.HomePage;

public class TC002_HSIMHomePage extends BaseClass {
	
	@Test(dependsOnMethods = "core.testCases.TC001_LoginPage.verifyLogin")
	public void SelectFunction() {
		try {
			
			System.out.println("Session ID in HSIM: " + ((RemoteWebDriver) driver).getSessionId());
			
			HomePage hp= new HomePage(driver);
			hp.MenuEntry("HSIM");
			Thread.sleep(3000);
			hp.SearchBtn();
			
			//Redirecting to add page
			HSIMHomePage hsp= new HSIMHomePage(driver);
			hsp.funcSelect("A");
			hsp.Checked();
			hsp.ImgClick();
			
			String mainWindow= driver.getWindowHandle();
			
			Set<String> allWindows = driver.getWindowHandles();
				for(String wins: allWindows) {
					if(!wins.equals(mainWindow)){
						driver.switchTo().window(wins);
						System.out.println(driver.getTitle());
						break;
					}
				}
				
				//WebElement frame= driver.findElement(By.name("GetCustIdCriteria"));
				/*List<WebElement> allElements = driver.findElements(By.xpath("//*"));
				for (WebElement element : allElements) {
				    System.out.println("Tag: " + element.getTagName() + " | Text: " + element.getDomAttribute("name"));
				}*/
				
				driver.switchTo().frame("GetCustIdCriteria");
				
				WebElement frameElement = driver.findElement(By.xpath("//input[@name='Ok']"));
				System.out.println("Frame Element" +frameElement);
			hsp.SubmitBtn(frameElement);
			
			driver.switchTo().parentFrame();
			
			
			driver.switchTo().frame("GetCustIdResults");
			WebElement hotKey= driver.findElement(By.xpath("//a[@hotkeyid='100' and contains (@id,'0')]"));
			hsp.SubmitBtn(hotKey);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
	}

}
