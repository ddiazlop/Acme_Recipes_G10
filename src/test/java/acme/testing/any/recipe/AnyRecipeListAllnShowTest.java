
package acme.testing.any.recipe;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyRecipeListAllnShowTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/any/recipe/list-and-show-recipes.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(100)
	public void positiveRecipeTest(final int recordIndex, final String code, final String heading, final String description, final String price, final String preparationNotes, final String chef, final String info) {
		super.clickOnMenu("Anonymous", "Recipes");
		super.checkListingExists();
		super.sortListing(0, "desc");

		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, heading);
		super.checkColumnHasValue(recordIndex, 2, description);
		super.checkColumnHasValue(recordIndex, 3, price);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();

		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("preparationNotes", preparationNotes);
		super.checkInputBoxHasValue("chef", chef);
		super.checkInputBoxHasValue("price", price);
		super.checkInputBoxHasValue("info", info);

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/any/recipe/list-and-show-ingredients-from-recipe.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(1)
	public void positiveRecipeIngredientTest(final int recordIndex, final String code, final String name, final String quantity, final String unitType, final String price, final String description, final String totalPrice, final String info) {
		super.clickOnMenu("Anonymous", "Recipes");
		super.clickOnListingRecord(recordIndex);

		super.clickOnButton("List of ingredients");
		super.checkListingExists();

		super.sortListing(0, "desc");
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, quantity);
		super.checkColumnHasValue(recordIndex, 3, unitType);
		super.checkColumnHasValue(recordIndex, 4, price);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();

		super.checkInputBoxHasValue("kitchenware.code", code);
		super.checkInputBoxHasValue("kitchenware.name", name);
		super.checkInputBoxHasValue("quantity", quantity);
		super.checkInputBoxHasValue("kitchenware.description", description);
		super.checkInputBoxHasValue("totalPrice", totalPrice);
		super.checkInputBoxHasValue("kitchenware.info", info);

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/any/recipe/list-and-show-utensils-from-recipe.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(100)
	public void positiveRecipeUtensilsTest(final int recordIndex, final String code, final String name, final String quantity, final String price, final String description, final String info) {
		super.clickOnMenu("Anonymous", "Recipes");
		super.clickOnListingRecord(recordIndex);

		super.clickOnButton("List of kitchen utensils");
		super.checkListingExists();

		super.sortListing(0, "desc");
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, quantity);
		super.checkColumnHasValue(recordIndex, 4, price);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();

		super.checkInputBoxHasValue("kitchenware.code", code);
		super.checkInputBoxHasValue("kitchenware.name", name);
		super.checkInputBoxHasValue("quantity", quantity);
		super.checkInputBoxHasValue("kitchenware.description", description);
		super.checkInputBoxHasValue("retailPrice", price);
		super.checkInputBoxHasValue("kitchenware.info", info);

	}

}
