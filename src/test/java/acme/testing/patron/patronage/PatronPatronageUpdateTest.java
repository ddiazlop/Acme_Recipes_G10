
package acme.testing.patron.patronage;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class PatronPatronageUpdateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int patronageIndex, final String code, final String startDate, final String endDate, final String legalStuff, final String budget, final String info, final String inventorFullName, final String inventorEmail,
		final String inventorCompany, final String inventorStatement, final String inventorInfo) {

		super.signIn("patron3", "patron3");

		super.navigateHome();

		super.clickOnMenu("Patron", "My Patronages");

		super.checkListingExists();
		this.clickOnListingRecord(1);

		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("legalStuff", legalStuff);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("info", info);

		super.clickOnSubmit("Update");

		super.sortListing(0, "asc");

		this.clickOnListingRecord(1);

		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("info", info);

		super.signOut();

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int patronageIndex, final String code, final String startDate, final String endDate, final String legalStuff, final String budget, final String info, final String inventorFullName, final String inventorEmail,
		final String inventorCompany, final String inventorStatement, final String inventorInfo) {

		super.signIn("patron3", "patron3");

		super.navigateHome();

		super.clickOnMenu("Patron", "My Patronages");

		super.checkListingExists();
		this.clickOnListingRecord(1);

		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("legalStuff", legalStuff);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("info", info);

		super.clickOnSubmit("Update");

		super.checkErrorsExist();

	}

}
