
package acme.testing.chef.recipe;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class ChefRecipeUpdateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String heading, 
			final String description, final String preparationNotes, final Boolean published, final String info) {
		
		super.signIn("chef3", "chef3");

		super.navigateHome();

		super.clickOnMenu("Chef", "My Recipes");

		super.checkListingExists();
		this.clickOnListingRecord(1);
		
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("preparationNotes", preparationNotes);
		super.fillInputBoxIn("published", published);
		super.fillInputBoxIn("info", info);

		
		super.clickOnSubmit("Update");

		super.sortListing(0, "asc");

		this.clickOnListingRecord(1);

		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("preparationNotes", preparationNotes);
		super.checkInputBoxHasValue("published", published);
		super.checkInputBoxHasValue("info", info);

		super.signOut();

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String code, final String heading, 
			final String description, final String preparationNotes, final Boolean published, final String info) {

		super.signIn("chef3", "chef3");

		super.navigateHome();

		super.clickOnMenu("Chef", "My Recipes");

		super.checkListingExists();
		this.clickOnListingRecord(1);

		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("preparationNotes", preparationNotes);
		super.fillInputBoxIn("published", published);
		super.fillInputBoxIn("info", info);


		super.clickOnSubmit("Update");

		super.checkErrorsExist();

	}

}
