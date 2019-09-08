package com.w2a.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

public class AddCustomerTest extends TestBase {
	
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void addCustomerTest(Hashtable<String,String> data) throws InterruptedException {
		
		if(!TestUtil.isTestRunnable("addCustomerTest", excel)) {
			throw new SkipException("Skipping the test"+ "addCustomerTest".toUpperCase()+ "as the run mode is No");
		}
		
		if(!data.get("Runmode").equalsIgnoreCase("Y")) {
			
			throw new SkipException("skipping the test case as the run mode is No");
		}
		click("addCusBtn_CSS");
		
		type("firstName_CSS",data.get("firstname"));
		type("lastName_XPATH",data.get("lastname"));
		type("postCode_CSS",data.get("postcode"));
		click("addbtn_CSS");
		
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("alertText")));
		
	
		alert.accept();
		//Assert.fail("customer not added successfull");
	}
	
	
}
