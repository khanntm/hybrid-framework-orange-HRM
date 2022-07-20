package factoryBrowser;

import java.util.Collections;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import commons.GlobalConstants;
//import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverManage implements BrowserFactory{

	@Override
	public WebDriver getBrowserDriver() {
		 WebDriverManager.chromedriver().setup();
		 ChromeOptions options = new ChromeOptions();
		 options.setExperimentalOption("useAutomationExtension", false);
		 options.setExperimentalOption("excludeSwitches", Collections.singleton("enable-automation"));
		 options.addArguments("--disable-notifications");
		 options.addArguments("--disable-geolocation");
		 
		 HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		 chromePrefs.put("profile.default_content_settings.popups", 0);
		 chromePrefs.put("download.default_directory", GlobalConstants.getGlobalConstants().getDownloadFile());
		 options.setExperimentalOption("prefs", chromePrefs);
		 
		 //options.AddArguments("--incognito");
		 
		 System.setProperty("webdriver.chrome.args", "--disable-logging");
		 System.setProperty("webdriver.chrome.silentOutput", "true");
		 
		 return new ChromeDriver(options);	
	}

}
