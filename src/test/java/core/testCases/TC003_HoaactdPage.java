package core.testCases;

import org.testng.annotations.Test;

import core.pageObject.HoaactdPage;
import core.utilities.Dataproviders;

public class TC003_HoaactdPage extends BaseClass {
	
	
	@Test(dependsOnMethods = "core.testCases.TC001_LoginPage.verifyLogin",dataProvider = "HOAACTDData", dataProviderClass = Dataproviders.class)
	
	public void runtests(String[] testData) throws InterruptedException {
		
		HoaactdPage hd= new HoaactdPage(driver);
		hd.menuSelect(testData[0]);
		hd.functionSelect(testData[1]);
		hd.enterCif(testData[2]);
		hd.enterScheme(testData[3]);
		hd.genrlCode(testData[4]);
		hd.goButton();
		hd.Ittab();
		hd.scheme();
		hd.alertFunc();
		hd.depositPeriod(testData[5]);
		hd.depositAmount(testData[6]);
		hd.maturityDate(testData[7]);
		hd.radioSelect(testData[8]);
		hd.flowTab();
		Thread.sleep(2000);
		hd.rncTab();
		hd.rpdTab();
		Thread.sleep(2000);
		hd.submitButton();
		//Thread.sleep(2000);
		hd.accountNo();
		hd.logout();
	}

}
