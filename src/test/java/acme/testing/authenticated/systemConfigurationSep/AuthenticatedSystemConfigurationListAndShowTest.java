
package acme.testing.authenticated.systemConfigurationSep;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedSystemConfigurationListAndShowTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/systemConfigurationSep/show-system-configuration-sep.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveSystemConfigurationTest(final int recordIndex, final String systemCurrency, final String acceptedCurrencies) {

		super.signIn("epicure1", "epicure1");

		super.navigateHome();
		super.clickOnMenu("Authenticated", "Currencies information sep");

		super.checkFormExists();
		super.checkInputBoxHasValue("systemCurrency", systemCurrency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);

		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		
		super.navigate("/authenticated/system-configuration-sep/show");
		super.checkPanicExists();
	}
	

}
