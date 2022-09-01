package acme.testing.chef.fineDish;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class ChefFineDishCreateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/fineDish/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex,final String status, final String code, final String request, 
			final Money budget, final Date creationDate, final Date startDate, final Date endDate, final String info) {
		
		super.signIn("chef3", "chef3");

		super.navigateHome();

		super.clickOnMenu("Chef", "My FineDishes");
		super.clickOnButton("Create");

		super.fillInputBoxIn("status", status);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("request", request);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("creationDate", creationDate);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("info", info);

		super.clickOnSubmit("Create");

		super.sortListing(0, "asc");

		this.clickOnListingRecord(recordIndex); //1

		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("request", request);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("creationDate", creationDate);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("info", info);
		
		super.signOut();

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/fineDish/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex,final String status, final String code, final String request, 
			final Money budget, final Date creationDate, final Date startDate, final Date endDate, final String info) {

		super.signIn("chef3", "chef3");

		super.navigateHome();

		super.clickOnMenu("Chef", "My FineDishes");
		super.clickOnButton("Create");

		super.fillInputBoxIn("status", status);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("request", request);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("creationDate", creationDate);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("info", info);
		
		super.clickOnSubmit("Create");
		super.checkErrorsExist();
	}

	@Test
	@Order(30)
	public void hackingTest() {

		super.navigate("/chef/fineDish/create");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/chef/fineDish/create");
		super.checkPanicExists();
		super.signOut();


	}

}
