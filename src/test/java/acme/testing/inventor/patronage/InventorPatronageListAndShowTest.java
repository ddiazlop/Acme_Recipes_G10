
package acme.testing.inventor.patronage;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class InventorPatronageListAndShowTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/list-patronage.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positivePatronageTest(final int recordIndex, final String status, final String code, final String legal_stuff, final String budget, final String creation_date, final String start_date, final String end_date, final String info,
		final String patron, final String inventor, final String patron_name, final String patron_surname, final String patron_email, final String patron_company, final String patron_statement, final String patron_info) {

		super.signIn("inventor1", "inventor1");
		super.navigateHome();

		super.clickOnMenu("Inventor", "My Patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, patron);
		super.checkColumnHasValue(recordIndex, 2, status);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("creationDate", creation_date);
		super.checkInputBoxHasValue("startDate", start_date);
		super.checkInputBoxHasValue("endDate", end_date);
		super.checkInputBoxHasValue("status", status);
		
		super.checkInputBoxHasValue("legalStuff", legal_stuff);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("info", info);


		super.checkInputBoxHasValue("patronFullName", patron_surname+", "+patron_name);
		super.checkInputBoxHasValue("patronEmail", patron_email);
		super.checkInputBoxHasValue("patronCompany", patron_company);
		super.checkInputBoxHasValue("patronStatement", patron_statement);
		super.checkInputBoxHasValue("patronInfo", patron_info);

		super.signOut();
	}

}
