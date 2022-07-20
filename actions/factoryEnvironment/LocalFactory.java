package factoryEnvironment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;

import factoryBrowser.BrowserList;
import factoryBrowser.BrowserNotSupportedException;
import factoryBrowser.ChromeDriverManage;
import factoryBrowser.EdgeDriverManager;
import factoryBrowser.FirefoxDriverManager;
import factoryBrowser.HeadlessChromeDriverManager;
import factoryBrowser.HeadlessFirefoxDriverManager;
import factoryBrowser.SafariDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.Architecture;
//import io.github.bonigarcia.wdm.managers.ChromeDriverManager;


public class LocalFactory {
	private String browserName;
	private WebDriver driver;
	public LocalFactory(String browserName) {
		this.browserName = browserName;
	}
	
	public WebDriver createDriver() {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
		
		switch (browser) {
		case FIREFOX:
			driver = new FirefoxDriverManager().getBrowserDriver();
			break;
			
		case CHROME:
			driver = new ChromeDriverManage().getBrowserDriver();
			break;
			
		case SAFARI:
			driver = new SafariDriverManager().getBrowserDriver();
			break;
			
		case EDGE_CHROMIUM:
			driver = new EdgeDriverManager().getBrowserDriver();
			break;
			
		case H_CHROME:
			driver = new HeadlessChromeDriverManager().getBrowserDriver();
			break;
			
		case H_FIREFOX:
			driver = new HeadlessFirefoxDriverManager().getBrowserDriver();
			break;

		default:
			throw new BrowserNotSupportedException(browserName) ;
		}
		
		 
		 return driver;
	}
	
}
