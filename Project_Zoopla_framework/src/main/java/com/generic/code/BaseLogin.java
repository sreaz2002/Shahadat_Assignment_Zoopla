package com.generic.code;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import com.config.BaseConfig;
import com.page.object.model.LoginPage;
import com.util.Wait;
import com.util.Highlighter;
import com.util.ScreenShot;

public class BaseLogin {
	
	
	public static void getLogin() throws Throwable {
		
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,"true");
		Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
		WebDriver driver = new ChromeDriver();
		LoginPage login = new LoginPage(driver);
		
		driver.manage().window().maximize();
		driver.get(BaseConfig.getConfig("URL"));
				
		login.getAcceptCookie().click();

		//login.getSignInBtn().sendKeys(BaseConfig.getConfig("userName"));
		Highlighter.getcolor(driver, login.getSignInBtn());				
		new ScreenShot().getScreenShot(driver, "SignInPage");
		login.getSignInBtn().click();
		
//		new ExplicitWait().getExplicitWait(driver,By.xpath("//input[@id='signin_email']"));
		new Wait().getExplicitWait(driver, login.getEmail());
		
		login.getEmail().sendKeys(BaseConfig.getConfig("userName"));
		Highlighter.getcolor(driver, login.getEmail(),"red");
				
		login.getPassWord().sendKeys(BaseConfig.getConfig("passWord"));
		Highlighter.getcolor(driver, login.getPassWord(),"blue");		
				
		Highlighter.getcolor(driver, login.getSubmitBtn());		
		new ScreenShot().getScreenShot(driver, "Login Page");
		login.getSubmitBtn().click();
		System.out.println("Title of the Page is: "+driver.getTitle());
		new ScreenShot().getScreenShot(driver, "Home Page");
		Thread.sleep(3000);
		driver.quit();
		
	}	

}
