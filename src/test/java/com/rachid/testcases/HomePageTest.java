package com.rachid.testcases;

import java.awt.Window;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.rachid.base.Base;


public class HomePageTest extends Base {
	
	@Test(groups="Regression")
	public void homePageLogo()  {
		
		System.out.println("Inside home page test");
		isElementPresent(By.xpath(OR.getProperty("pageLogo_XPATH")));
	}
	@Test(groups="Regression")
	public void quickLinksTest() throws InterruptedException {
		
		System.out.println(linksCount("quiclinks_XPATH"));
		
		for(int i=0;i<linksCount("quiclinks_XPATH");i++) {
			
			elementTarget("quiclinksElement_XPATH");
			
		}
		Iterator<String> iterator=driver.getWindowHandles().iterator();
		//iterator.next();
		while(iterator.hasNext()) {
			String childwindow= iterator.next();
			 
			driver.switchTo().window(childwindow);
			System.out.println(driver.getTitle());
			driver.switchTo().defaultContent();
			
		}
		//Assert.fail();
	
	}

}
