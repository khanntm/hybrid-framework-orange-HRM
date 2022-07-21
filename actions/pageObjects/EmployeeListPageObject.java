package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class EmployeeListPageObject extends BasePage {
	private WebDriver driver;

	public EmployeeListPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToEmployeeNameTextbox(String string) {
		// TODO Auto-generated method stub
		
	}

	public boolean isEmployeeInfoDisplayedAtResultTable(String string, String string2, String string3) {
		// TODO Auto-generated method stub
		return false;
	}
}
