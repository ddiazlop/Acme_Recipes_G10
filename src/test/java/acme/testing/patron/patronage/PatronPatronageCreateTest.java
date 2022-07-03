
package acme.testing.patron.patronage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class PatronPatronageCreateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int patronageIndex, final String code, final String startDate, final String endDate, final String legalStuff, final String budget, final String info, final String inventorFullName, final String inventorEmail,
		final String inventorCompany, final String inventorStatement, final String inventorInfo) {

		super.signIn("patron3", "patron3");

		super.navigateHome();

		super.clickOnMenu("Patron", "My Patronages");
		super.clickOnButton("Create");

		super.fillInputBoxIn("code", code);

		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("legalStuff", legalStuff);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("info", info);

		super.clickOnSubmit("Create");

		super.sortListing(0, "asc");

		this.clickOnListingRecord(patronageIndex); //1

		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("info", info);
		super.checkInputBoxHasValue("inventorFullName", inventorFullName);
		super.checkInputBoxHasValue("inventorEmail", inventorEmail);
		super.checkInputBoxHasValue("inventorCompany", inventorCompany);
		super.checkInputBoxHasValue("inventorStatement", inventorStatement);
		super.checkInputBoxHasValue("inventorInfo", inventorInfo);

		super.signOut();

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int patronageIndex, final String code, final String startDate, final String endDate, final String legalStuff, final String budget, final String info, final String inventorFullName, final String inventorEmail,
		final String inventorCompany, final String inventorStatement, final String inventorInfo) {

		super.signIn("patron3", "patron3");

		super.navigateHome();

		super.clickOnMenu("Patron", "My Patronages");
		super.clickOnButton("Create");

		super.fillInputBoxIn("code", code);

		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("legalStuff", legalStuff);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("info", info);

		super.clickOnSubmit("Create");
		super.checkErrorsExist();
	}

	@Test
	@Order(30)
	public void hackingTest() {

		super.navigate("/patron/patronage/create");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/patron/patronage/create");
		super.checkPanicExists();
		super.signOut();

		super.signIn("inventor1", "inventor1");
		super.navigate("/patron/patronage/create");
		super.checkPanicExists();
		super.signOut();
	}

}
