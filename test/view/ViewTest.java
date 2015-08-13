package view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class ViewTest {

	private WebDriver driver;
	private Properties dbProperties = new Properties();

	protected static final String NAME_NORMAL_USER = "user1";
	protected static final String EMAIL_NORMAL_USER = "user1@gmail.com";
	protected static final String PASSWORD_NORMAL_USER = "password";

	// TODO you may have to modify these constants to the names used in your project
	protected static final String BASE_URL = "http://localhost:8080/r0457206_IP_SpringFrameworkApp-nyx/";
	protected static final String LOGIN_URL = BASE_URL;
	protected static final String REGISTER_URL = BASE_URL + "register.htm";
	protected static final String NAME_ADMIN = "user2";
	protected static final String PASSWORD_ADMIN = "password";
	protected static final String EMAIL_ADMIN = "user2@gmail.com";

	public ViewTest() {
		super();

		dbProperties.setProperty("url", "jdbc:postgresql://generalpostgresql.chjevgeydfg5.eu-west-1.rds.amazonaws.com:5432/generalPostgreSQL?user=tempdummy2&password=dummyPassword");
		//dbProperties.setProperty("user", "daily.build");
		//dbProperties.setProperty("password", "irooZiNgae0daiba");
		//dbProperties.setProperty("ssl", "true");
		//dbProperties.setProperty("sslfactory","org.postgresql.ssl.NonValidatingFactory");
	}
	
	public void setUp() {
		setDriver(new FirefoxDriver());
                //setDriver(new HtmlUnitDriver());
	}
	
	public void clean(String... userToDelete) {
		getDriver().quit();
		
		// TODO there are better ways to clean your database after the test, but
		// for now this will do...
                /*
		for (String email : userToDelete){
			UserSystem service = new UserSystem(getDbProperties(), getDbProperties());
			service.delete(email);
		}
                        */
	}

	protected void fillInName(String name) {
		WebElement nameField = driver.findElement(By.id("name"));
		nameField.clear();
		nameField.sendKeys(name);
	}

	protected void fillInEmail(String email) {
		WebElement emailField = driver.findElement(By.id("email"));
		emailField.clear();
		emailField.sendKeys(email);
	}

	protected void fillInPassword(String password) {
		WebElement passwordField = driver.findElement(By.id("password"));
		passwordField.clear();
		passwordField.sendKeys(password);
	}

	protected void check(String checkBoxName) {
		WebElement checkBox = driver.findElement(By.id(checkBoxName));
		if (!checkBox.isSelected()) {
			checkBox.click();
		}
	}

	protected void unCheck(String checkBoxName) {
		WebElement checkBox = driver.findElement(By.id(checkBoxName));
		if (checkBox.isSelected()) {
			checkBox.click();
		}
	}

	protected void clickOn(String buttonOrLinkName) {
		WebElement button = driver.findElement(By.id(buttonOrLinkName));
		button.click();
	}

	protected void clickOn(String buttonName, int index) {
		List<WebElement> buttons = driver.findElements(By.id(buttonName));
		WebElement button = buttons.get(index);
		button.click();
	}

	protected void checkIfUserInTable(String email) {
		assertTrue("Email not found in table", getRowForUser(email) > -1);
	}

	protected void checkIfUserNotInTable(String email) {
		assertTrue("Email found in table", getRowForUser(email) == -1);
	}

	protected int getRowForUser(String email) {
		List<WebElement> tableRows = driver.findElements(By.tagName("tr"));
		for (int i = 0; i < tableRows.size(); i++) {
			WebElement row = tableRows.get(i);
			if (row.getText().contains(email)) {
				return i - 1;
			}
		}
		return -1;
	}

	protected int getNumberOfRowsInTable() {
		List<WebElement> tableRows = driver.findElements(By.tagName("tr"));
		return tableRows.size() - 1;
	}

	protected void checkIfPreviousValueEmailIsShown(String email) {
		WebElement emailField = driver.findElement(By.id("email"));
		assertEquals(email, emailField.getAttribute("value"));
	}

	protected void checkIfPreviousValueEmailIsNotShown() {
		WebElement emailField = driver.findElement(By.id("email"));
		assertEquals("", emailField.getAttribute("value"));
	}

	protected void checkIfPreviousValueNameIsShown(String name) {
		WebElement nameField = driver.findElement(By.id("name"));
		assertEquals(name, nameField.getAttribute("value"));
	}

	protected void checkIfPreviousValuePasswordIsNotShown() {
		WebElement passwordField = driver.findElement(By.id("password"));
		assertEquals("", passwordField.getAttribute("value"));
	}

	protected void login() {
		driver.get(LOGIN_URL);
		fillInEmail(EMAIL_ADMIN);
		fillInPassword(PASSWORD_ADMIN);
		clickOn("login");
	}

	protected void checkErrorMessagesShownOnSamePage(String pageTitle,
			int numberOfErrorMessagesExpected) {
		assertEquals(pageTitle, driver.getTitle());
		List<WebElement> messages = driver.findElements(By
				.className("alert-danger"));
		assertEquals(numberOfErrorMessagesExpected, messages.size());
	}

	protected void checkIfRedirectedToPageWithTitle(String title) {
		assertEquals(title, driver.getTitle());
	}

	protected void checkIfNotRedirectedToPageWithTitle(String title) {
		assertFalse(title.equals(driver.getTitle()));
	}

	protected void navigateToUserForm(String email) {
		int rowOfAddedUser = getRowForUser(email);
		clickOn("userOverview");
		clickOn("updateUser", rowOfAddedUser);
	}
	
	protected void checkIfLinksAreNotVisible(String... linkNames) {
		for(String linkName : linkNames){
			checkIfLinkIsNotVisible(linkName);
		}
	}
	
	protected void checkIfLinksAreVisible(String... linkNames) {
		for(String linkName : linkNames){
			checkIfLinkIsVisible(linkName);
		}
	}

	protected void checkIfLinkIsVisible(String linkName) {
		assertTrue("Link '" + linkName + "' should be visible", containsLink(linkName));
	}

	protected void checkIfLinkIsNotVisible(String linkName) {
		assertFalse("Link '" + linkName + "' should not be visible", containsLink(linkName));
	}

	private boolean containsLink(String linkName) {
		List<WebElement> links = driver.findElements(By.tagName("a"));
		for(WebElement link : links){
			if(linkName.equals(link.getText())){
				return true;
			}
		}
		return false;
	}

	protected WebDriver getDriver() {
		return driver;
	}

	protected void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	protected Properties getDbProperties() {
		return dbProperties;
	}

}