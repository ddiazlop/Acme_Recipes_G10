
package acme.testing.chef.fineDish;

import java.util.Date;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class ChefFineDishUpdateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/fineDish/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex,final String status, final String code, final String request, 
			final Money budget, final Date creationDate, final Date startDate, final Date endDate, final String info) {
		
		super.signIn("chef3", "chef3");

		super.navigateHome();

		super.clickOnMenu("Chef", "My FineDishes");

		super.checkListingExists();
		this.clickOnListingRecord(1);
		

		super.fillInputBoxIn("status", status);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("request", request);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("creationDate", creationDate);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("info", info);


		
		super.clickOnSubmit("Update");

		super.sortListing(0, "asc");

		this.clickOnListingRecord(1);


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
	@CsvFileSource(resources = "/chef/fineDish/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex,final String status, final String code, final String request, 
			final Money budget, final Date creationDate, final Date startDate, final Date endDate, final String info) {

		super.signIn("chef3", "chef3");

		super.navigateHome();

		super.clickOnMenu("Chef", "My FineDishes");

		super.checkListingExists();
		this.clickOnListingRecord(1);

		super.fillInputBoxIn("status", status);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("request", request);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("creationDate", creationDate);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("info", info);



		super.clickOnSubmit("Update");

		super.checkErrorsExist();

	}

}
