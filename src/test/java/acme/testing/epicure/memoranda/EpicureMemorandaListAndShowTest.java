
package acme.testing.epicure.memoranda;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class EpicureMemorandaListAndShowTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/memorandum/list-and-show-test.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveRecipeTest(final int recordIndex, final String code, final String report, final String moment
		, final String info) {
		
		super.signIn("epicure1", "epicure1");
		super.navigateHome();
		
		super.clickOnMenu("Epicure", "My Memoranda");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, moment);
		super.checkColumnHasValue(recordIndex, 2, report);
		super.checkColumnHasValue(recordIndex, 3, info);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("moment", moment);
		super.checkInputBoxHasValue("request", report);
		super.checkInputBoxHasValue("info", info);
	}

}
