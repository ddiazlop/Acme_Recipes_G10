package acme.testing.chef.memoranda;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefMemorandaListAndShowFromFineDishTest extends TestHarness{
	
	
	@Order(100)
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/memoranda/list-all-memoranda-finedish.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positiveMemorandaShowAndListTest(final int recordIndex, final String sequenceNumber, final String fdcode, final String moment, final String epicureUsername,
												final String report) {
			
		super.signIn("chef2", "chef2");
		super.clickOnMenu("Chef", "My Dishes");

		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(1);
			
		super.checkButtonExists("View Related Memoranda");
		super.clickOnButton("View Related Memoranda");
		super.clickOnListingRecord(0);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("sequenceNumber", sequenceNumber);
		super.checkInputBoxHasValue("fineDishCode", fdcode);
		super.checkInputBoxHasValue("report", report);
		super.checkInputBoxHasValue("moment", moment);

		super.signOut();
			
		}

	
}
