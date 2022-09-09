
package acme.testing.epicure.memoranda;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class EpicureMemorandaListAndShowTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/memorandum/list-and-show-test.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(100)
	public void positiveRecipeTest(final int recordIndex, final String moment, final String secuence, final String report
		, final String info,final String chef,final String fDcode) {
		
		super.signIn("epicure1", "epicure1");
		super.navigateHome();
		
		super.clickOnMenu("Epicure", "My Memoranda");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, secuence);
		super.checkColumnHasValue(recordIndex, 2, chef);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("moment", moment);
		super.checkInputBoxHasValue("sequenceNumber", secuence);
		super.checkInputBoxHasValue("report", report);
		super.checkInputBoxHasValue("info", info);
		super.checkInputBoxHasValue("chef.userAccount.username", chef);
		super.checkInputBoxHasValue("fineDish.code", fDcode);
	}

}
