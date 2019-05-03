package com.Automation.Test.KeywordDrivenFramework.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ConfigFileReader {
	
	private static Properties properties;
	private final String propertyFilePath= "Configs//Configuration.properties";


	public ConfigFileReader(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}

	public static String getDriverPathChrome(){
		String driverPath = properties.getProperty("driverPathChrome");
		if(driverPath!= null) return driverPath;
		else throw new RuntimeException("Chrome driverPath not specified in the Configuration.properties file.");		
	}
	
	public String getDriverPathFirefox(){
		String driverPath = properties.getProperty("driverPathFirefox");
		if(driverPath!= null) return driverPath;
		else throw new RuntimeException("Firefox driverPath not specified in the Configuration.properties file.");		
	}

	public static long getImplicitlyWait() {		
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
		else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");		
	}

	public static String getApplicationUrl() {
		String url = properties.getProperty("url");
		if(url != null) return url;
		else throw new RuntimeException("url not specified in the Configuration.properties file.");
	}
	
	public Boolean getBrowserWindowSize() {
		String windowSize = properties.getProperty("windowMaximize");
		if(windowSize != null) return Boolean.valueOf(windowSize);
		return true;
	}
	
	public static String getData(String key){
		String value = null;
		try {
			File file = new File("./Configs/Configuration.properties");
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();

			Enumeration<Object> enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String keydata = (String) enuKeys.nextElement();
				if(keydata.toString().equals(key)){
					value= properties.getProperty(key);
					System.out.println(value);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

}
