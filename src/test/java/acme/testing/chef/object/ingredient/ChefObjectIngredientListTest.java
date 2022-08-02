package acme.testing.chef.object.ingredient;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefObjectIngredientListTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/object/list-ingredient.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveChefIngredientsTest(final int recordIndex, final String name, final String code, final String objectType, final String description, final String retailPrice, final String info,
		final String published) {
		
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "My Ingredients");	
		super.checkListingExists();
		
		super.checkListingExists();
		super.sortListing(0, "desc");

		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, published);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("info", info);
		super.checkInputBoxHasValue("objectType", objectType);
		super.checkInputBoxHasValue("published", published);
		
		super.signOut();
		
		
	}
	
	@Test
	@Order(20)
	public void hackingTest() {
		final String pathShowObject= "/chef/object/show";
		
		
		super.checkNotLinkExists("Chef");
		super.navigate(pathShowObject);
		super.checkPanicExists();
		
		super.signIn("administrator", "administrator");
		super.checkNotLinkExists("Chef");
		super.navigate(pathShowObject);
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("epicure1", "epicure1");
		super.checkNotLinkExists("Chef");
		super.navigate(pathShowObject);
		super.checkPanicExists();
		
		super.signOut();
		
	}

}
