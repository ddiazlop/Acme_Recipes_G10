package acme.testing.any.peep;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyPeepListAllTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/any/peep/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(100)
	public void positiveListAllTest(final int recordIndex, final String heading, final String writer, final String text,
										final String email) {
		
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Peeps");
		
		super.checkListingExists();
		super.sortListing(0, "desc");


		super.checkColumnHasValue(recordIndex, 1, heading);
		super.checkColumnHasValue(recordIndex, 2, writer);
		super.checkColumnHasValue(recordIndex, 3, text);
		super.checkColumnHasValue(recordIndex, 4, email);
		
		
	}

}