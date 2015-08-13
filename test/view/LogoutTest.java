package view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LogoutTest extends ViewTest{
	
	public LogoutTest() {
		super();
	}

	@Before
	public void setUp() {
		super.setUp();
		login();
	}
	
	@After
	public void clean(){
		super.clean();
	}

	@Test
	public void test_When_user_clicks_on_logout_Then_protectedLinks_are_not_accessible_anymore () {
		clickOn("logout");
		checkIfLinksAreNotVisible("My Journal", "New Entry", "Log out");
	}

	@Test
	public void test_When_user_clicks_on_logout_Then_login_link_is_accessible () {
		clickOn("logout");
                checkIfRedirectedToPageWithTitle("Login - DreamJournal");
	}
}

