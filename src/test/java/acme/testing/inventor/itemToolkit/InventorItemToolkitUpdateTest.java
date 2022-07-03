package acme.testing.inventor.itemToolkit;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

@TestMethodOrder(OrderAnnotation.class)
public class InventorItemToolkitUpdateTest  extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/itemToolkit/update-itemToolkit-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeUpdatePublishedItemToolkit(final int recordIndex) {
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
		super.checkNotSubmitExists("Update Quantity");

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/itemToolkit/update-itemToolkit-negative2.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeUpdateItemToolkit(final String quantity) {
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
		super.fillInputBoxIn("quantity", quantity);
		super.clickOnSubmit("Update Quantity");
		
		super.checkErrorsExist();
		super.signOut();
	}
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/itemToolkit/update-itemToolkit-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void positiveUpdateItemToolkit(final String quantity) {
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
		super.fillInputBoxIn("quantity", quantity);
		super.clickOnSubmit("Update Quantity");
				
		super.checkListingExists();
		super.checkColumnHasValue(0, 0, quantity);
		
		super.signOut();
	} 
	
//	@ParameterizedTest
//	@CsvFileSource(resources = "/inventor/itemToolkit/update-itemToolkit-negative3.csv", encoding = "utf-8", numLinesToSkip = 1)
//	@Order(50)
//	@AfterAll
//	public void negativeUpdateToolItemToolkit(final int recordIndex, final String quantity) {
//		super.signIn("inventor2", "inventor2");
//		super.navigateHome();
//		
//		super.clickOnMenu("Inventor", "My Toolkits");
//		super.checkListingExists();
//		super.sortListing(0, "asc");
//		super.checkColumnHasValue(1, 4, "NOT PUBLISHED");
//		super.clickOnListingRecord(1);
//		
//		super.checkFormExists();
//		super.clickOnButton("Add Items");
//		
//		super.checkFormExists();
//		super.fillInputBoxIn("quantity","1");
//		super.clickOnSubmit("Add to Toolkit");
//		
//		super.checkFormExists();
//		super.clickOnButton("Toolkit Content");
//		super.checkListingExists();
//		super.clickOnListingRecord(recordIndex);
//		super.fillInputBoxIn("quantity", quantity);
//		super.clickOnSubmit("Update Quantity");
//		
//		super.checkErrorsExist();
//		super.signOut();
//	}


}
