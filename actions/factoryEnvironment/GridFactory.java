package factoryEnvironment;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import factoryBrowser.BrowserList;

import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GridFactory {
	private WebDriver driver;
	private String browserName;
	private String ipAddress;
	private String portNumber;
	
	public GridFactory(String browserName, String ipAddress, String portNumber) {
		this.browserName = browserName;
		this.ipAddress = ipAddress;
		this.portNumber = portNumber;
	}
	
	public WebDriver createDriver() {
		
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		DesiredCapabilities capability = null;
		
		 if(browserList == BrowserList.FIREFOX) {
			 WebDriverManager.firefoxdriver().setup();
			 capability = DesiredCapabilities.firefox();
			 capability.setBrowserName("firefox");
			 capability.setPlatform(Platform.WINDOWS);
				
			 FirefoxOptions options = new FirefoxOptions();
			 options.merge(capability);
		 } 
		 else if (browserList == BrowserList.H_FIREFOX) {
			 WebDriverManager.firefoxdriver().setup();
			 FirefoxOptions options = new FirefoxOptions();
			 options.addArguments("--headless");
			 options.addArguments("window-size=1920*1080");
			 driver = new FirefoxDriver(options);
		 }
		 
		 else if(browserList == BrowserList.CHROME) {
			 WebDriverManager.chromedriver().setup();
			 ChromeOptions options = new ChromeOptions();
			 capability = DesiredCapabilities.chrome();
			 capability.setBrowserName("chrome");
			 capability.setPlatform(Platform.WINDOWS);				
			 options.merge(capability);
				
		 }
		 else if(browserList == BrowserList.H_CHROME) {
			 WebDriverManager.chromedriver().setup();
			 ChromeOptions options = new ChromeOptions();
			 options.addArguments("-headless");
			 options.addArguments("window-size=1920*1080");
			 driver = new ChromeDriver(options);
		 }
		 else if(browserList == BrowserList.OPERA) {
			 WebDriverManager.operadriver().setup();
			 driver = new OperaDriver();
		 }
		 else if(browserList == BrowserList.EDGE_CHROMIUM) {
			 WebDriverManager.edgedriver().setup();
			 driver = new EdgeDriver();
		 } else {
			 throw new RuntimeException("Browser name invalid.");
		 }
		 
		 try {
				driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/wd/hub", ipAddress, portNumber)), capability);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		
		return driver;
	}
 }
