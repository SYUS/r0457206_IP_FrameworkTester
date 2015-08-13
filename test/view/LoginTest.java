package view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginTest extends ViewTest {
	
	public LoginTest() {
		super();
	}
	
	@Before
	public void setUp() {
		super.setUp();
		getDriver().get(LOGIN_URL);
	}

	@After
	public void clean() {
		super.clean();
	}

	@Test
	public void test_When_email_is_empty_Then_the_login_form_is_shown_again() {
		fillInEmail("");
		fillInPassword(PASSWORD_ADMIN);
		clickOn("login");

		checkErrorMessagesShownOnSamePage("Login - DreamJournal", 1);
		checkIfPreviousValuePasswordIsNotShown();
	}

}
