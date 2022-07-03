
package acme.testing.patron.patronage;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class PatronPatronageListAndShowTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/list-patronage.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positivePatronageTest(final int recordIndex, final String status, final String code, final String legal_stuff, final String budget, final String creation_date, final String start_date, final String end_date, final String info,
		final String patron, final String inventor, final String inventor_name, final String inventor_surname, final String inventor_email, final String inventor_company, final String inventor_statement, final String inventor_info) {

		super.signIn("patron1", "patron1");
		super.navigateHome();

		super.clickOnMenu("Patron", "My Patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, inventor);

		super.checkColumnHasValue(recordIndex, 2, status);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("creationDate", creation_date);
		super.checkInputBoxHasValue("startDate", start_date);
		super.checkInputBoxHasValue("endDate", end_date);
	
		super.checkInputBoxHasValue("legalStuff", legal_stuff);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("info", info);
		
		super.checkInputBoxHasValue("inventorFullName", inventor_surname+", "+inventor_name);
		super.checkInputBoxHasValue("inventorEmail", inventor_email);
		super.checkInputBoxHasValue("inventorCompany", inventor_company);
		super.checkInputBoxHasValue("inventorStatement", inventor_statement);
		super.checkInputBoxHasValue("inventorInfo", inventor_info);
		super.checkInputBoxHasValue("status", status);

		super.signOut();
	}

}
