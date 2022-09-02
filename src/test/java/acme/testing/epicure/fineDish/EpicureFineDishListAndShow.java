
package acme.testing.epicure.fineDish;

import java.util.Date;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.framework.datatypes.Money;
import acme.testing.TestHarness;

public class EpicureFineDishListAndShow extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/fineDish/list-and-show-test.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveRecipeTest(final int recordIndex, final String code, final String request, final String budget, 
		final String creationDate, final String startDate,final String endDate, final String info, final String state) {
		
		super.signIn("epicure1", "epicure1");
		super.navigateHome();
		
		super.clickOnMenu("FineDish", "FineDIsh");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, request);
		super.checkColumnHasValue(recordIndex, 2, budget);
		super.checkColumnHasValue(recordIndex, 3, creationDate);
		super.checkColumnHasValue(recordIndex, 4, startDate);
		super.checkColumnHasValue(recordIndex, 5, endDate);
		super.checkColumnHasValue(recordIndex, 6, info);
		super.checkColumnHasValue(recordIndex, 7, state);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("request", request);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("creationDate", creationDate);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("info", info);
	}

}
