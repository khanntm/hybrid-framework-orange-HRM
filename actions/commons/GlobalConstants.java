package commons;

import java.io.File;

import lombok.Getter;
import lombok.Setter;

@Getter

public class GlobalConstants {
	
	private static GlobalConstants globalInstance;
	
	private GlobalConstants() {
		//Apply Singleton by creating private constructor this way not allow another object can NOT access directly or through an Object.
		//Access by new an Object by getGlobalConstants() 
	}
	
	public static synchronized GlobalConstants getGlobalConstants() {
		if(globalInstance==null) {
			globalInstance = new GlobalConstants();
		}
		return globalInstance;
	}
	
	/* Using lombok library to auto generate getter/ setter auto
	public long getShortTimeout() {
		return shortTimeout();
	} */ 
	
	private final String projectPath = System.getProperty("user.dir");
	private final String osName= System.getProperty("os.name");
	private final String javaVersion = System.getProperty("java.version");
	
	//With this format File.separator can run on all platform Windown - Linux - Mac	
	private final String uploadFile = projectPath + File.separator + "uploadFiles" + File.separator;
	
	//Return default download folder of user e.g: Download  
	private final String downloadFile = projectPath + File.separator + "downloadFiles";
	
	//Browser log
	private final String browserLog = projectPath + File.separator + "browserLogs" + File.separator;
	
	private final String dragDropHtml5 = projectPath + File.separator + "dragDropHTML5";
	
	private final String reportingScreenshot = projectPath + File.separator + "reportNGImages" + File.separator;
	
	private final String autoITScript = projectPath + File.separator + "autoIT";
	
	private final long shortTimeout = 5;
	private final long longTimeout = 10;
	private final long retryTestFail = 3;
	
	//For Browserstack config
	private final String broswerUsername = "minhkhannguyenth_36jPvN";
	private final String browserAutomateKey = "jpvxfdFtDpABm6JCUp4C";
	private final String browserStackUrl = "https://" + broswerUsername + ":" + browserAutomateKey + "@hub-cloud.browserstack.com/wd/hub";
	
	//SauceLab config
	private final String sauceUserName = "oauth-khanntm-04168";
	private final String sauceAutomateKey = "f803253c-31ac-448d-ab9d-17122f8c1472";
	private final String sauceUrl = "https://" + sauceUserName + ":" + sauceAutomateKey + "@ondemand.apac-southeast-1.saucelabs.com:443/wd/hub";
	
	//Crossbrowser config
	private final String crossUsername = "".replace("@", "%40");
	private final String crossAutomateKey = "";
	private final String crossUrl = "http://" + crossUsername + ":" + crossAutomateKey + "@...";
	
	//Lamda Test config
	private final String lambdaUsername = "";
	private final String lambdaAutomateKey = "";
	private final String lambdaUrl = "https://" + lambdaUsername + ":" + lambdaAutomateKey + "@...";
}
