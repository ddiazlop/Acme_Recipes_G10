
package acme.testing.epicure.fineDish;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class EpicureFineDishListAndShowTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/fineDish/list-and-show-test.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(100)
	public void positiveRecipeTest(final int recordIndex, final String code, final String request, final String status, final String budget, 
		final String creationDate, final String startDate,final String endDate, final String info , final String published , final String chef) {
		
		super.signIn("epicure1", "epicure1");
		super.navigateHome();
		
		super.clickOnMenu("Epicure", "My Fine Dishes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, request);
		super.checkColumnHasValue(recordIndex, 2, status);
		super.checkColumnHasValue(recordIndex, 3, published);
	
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("request", request);
		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("creationDate", creationDate);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("info", info);
		super.checkInputBoxHasValue("chef.username", chef);
		
	}

}
