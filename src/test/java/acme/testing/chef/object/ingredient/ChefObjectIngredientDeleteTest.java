package acme.testing.chef.object.ingredient;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefObjectIngredientDeleteTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveObjectIngredientDeleteTest(final int recordIndex) {

		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My Ingredients");

		super.checkListingExists();
		super.sortListing(0, "desc");

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();

	}

}
