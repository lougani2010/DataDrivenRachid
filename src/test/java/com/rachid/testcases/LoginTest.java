package com.rachid.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.rachid.base.Base;
import com.rachid.utilities.TestUtil;


public class LoginTest extends Base {
	
	//Testing login functionality
	@Test(groups="Smoke",dataProviderClass=TestUtil.class,dataProvider= "dp")
	public void loginTest(Hashtable<String,String>data) {
		
		click("loginLink_CSS");
		type("emailAddress_ID",data.get("email"));
		type("password_ID",data.get("password"));
		click("loginBtn_XPATH");
		//logout
		click("logoutdropdown_XPATH");
		click("logoutlink_XPATH");
		//click on home tab link
		click("homelink_XPATH");
		
//		System.out.println(data.get("email"));
//		System.out.println(data.get("password"));
	}
	

}
