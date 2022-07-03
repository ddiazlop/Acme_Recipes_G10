
package acme.testing.any.chirp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class AnyChirpListTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/list-chirp.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)

	public void positiveChirpTest(final int recordIndex, final String moment,
		final String title, final String author, final String body, final String email) {

		super.navigateHome();
		super.clickOnMenu("Anonymous", "Chirps");
		
		super.checkListingExists();
		super.sortListing(2, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, author);
		super.checkColumnHasValue(recordIndex, 3, body);
		super.checkColumnHasValue(recordIndex, 4, email);

			
	}
	
	
}
