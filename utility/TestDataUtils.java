package com.Automation.Test.KeywordDrivenFramework.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataUtils {
	
	public static String cellKeyValue;
	
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	
	static String userDir = System.getProperty("user.dir");
	static String filePath = userDir+"/src/test/java/com/Automation/Test/KeywordDrivenFramework/DataEngine/RunManager.xlsx";

	public static String getExcelData(String cellKey)
	{
		try
		{
			FileInputStream file = new FileInputStream(new File(filePath));

			//Create Workbook instance holding reference to .xlsx file
			workbook = new XSSFWorkbook(file);

			//Get first/desired sheet from the workbook
			sheet = workbook.getSheetAt(0);

			//Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext())
			{
				Row row = rowIterator.next();

				//For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell = cellIterator.next();
				String cellValue = cell.toString();

				//compare the keyValue
				if(cellValue.equals(cellKey)){
					while (cellIterator.hasNext())
					{	
						Cell getCellValue = cellIterator.next();
						cellKeyValue = getCellValue.getStringCellValue();
					}
				}

			}

			file.close();
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return cellKeyValue;
	}
	
	public static void getTestData(String cellData){
		try {
	        FileInputStream file = new FileInputStream(new File(filePath));

	        //Create Workbook instance holding reference to .xlsx file
	        workbook = new XSSFWorkbook(file);

	        //Get first/desired sheet from the workbook
	        sheet = workbook.getSheetAt(1);

	        //Iterate through each rows one by one
	        Iterator<Row> rowIterator = sheet.iterator();
//	        while (rowIterator.hasNext())
//	        {
	            Row row = rowIterator.next();
	            //For each row, iterate through all the columns
	            Iterator<Cell> cellIterator = row.cellIterator();

	            while (cellIterator.hasNext()) 
	            {
	                Cell cell = cellIterator.next();
	                System.out.println(cell);
	                //Check the cell type and format accordingly
//	                switch (cell.getCellType()) 
//	                {
//	                    case Cell.CELL_TYPE_NUMERIC:
//	                        System.out.print(cell.getNumericCellValue() + "\t");
//	                        break;
//	                    case Cell.CELL_TYPE_STRING:
//	                        System.out.print(cell.getStringCellValue() + "\t");
//	                        break;
//	                }
//	            }
	            System.out.println("");
	        }
	        file.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	

}
