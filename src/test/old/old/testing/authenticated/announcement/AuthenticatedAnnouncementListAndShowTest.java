package old.testing.authenticated.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import old.testing.TestHarness;


public class AuthenticatedAnnouncementListAndShowTest extends TestHarness{
		
		
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/announcement/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveAnnouncementTest(final int recordIndex, final String title, final String moment, 
		final String body, final String critical, final String link) {

		super.signIn("patron1", "patron1");
		super.navigateHome();
		
		super.clickOnMenu("Authenticated", "Announcements");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, moment);
		super.checkColumnHasValue(recordIndex, 2, body);
		
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("body", body);
		super.checkInputBoxHasValue("critical", critical);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		
		super.navigate("/authenticated/announcement/list");
		super.checkPanicExists();
		
	}

}
