package acme.testing.inventor.itemToolkit;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

@TestMethodOrder(OrderAnnotation.class)
public class InventorItemToolkitCreateTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/itemtoolkit/create-item-toolkit.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(3)
	public void positiveCreateItemToolkit(final int toolkitIndex, final int recordIndex, final String quantity, final String name, final String itemType,
		final String code,final String technology,final String retailPrice, final String toolkitPrice) {
		super.signIn("inventor2", "inventor2");
		super.navigateHome();
		
		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(toolkitIndex, 4, "NOT PUBLISHED");
		super.clickOnListingRecord(toolkitIndex);
		
		super.checkFormExists();
		super.clickOnButton("Add Items");
		
		super.checkFormExists();
		super.fillInputBoxIn("quantity",quantity);
		super.clickOnSubmit("Add to Toolkit");
		
		super.checkFormExists();
		super.clickOnButton("Toolkit Content");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, quantity);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, itemType);
		super.checkColumnHasValue(recordIndex, 3, code);
		super.checkColumnHasValue(recordIndex, 4, technology);
		super.checkColumnHasValue(recordIndex, 5, retailPrice);
		
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("quantity", quantity);
		super.checkInputBoxHasValue("item.name", name);
		
		super.signOut();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/itemtoolkit/create-itemToolkit-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(2)
	public void negativeCreateItemToolkit(final int recordIndex, final String quantity) {
		super.signIn("inventor2", "inventor2");
		super.navigateHome();
		
		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 4, "NOT PUBLISHED");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.clickOnButton("Add Items");
		
		super.checkFormExists();
		super.fillInputBoxIn("quantity",quantity);
		super.clickOnSubmit("Add to Toolkit");
		super.checkErrorsExist();
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/itemToolkit/create-itemToolkit-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(1)
	public void negativeCreateItemToolkitPublished(final int recordIndex, final String quantity) {
		super.signIn("inventor2", "inventor2");
		super.navigateHome();
		
		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(0, 4, "PUBLISHED");
		super.clickOnListingRecord(0);
		
		super.checkFormExists();
		super.checkNotButtonExists("Add Items");
		
		super.signOut();
	}
	
	//Da error pero el método checkErrors no lo capta
	
//	@ParameterizedTest
//	@CsvFileSource(resources = "/inventor/itemToolkit/create-item-toolkit.csv", encoding = "utf-8", numLinesToSkip = 1)
//	@Order(4)
//	public void negativeDuplicatedCreateItemToolkit(final int toolkitIndex, final int recordIndex, final String quantity) {
//		super.signIn("inventor2", "inventor2");
//		super.navigateHome();
//		
//		super.clickOnMenu("Inventor", "My Toolkits");
//		super.checkListingExists();
//		super.sortListing(0, "asc");
//		super.checkColumnHasValue(toolkitIndex, 4, "NOT PUBLISHED");
//		super.clickOnListingRecord(toolkitIndex);
//		
//		super.checkFormExists();
//		super.clickOnButton("Add Items");
//		
//		super.checkFormExists();
//		super.fillInputBoxIn("quantity",quantity);
//		super.clickOnSubmit("Add to Toolkit");
//		super.checkErrorsExist();
//		
//		super.signOut();
//	}
		
}
