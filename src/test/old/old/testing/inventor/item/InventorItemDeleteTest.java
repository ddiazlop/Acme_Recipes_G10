package old.testing.inventor.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import old.testing.TestHarness;


public class InventorItemDeleteTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveToolTest(final int recordIndex) {
		super.navigateHome();

		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "My Tools");

		super.checkListingExists();
		super.checkNotListingEmpty();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();

	}

}
