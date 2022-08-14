package acme.testing.administrator.systemConfigurationSep;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.framework.testing.BrowserDriver;
import acme.testing.TestHarness;

public class AdministratorSystemConfigurationSepUpdateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/systemConfigurationSep/update-system-configuration-sep.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String systemCurrency, final String acceptedCurrencies, final String spamTuple, final String spamThreshold) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Update System Details Sep");
		super.checkFormExists();
		
		super.clickOnButton("Accepted currencies");
		super.checkFormExists();
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.clickOnSubmit("Update");
		super.checkNotErrorsExist();
		
		super.clickOnButton("System currency");
		super.checkFormExists();
		final BrowserDriver driver = super.getDriver();
		driver.locateOne(By.xpath("//*[@name=\"systemCurrency_proxy\"]/option[\""+ systemCurrency +"\"]")).click();
		super.clickOnSubmit("Update");
		super.checkNotErrorsExist();
		
		super.clickOnButton("Spam Words");
		super.checkFormExists();
		super.fillInputBoxIn("spamTuple", spamTuple);
		super.fillInputBoxIn("spamThreshold", spamThreshold);
		super.clickOnSubmit("Update");
		super.checkNotErrorsExist();

		super.clickOnMenu("Administrator", "Update System Details Sep");
		super.checkFormExists();
		
		super.clickOnButton("Accepted currencies");
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		
		super.clickOnButton("Spam Words");
		super.checkInputBoxHasValue("spamTuple", spamTuple);
		super.checkInputBoxHasValue("spamThreshold", spamThreshold);
		
		super.checkNotErrorsExist();

		super.signOut();
	}
	
	
	@Test
	@Order(30)
	public void hackingTest() {
		
		super.navigate("/administrator/system-configuration-sep/update");
		super.checkPanicExists();
		
		super.signIn("chef1", "chef1");
		super.navigate("/administrator/system-configuration-sep/update");
		super.checkPanicExists();
		super.signOut();

		super.signIn("epicure1", "epicure1");
		super.navigate("/administrator/system-configuration-sep/update");
		super.checkPanicExists();
		super.signOut();
	}
	
}