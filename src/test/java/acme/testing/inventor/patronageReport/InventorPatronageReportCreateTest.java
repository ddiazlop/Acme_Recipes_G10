
package acme.testing.inventor.patronageReport;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class InventorPatronageReportCreateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage-report/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int reportIndex, final int recordIndex, final String reference, final String memorandum, final String info) {

		super.signIn("inventor1", "inventor1");

		super.navigateHome();

		//patronages
		super.clickOnMenu("Inventor", "My Patronages");
		this.clickOnListingRecord(reportIndex);
		super.clickOnButton("Create Report");

		super.fillInputBoxIn("memorandum", memorandum);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("confirmation", "true");

		super.clickOnSubmit("Create");

		super.clickOnButton("Reports");

		//reports
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 1, memorandum);
		super.checkColumnHasValue(recordIndex, 2, info);
		this.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("memorandum", memorandum);
		super.checkInputBoxHasValue("info", info);

		super.signOut();

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage-report/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int reportIndex, final int recordIndex, final String reference, final String memorandum, final String info) {

		super.signIn("inventor1", "inventor1");

		super.navigateHome();

		super.clickOnMenu("Inventor", "My Patronages");
		this.clickOnListingRecord(reportIndex);
		super.clickOnButton("Create Report");

		super.fillInputBoxIn("memorandum", memorandum);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("confirmation", "true");

		super.clickOnSubmit("Create");
		super.checkErrorsExist();
		super.signOut();

	}
	
	@Test
	@Order(30)
	public void hackingTest() {

		super.navigate("/inventor/patronage-report/create");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/inventor/patronage-report/create");
		super.checkPanicExists();
		super.signOut();

		super.signIn("patron1", "patron1");
		super.navigate("/inventor/patronage-report/create");
		super.checkPanicExists();
		super.signOut();
	}
}
	

