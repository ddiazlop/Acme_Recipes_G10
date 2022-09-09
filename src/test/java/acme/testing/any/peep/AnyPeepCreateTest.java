package acme.testing.any.peep;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyPeepCreateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/any/peep/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(100)
	public void positiveCreateTest(final int recordIndex, final String heading, final String writer, final String text,
										final String email) {
		
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Peeps");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnButton("Create");
		super.fillInputBoxIn("heading",  heading);
		super.fillInputBoxIn("writer",  writer);
		super.fillInputBoxIn("email",  email);
		super.fillInputBoxIn("text",  text);

		
		super.fillInputBoxIn("confirmation", "true");
		super.clickOnSubmit("Create");
		super.checkNotErrorsExist();
		
	}
	@ParameterizedTest
	@CsvFileSource(resources = "/any/peep/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(100)
	public void negativeCreateTest(final int recordIndex, final String heading, final String writer, final String text,
										final String email) {
		
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Peeps");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnButton("Create");

		super.fillInputBoxIn("heading",  heading);
		super.fillInputBoxIn("writer",  writer);
		super.fillInputBoxIn("email",  email);
		super.fillInputBoxIn("text",  text);

		
		super.fillInputBoxIn("confirmation", "true");
		super.clickOnSubmit("Create");
		super.checkErrorsExist();
		
	}

}