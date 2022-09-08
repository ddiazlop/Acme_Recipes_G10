package acme.testing.authenticated.bulletin;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedBulletinListAndShowTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/bulletin/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(100)
	public void positiveBulletinTest(final int recordIndex, final String heading, final String moment,
		final String text, final String critical, final String link) {

		super.signIn("chef1", "chef1");
		super.clickOnMenu("Authenticated", "Bulletins");

		super.checkListingExists();
		
		super.sortListing(0, "desc");

		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, heading);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("moment", moment);
		super.checkInputBoxHasValue("text", text);
		super.checkInputBoxHasValue("critical", critical);
		super.checkInputBoxHasValue("link", link);

		super.signOut();
	}

}
