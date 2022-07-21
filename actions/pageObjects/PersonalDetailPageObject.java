package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.PersonalDetailPageUI;

public class PersonalDetailPageObject extends BasePage {
	private WebDriver driver;

	public PersonalDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isUploadAvatarSuccessMessageDisplayed() {
		waitForElementVisible(driver, PersonalDetailPageUI.UPLOAD_AVATAR_SUCCESS_MESSAGE);
		return isElementDisplayed(driver, PersonalDetailPageUI.UPLOAD_AVATAR_SUCCESS_MESSAGE);
		
	}

	public void clickToChangePhotoImage() {
		waitForElementClickable(driver, PersonalDetailPageUI.AVATAR_IMAGE);
		clickToElement(driver, PersonalDetailPageUI.AVATAR_IMAGE);
		
	}

	public boolean isNewAvatarImageDisplayed() {
		waitForElementVisible(driver, PersonalDetailPageUI.AVATAR_IMAGE);
		int imageWidth = Integer.parseInt(getElementAttribute(driver, PersonalDetailPageUI.AVATAR_IMAGE, "width"));
		int imageHeight = Integer.parseInt(getElementAttribute(driver, PersonalDetailPageUI.AVATAR_IMAGE, "height"));
		return (imageWidth!=200) || (imageHeight!=200);
	}

	
}
