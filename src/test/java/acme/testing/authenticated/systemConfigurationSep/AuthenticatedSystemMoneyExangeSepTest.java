package acme.testing.authenticated.systemConfigurationSep;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedSystemMoneyExangeSepTest  extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/systemConfigurationSep/money-exange-sep.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	
	
	public void positiveSystemConfigurationTestExhange(final int recordIndex, final String systemCurrency, final String acceptedCurrencies,final String source_money,final String target_currency) {
		super.signIn("epicure1", "epicure1");
		super.navigateHome();
		super.clickOnMenu("Authenticated", "Currencies information Sep");
		super.checkFormExists();
		
		super.clickOnButton("Perform exchange");
		super.fillInputBoxIn("source", source_money);
		super.fillInputBoxIn("targetCurrency", target_currency);
		super.clickOnButton("Perform exchange");
		super.checkNotErrorsExist();
		
		super.signOut();
		
		
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.navigate("/authenticated/money-exchange-sep/perform");
		super.checkPanicExists();
	}
	
	
}
