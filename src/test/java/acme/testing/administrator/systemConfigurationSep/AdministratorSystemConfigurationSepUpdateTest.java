package acme.testing.administrator.systemConfigurationSep;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorSystemConfigurationSepUpdateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/systemConfigurationSep/update-system-configuration-sep.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String systemCurrency, final String acceptedCurrencies, final String spamTuple, final String spamThreshold) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Update System Details Sep");
		super.checkFormExists();
		

		super.fillInputBoxIn("systemCurrency", systemCurrency);
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.fillInputBoxIn("spamTuple", spamTuple);
		super.fillInputBoxIn("spamThreshold", spamThreshold);
		super.clickOnSubmit("Update");
		
		super.clickOnMenu("Administrator", "Update System Details Sep");
		super.checkFormExists();
		
		super.checkInputBoxHasValue("systemCurrency", systemCurrency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		super.checkInputBoxHasValue("spamTuple", spamTuple);
		super.checkInputBoxHasValue("spamThreshold", spamThreshold);
		
		super.checkNotErrorsExist();

		super.signOut();
	}
	
}