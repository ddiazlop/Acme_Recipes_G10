package acme.testing.any.object.ingredient;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyObjectIngredientListTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/any/object/ingredient/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveIngredientTest(final int recordIndex, final String name, final String code, final String objectType, final String description, final String retailPrice, final String info,
		final String published) {
		
		super.clickOnMenu("Anonymous", "Ingredients");
		
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
	}

	

}
