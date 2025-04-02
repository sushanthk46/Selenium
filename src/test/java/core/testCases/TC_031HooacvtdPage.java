package core.testCases;

import org.testng.annotations.Test;

import core.utilities.Dataproviders;

public class TC_031HooacvtdPage {
	
	@Test(dependsOnMethods = "core.testCases.TC001_LoginPage.verifyLogin",dataProvider = "HOAACTDData", dataProviderClass = Dataproviders.class)
	
	public void runVerify() {
		
		System.out.println("Entered Verify");
	}

}
