package acme.testing.inventor.itemToolkit;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

@TestMethodOrder(OrderAnnotation.class)
public class InventorItemToolkitDeleteTest  extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/itemToolkit/delete-itemToolkit-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(1)
	public void negativeDeleteItemToolkit(final int recordIndex) {
		super.signIn("inventor2", "inventor2");
		super.navigateHome();
		
		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(0, 4, "PUBLISHED");
		super.clickOnListingRecord(0);
		
		super.checkFormExists();
		super.clickOnButton("Toolkit Content");
		super.checkListingExists();
		super.clickOnListingRecord(recordIndex);
		super.checkNotSubmitExists("Delete Item");
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/itemToolkit/create-itemToolkit-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(2)
	public void positiveDeleteItemToolkit(final int recordIndex) {
		super.signIn("inventor2", "inventor2");
		super.navigateHome();
		
		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(1, 4, "NOT PUBLISHED");
		super.clickOnListingRecord(1);
		
		super.checkFormExists();
		super.clickOnButton("Toolkit Content");
		super.checkListingExists();
		super.clickOnListingRecord(0);
		super.clickOnSubmit("Delete Item");
		
		super.checkListingEmpty();
		
		super.signOut();
	}

}
