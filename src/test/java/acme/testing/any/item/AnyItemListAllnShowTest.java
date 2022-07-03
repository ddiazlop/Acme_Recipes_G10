
package acme.testing.any.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;


public class AnyItemListAllnShowTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/any/item/list-all-components.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveComponentTest(final int recordIndex, final String itemType, final String name, final String code, final String technology, final String description, final String retailPrice, final String inventor, final String info) {
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Components");
		this.checkListAllItemsNShow(recordIndex, code, name, technology, retailPrice, description, info);
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/any/item/list-all-tools.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveToolTest(final int recordIndex, final String itemType, final String name, final String code, final String technology, final String description, final String retailPrice, final String inventor, final String info) {
		super.navigateHome();

		super.clickOnMenu("Anonymous", "Tools");

		this.checkListAllItemsNShow(recordIndex, code, name, technology, retailPrice, description, info);
	}
	
	protected void checkListAllItemsNShow(final int recordIndex, final String code, final String name, final String technology, final String retailPrice, final String description, final String info) {
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, technology);
		super.checkColumnHasValue(recordIndex, 3, retailPrice);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("info", info);
		
	}


}
