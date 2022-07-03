
package acme.testing.patron.patronage;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class PatronPatronageDeleteTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/delete-patronage-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positivePatronageTest(final int recordIndex) {

		super.signIn("patron4", "patron4");
		super.navigateHome();

		super.clickOnMenu("Patron", "My Patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkNotListingEmpty();
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();
		
		super.signOut();
	}

}
