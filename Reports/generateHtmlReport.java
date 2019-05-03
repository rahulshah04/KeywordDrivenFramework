package com.Automation.Test.KeywordDrivenFramework.Reports;

import java.io.File;

import org.testng.ITestResult;

import com.Automation.Test.KeywordDrivenFramework.utility.ConfigFileReader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class generateHtmlReport{
	
	static ExtentTest logger;
	static ExtentReports report;
	
	public static ExtentTest generateReport(String testName, String Description) {
		report = new ExtentReports(System.getProperty("user.dir")+"/target/HTMLExtentReport.html");
		report.loadConfig(new File(ConfigFileReader.getData("reportConfigPath")));
		report.addSystemInfo("User Name", System.getProperty("user.name"));
		report.addSystemInfo("Time Zone", System.getProperty("user.timezone"));
		report.addSystemInfo("Machine", 	"Windows 10" + "64 Bit");
		report.addSystemInfo("Selenium", "3.11.0");
		report.addSystemInfo("Maven", "3.5.2");
		report.addSystemInfo("Java Version", "1.8.0_131");
		
		logger = report.startTest(testName, Description);
//		logger = report.startTest("Mercury Tours Login", "User should be able to book round trip ticket from Mecury Tours Website");
		return logger;
	}
	
	public static void getResult(ITestResult result){
		if(result.getStatus() == ITestResult.FAILURE){
			logger.log(LogStatus.FAIL, result.getName() + " - Test Case Failed");
			logger.log(LogStatus.FAIL, result.getThrowable() + " - Test Case Failed");
		}else if(result.getStatus() == ITestResult.SKIP){
			logger.log(LogStatus.SKIP, result.getName() + " - Test Case Skipped");	
		}
	}
	
	public static void flushReport(){
		report.endTest(logger);
		report.flush();
	}
}
