package core.testCases;

import org.testng.annotations.Test;

import core.pageObject.LoginPage;
import core.utilities.Dataproviders;

public class TC001_LoginPage extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass= Dataproviders.class)
	public void verifyLogin(String uname, String pwd,String res) {
	
	try {
	LoginPage lp= new LoginPage(driver);
	System.out.println("uname" +uname);
	lp.txtUser(uname);
	lp.txtPwd(pwd);
	lp.btnClick();
	}
	catch(Exception e) {
		System.out.println(e);
	}
}
}
