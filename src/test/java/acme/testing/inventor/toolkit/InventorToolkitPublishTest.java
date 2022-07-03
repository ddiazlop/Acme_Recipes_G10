package acme.testing.inventor.toolkit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class InventorToolkitPublishTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/publish-toolkit-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positivePublishToolkitTest(final int recordIndex, final String quantity, final String item) {
		
		super.signIn("inventor2", "inventor2");
		super.navigateHome();
		
		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnButton("Create Toolkit");
		super.fillInputBoxIn("code","AAA-000-Z" );
		super.fillInputBoxIn("title", "Toolkit Test 2");
		super.fillInputBoxIn("description", "Toolkit Test number 2");
		super.fillInputBoxIn("assemblyNotes", "Test it better than the others");
		super.clickOnSubmit("Create Toolkit");
		
		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.clickOnButton("Add Items");
		
		super.fillInputBoxIn("quantity", quantity);
		super.clickOnSubmit("Add to Toolkit");
		
		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		
		super.checkFormExists();
		super.clickOnButton("Toolkit Content");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, quantity);
		super.checkColumnHasValue(recordIndex, 1, item);
		super.clickOnButton("Return");
		
		super.checkFormExists();
		super.clickOnSubmit("Publish Toolkit");
		super.checkNotErrorsExist();
		
		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(0,4, "PUBLISHED");
		
		super.signOut();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/publish-toolkit-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativePublishEmptyToolkitTest(final int recordIndex, final String quantity, final String item) {
		super.signIn("inventor2", "inventor2");
		super.navigateHome();
		
		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnButton("Create Toolkit");
		super.fillInputBoxIn("code","AAA-000-Y" );
		super.fillInputBoxIn("title", "Toolkit Test 2");
		super.fillInputBoxIn("description", "Toolkit Test number 2");
		super.fillInputBoxIn("assemblyNotes", "Test it better than the others");
		super.clickOnSubmit("Create Toolkit");
		
		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkNotSubmitExists("Publish Toolkit");
		super.navigate("/inventor/toolkit/publish");
		super.checkPanicExists();
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/publish-toolkit-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void negativePublishToolkitTest(final int recordIndex, final String quantity, final String item) {
		super.signIn("inventor2", "inventor2");
		super.navigateHome();
		
		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(1);
		
		super.checkFormExists();
		super.clickOnButton("Toolkit Content");
		super.checkListingExists();
		super.checkColumnHasValue(0, 2, "COMPONENT");
		super.clickOnButton("Return");
		
		super.checkNotSubmitExists("Publish Toolkit");
		super.navigate("/inventor/toolkit/publish");
		super.checkPanicExists();
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/publish-toolkit-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(40)
	public void negativePublishPublishedToolkitTest(final int recordIndex, final String quantity, final String item) {
		super.signIn("inventor2", "inventor2");
		super.navigateHome();
		
		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(1, 4, "PUBLISHED");
		super.clickOnListingRecord(1);
		
		super.checkFormExists();
		super.checkNotSubmitExists("Publish Toolkit");
		super.navigate("/inventor/toolkit/publish");
		super.checkPanicExists();
		super.signOut();
	}
}
