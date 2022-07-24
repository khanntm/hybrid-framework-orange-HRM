package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.MyInfoPageUI;

public class MyInfoPageObject extends BasePage {
	private WebDriver driver;

	public MyInfoPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToChangePhotoImage() {
		waitForElementClickable(driver, MyInfoPageUI.AVATAR_IMAGE);
		clickToElement(driver, MyInfoPageUI.AVATAR_IMAGE);
		
	}


	public boolean isNewAvatarImageDisplayed() {
		waitForElementVisible(driver, MyInfoPageUI.AVATAR_IMAGE);
		int imageWidth = Integer.parseInt(getElementAttribute(driver, MyInfoPageUI.AVATAR_IMAGE, "width"));
		int imageHeight = Integer.parseInt(getElementAttribute(driver, MyInfoPageUI.AVATAR_IMAGE, "height"));
		return (imageWidth != 200) || (imageHeight != 200);
	}

	public void openTabAtSideBarByName(String tabName) {
		waitForElementClickable(driver, MyInfoPageUI.TAB_LINK_AT_SIDEBAR, tabName);
		clickToElement(driver, MyInfoPageUI.TAB_LINK_AT_SIDEBAR, tabName);
	}

	public void enterValueToCommentTextArea(WebDriver driver, String empContactComment) {
		waitForElementVisible(driver, MyInfoPageUI.COMMENT_TEXT_AREA);
		sendkeyToElement(driver, MyInfoPageUI.COMMENT_TEXT_AREA, empContactComment);
		
	}
}
