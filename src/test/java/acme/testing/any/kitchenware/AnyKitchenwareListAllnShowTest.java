package acme.testing.any.kitchenware;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyKitchenwareListAllnShowTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/any/kitchenware/list-all-ingredients.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(100)
	public void positiveIngredientTest(final int recordIndex, final String name, final String code, final String description, final String retailPrice,
										final String info,final String converted,final String type) {
		
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Ingredients");
		
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, name);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("retailPriceConverted", converted);
		
		super.checkInputBoxHasValue("info", info);
		super.checkInputBoxHasValue("wareType", type);
		
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/any/kitchenware/list-all-kitchen-utensils.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveUtensilsTest(final int recordIndex, final String name, final String code, final String description, final String retailPrice,
		final String info,final String converted,final String type) {

		super.navigateHome();
		super.clickOnMenu("Anonymous", "Kitchen Utensils");
		
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, name);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("retailPriceConverted", converted);
		super.checkInputBoxHasValue("info", info);
		super.checkInputBoxHasValue("wareType", type);
		
	}

}