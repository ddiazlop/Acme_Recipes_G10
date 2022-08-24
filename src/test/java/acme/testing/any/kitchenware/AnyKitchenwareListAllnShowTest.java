package acme.testing.any.kitchenware;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyKitchenwareListAllnShowTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/any/object/ingredient/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveIngredientTest(final int recordIndex, final String name, final String code, final String description, final String retailPrice, final String info) {
		
		super.clickOnMenu("Anonymous", "Ingredients");
		
		super.checkListingExists();
		super.sortListing(0, "desc");

		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, name);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("info", info);
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/any/kitchenware/list-all-kitchen-utensils.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveKitchenwareTest(final int recordIndex,final String name, final String code, final String description, final String retailPrice, final String info) {
		super.navigateHome();

		super.clickOnMenu("Anonymous", "Kitchen Utensils");

		this.checkListAllKitchenwaresNShow(recordIndex, code, name, retailPrice, description, info);
	}
	
	protected void checkListAllKitchenwaresNShow(final int recordIndex, final String code, final String name, final String retailPrice, final String description, final String info) {
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
		super.checkInputBoxHasValue("info", info);
		
	}
	
}
