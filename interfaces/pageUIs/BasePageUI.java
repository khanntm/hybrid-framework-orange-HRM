package pageUIs;

public class BasePageUI {
	public static final String MENU_BY_PAGE_NAME = "xpath=//div[@id='mainMenu']//a[string()='%s']";
	public static final String BUTTON_BY_ID = "xpath=//input[@id='%s']";
	public static final String TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
	public static final String DROPWDOWN_BY_ID = "xpath=//select[@id='%s']";
	public static final String CHECKBOX_BY_LABEL = "xpath=//label[text()='%s']/following-sibling::input";
	public static final String RADIO_BY_LABEL = "xpath=//label[text()='%s']/preceding-sibling::input";
	public static final String TABLE_HEADER_BY_ID_AND_NAME = "xpath=//table[@id='%s']//th[string()='%s']/preceding-sibling::th";
	public static final String TABLE_ROW_BY_COLUMN_AND_ROW_INDEX = "xpath=//table[@id='%s']/tbody/tr[%s]/td[%s]";
	
}
