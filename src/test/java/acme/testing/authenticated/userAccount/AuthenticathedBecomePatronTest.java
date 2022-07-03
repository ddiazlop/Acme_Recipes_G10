package acme.testing.authenticated.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticathedBecomePatronTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources =  "/authenticated/userAccount/patron-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final String username, final String password, final String company, final String statement, final String info) {
		super.signIn(username, password);
		super.navigateHome();
		
		super.clickOnMenu("Account", "Become a patron");
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("statement", statement);
		super.fillInputBoxIn("info", info);

		super.clickOnSubmit("Register");
		
		super.navigateHome();
		super.clickOnMenu("Account", "Patron data");
		super.checkFormExists();
		super.checkInputBoxHasValue("company", company);
		super.checkInputBoxHasValue("statement", statement);
		super.checkInputBoxHasValue("info", info);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources =  "/authenticated/userAccount/patron-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final String username, final String password, final String company, final String statement, final String info) {
		super.signIn(username, password);
		super.navigateHome();
		
		super.clickOnMenu("Account", "Become a patron");
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("statement", statement);
		super.fillInputBoxIn("info", info);

		super.clickOnSubmit("Register");
		super.checkErrorsExist();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		
		super.navigate("/authenticated/patron/create");
		super.checkPanicExists();
		
		super.signIn("patron1", "patron1");
		super.navigate("/authenticated/patron/create");
		super.checkPanicExists();
		
	}


}
