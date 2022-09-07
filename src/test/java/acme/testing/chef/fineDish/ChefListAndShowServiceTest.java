package acme.testing.chef.fineDish;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class ChefListAndShowServiceTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/fine-dish/list-and-show-test.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveFineDishTest(final int recordIndex, final String code, final String budget, final String request, final String status, final String info) {
		
		super.signIn("chef2", "chef2");
		super.navigateHome();
		
		super.clickOnMenu("Chef", "My Dishes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, budget);
		super.checkColumnHasValue(recordIndex, 4, status);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("request", request);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("info", info);
		super.checkInputBoxHasValue("status", status);
	}
}
