package acme.testing.inventor.itemToolkit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class InventorItemToolkitListTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/itemToolkit/list-item-toolkit.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void checkListItemToolkit(final int toolkitIndex, final int recordIndex, final String quantity, final String name, final String itemType,
		final String code,final String technology,final String retailPrice) {
		
		super.signIn("inventor2", "inventor2");
		super.navigateHome();
		
		super.clickOnMenu("Inventor", "My Toolkits");
		super.sortListing(0, "asc");
		this.clickOnListingRecord(toolkitIndex);
		super.clickOnButton("Toolkit Content");
		
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, quantity);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, itemType);
		super.checkColumnHasValue(recordIndex, 3, code);
		super.checkColumnHasValue(recordIndex, 4, technology);
		super.checkColumnHasValue(recordIndex, 5, retailPrice);
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("quantity", quantity);
		super.checkInputBoxHasValue("item.name", name);
		
		super.signOut();
	}

}
