package com.rachid.testcases;

import org.testng.Assert;

import org.testng.annotations.Test;

import com.rachid.base.Base;

public class PracticePageTest extends Base {
	
	//Testing checkbox
	
	@Test(groups="Regression")
	public void checkboxTest() throws InterruptedException {
		
		
		click("practiceLink_CSS");
			
			click("checkbox_XPATH");
			Thread.sleep(3000);
		
	}
	
	@Test(groups="Regression")
	public void dropDownTest() throws InterruptedException {
		
		click("dropdown_XPATH");
		select("dropdown_XPATH","Option3");
		Thread.sleep(3000);
		
	}
	
	@Test(groups="Regression")
	public void radioButnTest() throws InterruptedException {
		
		
		click("radiobtn_CSS");
		type("alertbox_CSS","Rachid");
		click("alertbtn_ID");
		
		String alertText=driver.switchTo().alert().getText();
		if(alertText.contains("Rachid")) {
			System.out.println("Alert text verified");
		}else {
			System.out.println("Alert text NOT verified");
		}
		//Thread.sleep(3000);
		driver.switchTo().alert().accept();
		Assert.assertTrue(false);
	}
	
	
	
	

}
