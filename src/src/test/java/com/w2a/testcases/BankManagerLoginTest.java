package com.w2a.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

public class BankManagerLoginTest extends TestBase {
	
	@Test
	public void bankManagerLoginTest() throws InterruptedException, IOException
	{
		
		if(!TestUtil.isTestRunnable("bankManagerLoginTest", excel)) {
			throw new SkipException("Skipping the test"+ "bankManagerLoginTest".toUpperCase()+ "as the run mode is No");
		}
		verifyEquals("abc", "xzy");
		Thread.sleep(3000);
		log.debug("Inside LoginTest");
		click("bmlBtn_CSS");
		
		Assert.assertTrue( isElementPresent(By.cssSelector(OR.getProperty("addCusBtn_CSS"))),"Login not successfull");
		log.debug("Login successfully executed");
		
		//Assert.fail("login not successfull");
		
		
	}

}
