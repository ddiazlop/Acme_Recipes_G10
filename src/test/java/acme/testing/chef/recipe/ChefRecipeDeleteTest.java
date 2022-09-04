package acme.testing.chef.recipe;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class ChefRecipeDeleteTest extends TestHarness{
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/delete-recipe.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveDeleteToolkitTest(final int recordIndex) {
		super.signIn("chef2", "chef2");
		super.navigateHome();
		
		super.clickOnMenu("Chef", "My Recipes");
		super.checkListingExists();
		super.sortListing(0, "desc");
		super.clickOnListingRecord(recordIndex+1);
		super.clickOnSubmit("Delete Recipe");
		super.checkNotErrorsExist();
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/delete-recipe2.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeDeleteToolkitTest(final int recordIndex) {
		super.signIn("chef2", "chef2");
		super.navigateHome();
		
		super.clickOnMenu("Chef", "My Recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 4, "PUBLISHED");
		super.clickOnListingRecord(recordIndex);
		super.checkNotSubmitExists("Delete Recipe");
		
		super.signOut();
	}
	
}