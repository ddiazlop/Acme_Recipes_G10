package acme.testing.chef.recipe;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefRecipeUpdateTest  extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/update-recipe-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveUpdateToolkitTest(final int recordIndex,final String heading, final String description
		, final String preparationNotes, final String info) {
		super.signIn("chef2", "chef2");
		super.navigateHome();
		
		super.clickOnMenu("Chef", "My Recipes");
		super.checkListingExists();
		super.sortListing(1, "desc");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("preparationNotes", preparationNotes);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmit("Update");
		
		super.clickOnMenu("Chef", "My Recipes");
		super.checkListingExists();
		super.sortListing(1, "desc");
		super.checkColumnHasValue(recordIndex, 1, heading);
		super.checkColumnHasValue(recordIndex, 2, description);
	
	
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("preparationNotes", preparationNotes);
		super.checkInputBoxHasValue("chef", "Chef2Surname, Chef2Name");
		super.checkInputBoxHasValue("info", info);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/update-recipe-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeUpdateToolkitTest(final int recordIndex, final String code,final String heading, final String description
		, final String preparationNotes, final String info) {
		super.signIn("chef2", "chef2");
		super.navigateHome();
		
		super.clickOnMenu("Chef", "My Recipes");
		super.checkListingExists();
		super.sortListing(1, "desc");
		super.clickOnListingRecord(1);

		super.checkFormExists();
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("preparationNotes", preparationNotes);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmit("Update");
		
		super.checkErrorsExist();

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/delete-recipe2.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeUpdatePublishedRecipeTest(final int recordIndex) {
		super.signIn("chef2", "chef2");
		super.navigateHome();
		
		super.clickOnMenu("Chef", "My Recipes");
		super.checkListingExists();
		super.sortListing(4, "desc");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.checkNotSubmitExists("Update");

		super.signOut();
	}
	
	

}