package com.Automation.Test.KeywordDrivenFramework.executionEngine;

import java.lang.reflect.Method;

import com.Automation.Test.KeywordDrivenFramework.config.ActionKeywords;
import com.Automation.Test.KeywordDrivenFramework.utility.ExcelUtils;

public class DriverScript{ 

	//This is a class object, declared as 'public static'
	//So that it can be used outside the scope of main[] method
	public static ActionKeywords actionKeywords;
	public static String sActionKeyword;
	public static String userDir = System.getProperty("user.dir");

	//This is reflection class object, declared as 'public static'
	//So that it can be used outside the scope of main[] method
	public static Method method[];


	public static int iTestStep;
	public static int iTestLastStep;
	public static String sTestCaseID;
	public static String sRunMode;


	//Here we are instantiating a new object of class 'ActionKeywords'
	//	 public DriverScript() throws NoSuchMethodException, SecurityException{

	//		 actionKeywords = new ActionKeywords();

	//This will load all the methods of the class 'ActionKeywords' in it.
	//It will be like array of method, use the break point here and do the watch
	//		 method = actionKeywords.getClass().getMethods();
	//		 System.out.println(method);
	//	 }

	public static void main(String[] args) throws Exception {
		
		try{
		// Declaring the path of the Excel file with the name of the Excel file
		String sPath = userDir+"//src//test//java//com//Automation//Test//KeywordDrivenFramework//DataEngine//DataEngine.xlsx";

		// Here we are passing the Excel path and SheetName as arguments to connect with Excel file 
		ExcelUtils.setExcelFile(sPath, "Test_Steps");

		//Hard coded values are used for Excel row & columns for now
		//In later chapters we will replace these hard coded values with varibales
		//This is the loop for reading the values of the column 3 (Action Keyword) row by row
		/*for (int iRow=1;iRow<=13;iRow++){
			//Storing the value of excel cell in sActionKeyword string variable
			sActionKeyword = ExcelUtils.getCellData(iRow, 3);

			//A new separate method is created with the name 'execute_Actions'
			//You will find this method below of the this test
			//So this statement is doing nothing but calling that piece of code to execute
//			executeActions();

		}*/
		DriverScript runManager = new DriverScript();
		runManager.executeTestCase();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	private void executeTestCase() throws Exception {
		//This will return the total number of test cases mentioned in the Test cases sheet
		int iTotalTestCases = ExcelUtils.getRowCount("Test_Cases");
		//This loop will execute number of times equal to Total number of test cases
		for(int iTestcase=1;iTestcase<=iTotalTestCases;iTestcase++){
			//This is to get the Test case name from the Test Cases sheet
			sTestCaseID = ExcelUtils.getCellData(iTestcase, 0, "Test_Cases"); 
			//This is to get the value of the Run Mode column for the current test case
			sRunMode = ExcelUtils.getCellData(iTestcase, 2,"Test_Cases");
			//This is the condition statement on RunMode value
			if (sRunMode.equals("Yes")){
				//Only if the value of Run Mode is 'Yes', this part of code will execute
				iTestStep = ExcelUtils.getRowContains(sTestCaseID, 0,"Test_Steps");
				iTestLastStep = ExcelUtils.getTestStepsCount("Test_Steps", sTestCaseID, iTestStep);
				//This loop will execute number of times equal to Total number of test steps
				for (;iTestStep<=iTestLastStep;iTestStep++){
					sActionKeyword = ExcelUtils.getCellData(iTestStep,3,"Test_Steps");
//					sPageObject = ExcelUtils.getCellData(iTestStep, , "Test_Steps");
					executeActions();
				}
			}
		}
	}


	private static void executeActions() throws Exception {
		actionKeywords = new ActionKeywords();
		method = actionKeywords.getClass().getMethods();
		//This is a loop which will run for the number of actions in the Action Keyword class 
		//method variable contain all the method and method.length returns the total number of methods
		//		 System.out.println(method.length);
		for(int i = 0;i < method.length;i++){

			//This is now comparing the method name with the ActionKeyword value got from excel
			if(method[i].getName().equals(sActionKeyword)){

				//In case of match found, it will execute the matched method
				method[i].invoke(actionKeywords);

				//Once any method is executed, this break statement will take the flow outside of for loop
				break;
			}
		}
	}

}
