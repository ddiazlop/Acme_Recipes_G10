package acme.testing.chef.recipe;



import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefRecipePublishTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/publish-recipe-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positivePublishRecipeTest(final int recordIndex) {
		super.signIn("chef2", "chef2");
		super.navigateHome();

		super.clickOnMenu("Chef", "My Recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(2);
		super.checkFormExists();
		super.checkSubmitExists("Publish recipe");
		super.clickOnSubmit("Publish recipe");
		super.clickOnMenu("Chef", "My Recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(2, 4, "PUBLISHED");
		super.signOut();
		
	}
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/publish-recipe-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positivePublishNegativeRecipeTest(final int recordIndex){
		
		super.signIn("chef2", "chef2");
		super.navigateHome();

		super.clickOnMenu("Chef", "My Recipes");
		super.checkListingExists();
		super.sortListing(2, "asc");
		super.clickOnListingRecord(2);
		super.checkFormExists();
		super.checkNotSubmitExists("Publish recipe");
		
		super.signOut();
		
	}
}