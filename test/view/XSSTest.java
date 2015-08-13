package view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoAlertPresentException;

public class XSSTest extends ViewTest{

	private static final String XSS = "\"> <script>alert(\"lol\");</script>";
	
	public XSSTest() {
		super();
	}

	@Before
	public void setUp()  {
		super.setUp();
		login();
		getDriver().get(REGISTER_URL);
	}

	@After
	public void clean() {
		super.clean();
	}

	@Test (expected=NoAlertPresentException.class)
	public void test_When_malicious_script_is_entered_in_name_field_Then_the_form_is_shown_again_whith_script_as_plain_text_in_name_field () {
		fillInName(XSS);
		fillInEmail("");
		fillInPassword("");
		
		clickOn("register");
		getDriver().switchTo().alert();
	}
}