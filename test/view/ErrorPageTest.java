package view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ErrorPageTest extends ViewTest{

	public ErrorPageTest() {
		super();
	}

	@Before
	public void setUp()  {
		super.setUp();
		login();
	}

	@After
	public void clean() {
		super.clean();
	}

	@Test
	public void test_When_something_goes_wrong_Then_the_error_page_is_shown_with_the_error_shown () {
			addExistingUser();
			
			checkIfRedirectedToPageWithTitle("Register - DreamJournal");
	}

	private void addExistingUser() {
		getDriver().get(REGISTER_URL);
		fillInName(NAME_ADMIN);
		fillInEmail(EMAIL_ADMIN);
		fillInPassword(PASSWORD_ADMIN);
		clickOn("register");
	}
}