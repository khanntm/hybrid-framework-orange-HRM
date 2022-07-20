package com.hrm.employee;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObjects.AddEmployeePageObject;
import pageObjects.DashboardPageObject;
import pageObjects.EmployeeListPageObject;
import pageObjects.LoginPageObject;
import pageObjects.PersonalDetailPageObject;
import pageObjects.pageGenerator;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Registration_And_Login extends BaseTest{
	String employeeID, statusValue;
	
	
	@Parameters({"envName","serverName","browser", "ipAddress", "portNumber", "osName", "osVersion"})
	 @BeforeClass 
	  public void beforeClass(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10")String osVersion) {
		log.info("PreCondition - Step 01: Open browser and navigate to '"+ envName + "'");
		driver = getBrowserDriver(envName, serverName, browserName, ipAddress, portNumber, osName, osVersion); 
		driver.manage().window().maximize();
		loginPage = pageGenerator.getLoginPage(driver);
		
		statusValue = "Enabled";
		
		log.info("Pre-Condition - Step 01: Login with Admin role");
		dashboardPage = loginPage.loginToSystem("Admin","admin123");
	 }

 @Test
  public void Employee_01_Add_New_Employee() {
	 log.info("Add_New_01 - Step 01: Open Employee List page");
	 dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
	 employeeListPage = pageGenerator.getEmployeeListPage(driver);
	 
	 log.info("Add_New_01 - Step 02: Click to 'Add' button");
	 addEmployeePage = employeeListPage.clickToAddButton();
	 
	 log.info("Add_New_01 - Step 03: Enter valid info to 'First Name' textbox");
	 addEmployeePage.enterToFirstNameTextbox("");
	 
	 log.info("Add_New_01 - Step 04: Enter valid info to 'Last Name' textbox");
	 addEmployeePage.enterToLastNameTextbox("");
	 
	 log.info("Add_New_01 - Step 05: Get value of 'Employee ID'");
	 employeeID = addEmployeePage.getEmployeeID();

	 log.info("Add_New_01 - Step 06: Click to 'Create Login Details' checkbox");
	 addEmployeePage.clickToCreateLoginDetailCheckbox();
	 
	 log.info("Add_New_01 - Step 07: Enter valid info to 'User Name' textbox");
	 addEmployeePage.enterToUserNameTextbox();

	 log.info("Add_New_01 - Step 08: Enter valid info to 'Password' textbox");
	 addEmployeePage.enterToPasswordTextbox();
	 
	 log.info("Add_New_01 - Step 09: Enter valid info to 'Confirm Password' textbox");
	 addEmployeePage.enterToConfirmPasswordTextbox();
	 
	 log.info("Add_New_01 - Step 10: Select '"+ statusValue +"' value in 'Status' dropdown");
	 addEmployeePage.selectValueInStatusDropdown(statusValue);

	 log.info("Add_New_01 - Step 11: Click to 'Save' button");
	 personalDetailPage = addEmployeePage.clickToSaveButton();
	 
	 log.info("Add_New_01 - Step 12: Open Employee List page");
	 personalDetailPage.openSubMenuPage(driver, "PIM", "Employee List");
	 employeeListPage = pageGenerator.getEmployeeListPage(driver);
	 
	 log.info("Add_New_01 - Step 13: Enter valid info to 'Employee Name' textbox");
	 employeeListPage.enterToEmployeeNameTextbox("");

	 log.info("Add_New_01 - Step 14: Click to 'Search' button");
	 employeeListPage.clickToSearchButton();

	 log.info("Add_New_01 - Step 15: Verify Employee Information displayed at 'Result Table'");
	 verifyTrue(employeeListPage.isEmployeeInfoDisplayedAtResultTable("","",""));
	 
  }
 
 @Test
 public void Employee_02_Upload_Avatar() {
	 
 }
 
 @Test
 public void Employee_03_Personal_Details() {
	 
 }
 
 @Test
 public void Employee_04_Contact_Details() {
	 
 }
 
 @Test
 public void Employee_05_Emergency_Details() {
	 
 }
 
 @Test
 public void Employee_06_Assigned_Dependents() {
	 
 }
 
 @Test
 public void Employee_07_Edit_View_Job() {
	 
 }
 
 @Test
 public void Employee_08_Edit_View_Salary() {
	 
 }
 
 @Test
 public void Employee_08_Edit_View_Tax() {
	 
 }
 @Test
 public void Employee_09_Edit_View_Tax() {
	 
 }
 
 @Test
 public void Employee_10_Qualifications() {
	 
 }
 
  @Test
  public void Employee_11_Search_Employee() {
	  
  }
 
 
  @AfterClass(alwaysRun = true)
 	public void afterClass() {
 	  closeBrowserAndDriver("local");
 	}
  
  	WebDriver driver;
  	AddEmployeePageObject addEmployeePage;
	DashboardPageObject dashboardPage;
	EmployeeListPageObject employeeListPage;
	LoginPageObject loginPage;
	PersonalDetailPageObject personalDetailPage;
	
}