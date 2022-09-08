package acme.testing.chef.memoranda;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefMemorandaListAndShowTest extends TestHarness{
	
	@Order(100)
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/memoranda/list-all-memoranda.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positiveMemorandaShowAndListTest(final int recordIndex, final String sequenceNumber, final String fdcode, final String moment, final String epicureUsername,
												final String report) {
			
		
			super.signIn("chef2", "chef2");
			super.navigateHome();
			super.clickOnMenu("Chef", "My Memoranda");
			
			super.checkListingExists();
			super.sortListing(0, "asc");

			super.checkColumnHasValue(recordIndex, 0, sequenceNumber);
			super.checkColumnHasValue(recordIndex, 1, fdcode);
			super.checkColumnHasValue(recordIndex, 2, moment);
			super.checkColumnHasValue(recordIndex, 3, epicureUsername);

			super.clickOnListingRecord(recordIndex);
			super.checkFormExists();
		
			super.checkInputBoxHasValue("sequenceNumber", sequenceNumber);
			super.checkInputBoxHasValue("fineDishCode", fdcode);
			super.checkInputBoxHasValue("report", report);
			super.checkInputBoxHasValue("moment", moment);

			
			
		}

	
}
