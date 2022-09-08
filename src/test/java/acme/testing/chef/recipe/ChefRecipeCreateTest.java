package acme.testing.chef.recipe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class ChefRecipeCreateTest extends TestHarness{
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/create-recipe-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveCreateRecipeTest(final int recordIndex, final String code, final String heading, final String description
		, final String preparationNotes, final String info) {
		super.signIn("chef7", "chef7");
		super.navigateHome();
		
		super.clickOnMenu("Chef", "My Recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnButton("New Recipe");
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("preparationNotes", preparationNotes);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmit("New Recipe");
		
		super.clickOnMenu("Chef", "My Recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, heading);
		super.checkColumnHasValue(recordIndex, 2, description);
		super.checkColumnHasValue(recordIndex, 3, "EUR 0.00");
		super.checkColumnHasValue(recordIndex, 4, "NOT PUBLISHED");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("preparationNotes", preparationNotes);
		super.checkInputBoxHasValue("chef", "Chef7Surname, Chef7Name");
		super.checkInputBoxHasValue("price", "EUR 0.00");
		super.checkInputBoxHasValue("info", info);
		
		super.clickOnButton("View Associated Kitchenwares");
		
		super.checkListingExists();
		super.checkListingEmpty();
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/create-recipe-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeCreateRecipeTest(final int recordIndex, final String code, final String heading, final String description
		, final String preparationNotes, final String info) {
		super.signIn("chef2", "chef2");
		super.navigateHome();
		
		super.clickOnMenu("Chef", "My Recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnButton("New Recipe");
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("preparationNotes", preparationNotes);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmit("New Recipe");
		
		super.checkErrorsExist();

		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		
		super.navigate("/chef/recipe/create");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/inventor/toolkit/create");
		super.checkPanicExists();
		super.signOut();

		super.signIn("epicure1", "epicure1");
		super.navigate("/chef/recipe/create");
		super.checkPanicExists();
		super.signOut();
	}
}