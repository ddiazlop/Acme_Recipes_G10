
package acme.testing.chef.fineDish;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class ChefFineDishDeleteTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/fineDish/delete-fineDish-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveRecipeTest(final int recordIndex) {

		super.signIn("chef4", "chef4");
		super.navigateHome();

		super.clickOnMenu("Chef", "My FineDishes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkNotListingEmpty();
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();
		
		super.signOut();
	}

}
