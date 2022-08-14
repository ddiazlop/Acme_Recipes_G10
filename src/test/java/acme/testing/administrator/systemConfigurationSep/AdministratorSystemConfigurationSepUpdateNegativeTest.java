
package acme.testing.administrator.systemConfigurationSep;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.framework.testing.BrowserDriver;
import acme.testing.TestHarness;

public class AdministratorSystemConfigurationSepUpdateNegativeTest extends TestHarness {


	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/systemConfigurationSep/update-system-configuration-sep-negativeCurrencies.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTestAcceptedCurrencies(final int recordIndex, final String systemCurrency, final String acceptedCurrencies, final String spamTuple, final String spamThreshold) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Update System Details Sep");
		super.checkFormExists();
		
		super.clickOnButton("Accepted currencies");
		super.checkFormExists();
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.clickOnSubmit("Update");
		super.checkErrorsExist();
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/systemConfigurationSep/update-system-configuration-sep-negativeSystemCurrency.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTestSystemCurrency(final int recordIndex, final String systemCurrency, final String acceptedCurrencies, final String spamTuple, final String spamThreshold) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Update System Details Sep");
		super.checkFormExists();
		
		super.clickOnButton("System currency");
		final BrowserDriver driver = super.getDriver();
		driver.locateOne(By.xpath("//*[@name=\"systemCurrency_proxy\"]/option[\""+ systemCurrency +"\"]")).click();
		super.clickOnSubmit("Update");
		super.checkErrorsExist();
		
		super.signOut();
	}
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/systemConfigurationSep/update-system-configuration-sep-negativeSpam.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTestSpamWords(final int recordIndex, final String systemCurrency, final String acceptedCurrencies, final String spamTuple, final String spamThreshold) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Update System Details Sep");
		super.checkFormExists();
		
		super.clickOnButton("Spam Words");
		super.fillInputBoxIn("spamTuple", spamTuple);
		super.fillInputBoxIn("spamThreshold", spamThreshold);
		super.clickOnSubmit("Update");
		super.checkErrorsExist();
		
		super.signOut();
	}
	

	

}
