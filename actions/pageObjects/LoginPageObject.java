package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class LoginPageObject extends BasePage{
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
