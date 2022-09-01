package acme.testing.chef.fineDish;

import java.util.Date;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class ChefFineDishListAndShowTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/fineDish/list-fineDish.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveRecipeTest(final int recordIndex,final String status, final String code, final String request, 
			final Money budget, final Date creationDate, final Date startDate, final Date endDate, final String info) {
		
		super.signIn("chef1", "chef1");
		super.navigateHome();

		super.clickOnMenu("Chef", "My Recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, code);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("request", request);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("creationDate", creationDate);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("info", info);
		

		super.signOut();
	}

}
