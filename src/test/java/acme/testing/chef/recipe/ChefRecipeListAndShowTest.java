package acme.testing.chef.recipe;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class ChefRecipeListAndShowTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/list-recipe.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveRecipeTest(final int recordIndex, final String code, final String heading, 
			final String description, final String preparationNotes, final Boolean published, final String info) {
		
		super.signIn("chef1", "chef1");
		super.navigateHome();

		super.clickOnMenu("Chef", "My Recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, heading);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("preparationNotes", preparationNotes);
		super.checkInputBoxHasValue("published", published);
		super.checkInputBoxHasValue("info", info);


		super.signOut();
	}

}
