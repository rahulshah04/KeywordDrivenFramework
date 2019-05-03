package com.Automation.Test.KeywordDrivenFramework.config;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.Automation.Test.KeywordDrivenFramework.Reports.generateHtmlReport;
import com.Automation.Test.KeywordDrivenFramework.pages.FlightFinderPage;
import com.Automation.Test.KeywordDrivenFramework.pages.LoginPage;
import com.Automation.Test.KeywordDrivenFramework.utility.ConfigFileReader;
import com.Automation.Test.KeywordDrivenFramework.utility.TestDataUtils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class ActionKeywords {
	
	public static WebDriver driver;
	
	String User_dir = System.getProperty("user.dir"); 
	static ExtentTest reporter = generateHtmlReport.generateReport("Mercury Tour Login", TestDataUtils.getExcelData("Login_01"));
	
	 public static void openBrowser(){ 
		 System.setProperty("webdriver.chrome.driver", ConfigFileReader.getData("driverPathChrome"));
		 driver = new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 reporter.log(LogStatus.INFO, "User is able to enter URL");
		 
	 }
	 
	 public static void navigate(){
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.get(ConfigFileReader.getData("url"));
	 }
	 
	 public static void verifyUserOnLoginPage(){
		 WebElement flightFinderLogo = driver.findElement(LoginPage.findflight);
		 Assert.assertTrue(flightFinderLogo.isDisplayed(), "User is not on Login Page");
		 reporter.log(LogStatus.PASS,"User is on Login Page");
	 }
	 
	 public static void inputUsername(){
		 driver.findElement(LoginPage.userName).sendKeys(ConfigFileReader.getData("username"));
		 reporter.log(LogStatus.PASS, "UserName is entered");
	 }
		 
	 public static void inputPassword(){
		 driver.findElement(LoginPage.password).sendKeys(ConfigFileReader.getData("password"));
		 reporter.log(LogStatus.PASS, "Password is entered");
	 }
		 
	 public static void clickLoginButtn(){
		 driver.findElement(LoginPage.LogInBttn).click();
		 reporter.log(LogStatus.PASS,"User is on Flight Finder Launch Page");
	 }
	 
	 public static void selectRoundTrip(){
		 WebElement tripTypeRound = driver.findElement(FlightFinderPage.tripTypeRadioBttn);
			if(tripTypeRound.isSelected()) {
				System.out.println("Round Trip is selected");
				reporter.log(LogStatus.PASS, "Round Trip is selected");
				WebElement passengerCount = driver.findElement(FlightFinderPage.passengerCount);
				Select oselectPassCounty = new Select(passengerCount);
				oselectPassCounty.selectByIndex(0);
				reporter.log(LogStatus.PASS,"Passenger Country Dropdown Option is selected");
			}
			else {
				tripTypeRound.click();
				reporter.log(LogStatus.INFO,"Round Trip is clicked");
				WebElement passengerCount = driver.findElement(FlightFinderPage.passengerCount);
				Select oselectPassCounty = new Select(passengerCount);
				oselectPassCounty.selectByIndex(0);
				reporter.log(LogStatus.PASS,"Passenger Count Option is selected");
			}
	 }
	 
	 public static void selectDepartingFromOption(){
		WebElement departingFrom = driver.findElement(FlightFinderPage.departingFromdrpdwn);
		Select oselectDepartingCountry = new Select(departingFrom);
		oselectDepartingCountry.selectByIndex(1);
		System.out.println("Departing From dropdown is selected");
		Assert.assertTrue(departingFrom.isDisplayed(),"Departing From dropdown is selected");
		reporter.log(LogStatus.PASS,"Departing From dropdown is selected");
	 }
	 
	 public static void selectArrivingToOption(){
		WebElement arrivingTo = driver.findElement(FlightFinderPage.arrivingTodrpdwn);
		Select oselectArrivingCountry = new Select(arrivingTo);
		oselectArrivingCountry.selectByIndex(2);
		System.out.println("Arriving To dropdown is selected");
		Assert.assertTrue(arrivingTo.isDisplayed(), "Arriving In dropdown is selected");
		reporter.log(LogStatus.PASS,"Arriving In dropdown is selected");
//		reporter.log(LogStatus.PASS, "Arriving In dropdown is selected", t);
	 }
	 
	 public static void selectEconomyClassRadioButton(){
		WebElement serviceClass = driver.findElement(FlightFinderPage.serviceClassType);
		if(serviceClass.isSelected()) {
			System.out.println("Economy Class Radio Button is selected");
			reporter.log(LogStatus.PASS,"Economy Class Radio Button is selected");
		}
		else {
			serviceClass.click();
			System.out.println("Economy Class Radio Button is clicked");
			reporter.log(LogStatus.PASS,"Economy Class Radio Button is clicked");
		}
	 }
	 
	 public static void selectContinueButton(){
		WebElement continueButton = driver.findElement(FlightFinderPage.continueBttn);
		continueButton.click();
		System.out.println("Continue Button is clicked");
		reporter.log(LogStatus.PASS,"Continue Button is clicked");
	 }
	 
	 public static void closeBrowser(){
//		 generateHtmlReport.getResult();
		 generateHtmlReport.flushReport();
		 driver.close();
		 driver.quit();
	}
	 

}

