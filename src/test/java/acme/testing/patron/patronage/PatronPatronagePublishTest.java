
package acme.testing.patron.patronage;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class PatronPatronagePublishTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/publish-patronage-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positivePatronageTest(final int recordIndex) {

		super.navigateHome();

        super.signIn("patron4", "patron4");
        super.clickOnMenu("Patron", "My Patronages");

        super.checkListingExists();
        super.sortListing(0, "asc");

        super.clickOnListingRecord(recordIndex);
        super.checkFormExists();
        super.clickOnSubmit("Publish");
        super.checkNotErrorsExist();
        
        super.clickOnListingRecord(recordIndex);
        super.checkNotSubmitExists("Publish");
    
        super.signOut();

	}
}
