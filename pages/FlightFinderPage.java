package com.Automation.Test.KeywordDrivenFramework.pages;

import org.openqa.selenium.By;

public class FlightFinderPage {
	
	public static final By flightFinderCaption = By.xpath("//*[@src='/images/masts/mast_flightfinder.gif']");
	public static final By tripTypeRadioBttn = By.xpath("//*[@name='tripType' and @value='roundtrip']");
	public static final By passengerCount = By.xpath("//*[@name='passCount']");
	public static final By departingFromdrpdwn = By.xpath("//*[@name='fromPort']");
	public static final By arrivingTodrpdwn = By.xpath("//*[@name='toPort']");
	public static final By serviceClassType = By.xpath("//*[@name='servClass' and @value='Coach']");
	public static final By continueBttn = By.xpath("//*[@src='/images/forms/continue.gif']");

}