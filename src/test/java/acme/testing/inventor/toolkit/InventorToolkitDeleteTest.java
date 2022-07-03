package acme.testing.inventor.toolkit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class InventorToolkitDeleteTest extends TestHarness{
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/delete-toolkit.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveDeleteToolkitTest(final int recordIndex) {
		super.signIn("inventor2", "inventor2");
		super.navigateHome();
		
		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();
		super.sortListing(0, "desc");
		super.clickOnListingRecord(recordIndex);
		super.clickOnSubmit("Delete Toolkit");
		super.checkNotErrorsExist();
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/delete-toolkit2.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeDeleteToolkitTest(final int recordIndex) {
		super.signIn("inventor2", "inventor2");
		super.navigateHome();
		
		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();
		super.sortListing(0, "desc");
		super.checkColumnHasValue(recordIndex, 4, "PUBLISHED");
		super.clickOnListingRecord(recordIndex);
		super.checkNotSubmitExists("Delete Toolkit");
		
		super.signOut();
	}
	
}
