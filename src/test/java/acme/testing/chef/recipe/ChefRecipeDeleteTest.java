
package acme.testing.chef.recipe;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class ChefRecipeDeleteTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/delete-recipe-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveRecipeTest(final int recordIndex) {

		super.signIn("chef4", "chef4");
		super.navigateHome();

		super.clickOnMenu("Chef", "My Recipes");
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
