package commons;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.DashboardPageObject;
import pageObjects.LoginPageObject;
import pageObjects.pageGenerator;
import pageUIs.BasePageUI;
import pageUIs.MyInfoPageUI;

public class BasePage {
	
	public static BasePage getBasePageObject() {
		return new BasePage();
	}
	
	
	public void openPageUrl(WebDriver driver, String pageUrl)
	{
		driver.get(pageUrl);
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}	
	
	public Set<Cookie> getAllCookies (WebDriver driver) {
		return driver.manage().getCookies();
	}
	
	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie); 
		}
		sleepInSecond(3);
	}
	
	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}	
	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	public String getAlertText(WebDriver driver) {
		return  waitForAlertPresence(driver).getText();
	}
	
	public void sendkeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}
	
	public void switchToWindowByID(WebDriver driver, String windowPageID) {
		 Set<String> windowPageIDList = driver.getWindowHandles();
		
		 for (String eachWindow : windowPageIDList) {
			if (!eachWindow.equals(windowPageID)) {
				driver.switchTo().window(eachWindow);
				break;
			}
		}
		
	}
	
	public void swithToWindowByTitle(WebDriver driver, String expectedTitlePage) {
		 Set<String> windowPageIDList = driver.getWindowHandles();
		 for (String eachWindow : windowPageIDList) {
			driver.switchTo().window(eachWindow);
			 String actualPageTitle = driver.getTitle().trim();
			System.out.println(actualPageTitle);
			if(actualPageTitle.equals(expectedTitlePage)) {
				break;
			 }
		   }
		}
	
	public void closeAllWindowWithoutParent(WebDriver driver, String parentPageID) {
		 Set<String> windowPageIDList = driver.getWindowHandles();
		
		 for (String eachWindow : windowPageIDList) {
			 if(!eachWindow.equals(parentPageID)) {
				driver.switchTo().window(eachWindow);
			 	driver.close();
			 }
			 driver.switchTo().window(parentPageID);
		   }
	}
	
		public By getByLocator(String locatorType) {
		By by = null;
		System.out.println("Locattor Type = " + locatorType);
		if (locatorType.startsWith("id=") || locatorType.startsWith("Id=") || locatorType.startsWith("ID=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("class=") || locatorType.startsWith("Class=") || locatorType.startsWith("CLASS=")) {
			by = By.className(locatorType.substring(6));
		} else if (locatorType.startsWith("name=") || locatorType.startsWith("Name=") || locatorType.startsWith("NAME=")) {
			by = By.name(locatorType.substring(5));
		} else if (locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")) {
			by = By.cssSelector(locatorType.substring(4));
		}else if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("XPath=")) {
			by = By.xpath(locatorType.substring(6));
		} else {
			throw new RuntimeException("Locator is not support");
		}
		
		return by;
	}
	
	public String getDynamicXpath(String locatorType, String...dynamicValues) {
		System.out.println("Locator Type efore =" + locatorType);
		if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("XPath=")) {
			locatorType = String.format(locatorType, (Object[])	dynamicValues);
		}
		for (String value : dynamicValues) {
			System.out.println("Values map to locator = "+ value);
		}
		
		System.out.println("Locator Type After = " + locatorType);
		return locatorType;
	}
	
	public WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}
	
	public List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}
	
	public void clickToElement(WebDriver driver, String locatorType) {
		hightlightElement(driver, locatorType);
		if(driver.toString().contains("internet explore")) {
			clickToElementByJS(driver, locatorType);
			
			sleepInSecond(2);
		} else {
			getWebElement(driver, locatorType).click(); 
		}
	}
	
	public void clickToElement(WebDriver driver, String locatorType, String...dynamicValues) {
		hightlightElement(driver, locatorType, dynamicValues);
		 	if(driver.toString().contains("internet explore")) {
			clickToElementByJS(driver, getDynamicXpath(locatorType, dynamicValues));
			sleepInSecond(2);
		} else {
			 
			getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click(); 
		}
		
	}
	
	public void sendkeyToElement(WebDriver driver, String locatorType, String textValues) {
		hightlightElement(driver, locatorType);
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValues);
	}
	
	public void sendkeyToElement(WebDriver driver, String locatorType, String textValues, String...dynamicValues) {
		hightlightElement(driver, locatorType, dynamicValues);
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValues);
	}
	
	public String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText().trim();
	}
	
	public String getElementText(WebDriver driver, String locatorType, String...dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText().trim();
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByVisibleText(textItem);
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		select.selectByVisibleText(textItem);
	}
	
	public String getSelectedItemDefaultDropdown(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.getFirstSelectedOption().getText();
	}
	
	public String getSelectedItemDefaultDropdown(WebDriver driver, String xpathLocator, String...dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdownMultiple(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.isMultiple();
	}
	
	public void sleepInSecond(long second)
	{
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			 
			e.printStackTrace();
		}
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedItemText) {
		 		getWebElement(driver, parentXpath).click();
				sleepInSecond(2);
				
				WebDriverWait explicit = new WebDriverWait(driver, longTimeout);
				
			 	explicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
				
				 List<WebElement> childItems = driver.findElements(By.xpath(childXpath));
				  
				 for (WebElement tempElement : childItems) {
					 
				 	if (tempElement.getText().trim().equals(expectedItemText)) {
					 	if (tempElement.isDisplayed()) {
							 
							tempElement.click();
							sleepInSecond(1);
						} else {
							JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
						 	 
							jsExecutor.executeScript("arguments[0].scrollIntoView(true)", tempElement);
							sleepInSecond(1);
						 	jsExecutor.executeScript("arguments[0].click();", tempElement);
							sleepInSecond(1);
						}
						 break;
					}
				}

	}
	
	public String getElementAttribute (WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}
	
	public String getElementAttribute (WebDriver driver, String locatorType, String attributeName, String... dynamicValues) {
		return getWebElement(driver,  getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
	}
	
	public String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	} 
	
	public String getHexaColorFromRGBR(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	public int getElementSize(WebDriver driver, String locatorType) {
		return getListWebElement(driver, locatorType).size();
	}
	
	public int getElementSize(WebDriver driver, String locatorType, String... dynamicValues) {
		return getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).size();
	}

	public void checkToDefaultCheckboxOrRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public void checkToDefaultCheckboxOrRadio(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver,  getDynamicXpath(locatorType, dynamicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckToDefaultCheckbox(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckToDefaultCheckbox(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locatorType) {
		try {
			 
			return getWebElement(driver, locatorType).isDisplayed();	
		} catch (Exception e) {
			 
			return false;
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	}
	
	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, shortTimeout); 
		List<WebElement> elements = getListWebElement(driver, locator);	
		 
		overrideGlobalTimeout(driver, longTimeout);
		
		if(elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public void overrideGlobalTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}
	
	
	public boolean isElementEnabled(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isEnabled();
	}
	
	
	public boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}
	
	public boolean isElementSelected(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isSelected();
	}
	
	public void switchToFrameIframe(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}
	
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}
	
	public void hoverMouseToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues))).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locatorType), key).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String...dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)), key).perform();
	}
	
	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void hightlightElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}
	public void hightlightElement(WebDriver driver, String locatorType, String...dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}
	
	public void clickToElementByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}
	
	public void scrollToElementOnTop(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}
	
	public void scrollToElementOnDown(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locatorType));
	}
	
	public String getElementValueByJSXpath(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		xpathLocator = xpathLocator.replace("xpath", "");
		return (String) jsExecutor.executeScript("return $(document.evaluate(\"" + xpathLocator + "\", document, null, XpathResult.FIRST_ORDERED_NOTE_TYPE, null).singleNodeValue).val()");
	}
	
	public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
	}
	
	public String getElementValidationMessage(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
	}

	public boolean isImageLoaded(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getWebElement(driver, locatorType));
		if (status) {
			return true;
		}
		return false;
	}
	
	public boolean isImageLoaded(WebDriver driver, String locatorType, String...dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		
		return status;
	}
		
	public boolean isJQueryAjaxLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait;
		JavascriptExecutor jsExecutor;
		explicitWait = new WebDriverWait(driver, longTimeout);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active == 0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}

	
	public void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}
	
	public void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void waitForAllElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}
	
	public void waitForAllElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}
	
	public void waitForElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}
	/** 
	 * Wait for element un-displayed in DOM or not in DOM and override implicit timeout
	 * */
	public void waitForElementUndisplayed(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideGlobalTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		overrideGlobalTimeout(driver, longTimeout);
		
	}
	
	public void waitForElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void waitForAllElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));
	}
	public void waitForElementClickable(WebDriver driver, String locatorType) {
		System.out.println("Driver at wait for element  = " + driver.toString());
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}
	
	public void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void uploadSingleFile(WebDriver driver, String filePath) {	 
		getWebElement(driver, BasePageUI.UPLOAD_FILE).sendKeys(filePath);
	}
	
	/**
	 * Click to menu by Name text dynamic
	 * @param driver
	 * @param menuPageName
	 * @author Khan Nguyen
	 */	
	public void openMenuPage(WebDriver driver, String menuPageName) {
		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		isJQueryAjaxLoadedSuccess(driver);
	}
		
	public void openSubMenuPage(WebDriver driver, String menuPageName, String subMenuPageName) {
		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		
		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
		isJQueryAjaxLoadedSuccess(driver);
	}
	
	public void openChildSubMenuPage(WebDriver driver, String menuPageName, String subMenuPageName, String childSubMenuPageName) {
		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		
		waitForElementVisible(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
		hoverMouseToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
		
		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, childSubMenuPageName);
	    clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, childSubMenuPageName);
	    isJQueryAjaxLoadedSuccess(driver);
	}
	
	/**
	 * Click to button by ID dynamic
	 * @param driver
	 * @param buttonIDName
	 * @author Khan Nguyen
	 */
	public void clickToButtonByID(WebDriver driver, String buttonIDName) {
		waitForElementClickable(driver, BasePageUI.BUTTON_BY_ID, buttonIDName);
		clickToElement(driver, BasePageUI.BUTTON_BY_ID , buttonIDName);
	}

	/**
	 * Enter to text box by ID Dynamic
	 * @param driver
	 * @param textboxIDName
	 * @param valueInput
	 * @author Khan Nguyen
	 */
	public void enterToTextboxByID(WebDriver driver, String textboxIDName, String valueInput) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxIDName);
		sendkeyToElement(driver, BasePageUI.TEXTBOX_BY_ID, valueInput, textboxIDName);
	}
	
	
	/**
	 * Get value of a textbox by Dynamic ID 
	 * @param driver
	 * @param textboxIDName
	 * @return value of textbox by "value" attribute 
	 * @author Khan Nguyen
	 */
	public String getTextboxValueByID(WebDriver driver, String textboxIDName) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxIDName);
		return getElementAttribute(driver, BasePageUI.TEXTBOX_BY_ID, "value", textboxIDName);
	}
	
	/** Select value in dropdown by dynamic ID
	 * @param driver
	 * @param dropDownID
	 * @param valueItem
	 * @author Khan Nguyen
	 */
	public void selectItemInDropDownByID(WebDriver driver, String dropDownID, String valueItem) {
		waitForElementClickable(driver, BasePageUI.DROPWDOWN_BY_ID, dropDownID);
		selectItemInDefaultDropdown(driver, BasePageUI.DROPWDOWN_BY_ID, valueItem, dropDownID);
	}
	
	/** Get selected value in dropdown by ID
	 * @param driver
	 * @param dropDownID
	 * @return selected text in dropdown
	 * @author Khan Nguyen
	 */
	public String getSelectedValueInDropDownByID(WebDriver driver, String dropDownID) {
		waitForElementVisible(driver, BasePageUI.DROPWDOWN_BY_ID, dropDownID);
		return getSelectedItemDefaultDropdown(driver, BasePageUI.DROPWDOWN_BY_ID ,dropDownID);	
	}
	
	/**
	 * Click to checkbox by label text
	 * @param driver
	 * @param checkboxLabelName
	 * @author Khan Nguyen
	 */
	public void clickToCheckboxByLable(WebDriver driver, String checkboxLabelName) {
		waitForElementClickable(driver, BasePageUI.CHECKBOX_BY_LABEL, checkboxLabelName);
		checkToDefaultCheckboxOrRadio(driver, BasePageUI.CHECKBOX_BY_LABEL, checkboxLabelName);
	}
	
	/**
	 * Click to radio button by label text
	 * @param driver
	 * @param radioLabelName
	 * @author Khan Nguyen
	 */
	public void clickToRadioByLable(WebDriver driver, String radioLabelName) {
		waitForElementClickable(driver, BasePageUI.RADIO_BY_LABEL, radioLabelName);
		checkToDefaultCheckboxOrRadio(driver, BasePageUI.RADIO_BY_LABEL, radioLabelName);
	}
	
	public String getValueInTableIDAtColumnNameAndRowIndex(WebDriver driver, String tableID, String headerName, String rowIndex) {
		int columnIndex = getElementSize(driver, BasePageUI.TABLE_HEADER_BY_ID_AND_NAME, tableID, headerName) + 1;
		waitForElementVisible(driver, BasePageUI.TABLE_ROW_BY_COLUMN_AND_ROW_INDEX, tableID, rowIndex, String.valueOf(columnIndex));
		return getElementText(driver, BasePageUI.TABLE_ROW_BY_COLUMN_AND_ROW_INDEX, tableID, rowIndex, String.valueOf(columnIndex));
	}
	
	public DashboardPageObject loginToSystem(WebDriver driver, String userName, String password) {
		waitForElementVisible(driver, BasePageUI.USER_LOGIN_TEXTBOX);
		sendkeyToElement(driver, BasePageUI.USER_LOGIN_TEXTBOX, userName);
		sendkeyToElement(driver, BasePageUI.PASSWORD_LOGIN_TEXTBOX, password);
		clickToElement(driver, BasePageUI.LOGIN_BUTTON);
		return pageGenerator.getDashboardPage(driver);
		
	}
	
	public LoginPageObject logoutToSystem(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.WELCOME_USER_LINK);
		clickToElement(driver, BasePageUI.WELCOME_USER_LINK);
		
		waitForElementClickable(driver, BasePageUI.LOGOUT_LINK);
		clickToElement(driver, BasePageUI.LOGOUT_LINK);
		
		return pageGenerator.getLoginPage(driver);
		
	}
	
	public boolean isSuccessMessageDisplayed(WebDriver driver, String messageValue) {
		waitForElementVisible(driver, BasePageUI.SUCCESS_MESSAGE_VALUE, messageValue);
		return isElementDisplayed(driver, BasePageUI.SUCCESS_MESSAGE_VALUE, messageValue);
		
	}
	
	public boolean isFieldEnabledByName(WebDriver driver, String fieldID) {
		waitForElementVisible(driver, BasePageUI.ANY_FIELD_BY_ID, fieldID);
		return isElementEnabled(driver, BasePageUI.ANY_FIELD_BY_ID, fieldID);
	}
	
	public boolean isRadioButtonSelectedByLabel(WebDriver driver, String labelName) {
		waitForElementVisible(driver, BasePageUI.RADIO_BY_LABEL, labelName);
		return isElementSelected(driver, BasePageUI.RADIO_BY_LABEL, labelName);
	}
	
	public boolean isCheckboxSelectedByLabel(WebDriver driver, String labelName) {
		waitForElementVisible(driver, BasePageUI.CHECKBOX_BY_LABEL, labelName);
		return isElementSelected(driver, BasePageUI.CHECKBOX_BY_LABEL, labelName);
	}
	
	
	
	private long longTimeout = GlobalConstants.getGlobalConstants().getLongTimeout();
	private long shortTimeout = GlobalConstants.getGlobalConstants().getShortTimeout();
	
}
 
