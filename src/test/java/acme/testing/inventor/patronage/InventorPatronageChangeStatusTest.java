package acme.testing.inventor.patronage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class InventorPatronageChangeStatusTest extends TestHarness{

	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/change-status-patronage.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String buttonLabel, final String status) {
		super.signIn("inventor2", "inventor2");
		super.clickOnMenu("Inventor", "My Patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkSubmitExists(buttonLabel);
		super.clickOnSubmit(buttonLabel);
		super.checkListingExists();
		super.checkColumnHasValue(recordIndex, 2, status);
		
	}
	
	
	@Test
	@Order(10)
	public void negativeTest() {
		super.signIn("inventor2", "inventor2");
		super.clickOnMenu("Inventor", "My Patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		super.checkNotSubmitExists("ACCEPTED");
		super.checkNotSubmitExists("DENIED");
	}
	
}
