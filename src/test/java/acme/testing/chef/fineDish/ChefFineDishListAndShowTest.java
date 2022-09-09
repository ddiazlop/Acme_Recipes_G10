package acme.testing.chef.fineDish;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefFineDishListAndShowTest extends TestHarness{

	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/fineDish/list-and-show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(100)
	public void positiveChefFineDishTest(final int recordIndex, final String code, 
		final String request, final String budget, final String epicureUserName, final String epicureOrganisation) {
		
		super.signIn("chef2", "chef2");
		
		super.clickOnMenu("Chef", "My Dishes");	
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, code);


		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("request", request);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("epicureUserName", epicureUserName);
		super.checkInputBoxHasValue("epicureOrganisation", epicureOrganisation);
		
		super.signOut();
		
		
	}


}
