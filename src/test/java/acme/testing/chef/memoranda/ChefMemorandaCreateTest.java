package acme.testing.chef.memoranda;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefMemorandaCreateTest extends TestHarness{
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/memoranda/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(40)
	public void positiveMemorandaCreateTest(final int redordIndex ,final String report, final String info) {

		super.signIn("chef2", "chef2");
		super.clickOnMenu("Chef", "My Dishes");

		super.checkListingExists();
		super.clickOnListingRecord(0);
		
		super.checkButtonExists("Create New Memorandum");
		super.clickOnButton("Create New Memorandum");
		super.checkFormExists();

		super.fillInputBoxIn("report", report);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("confirmation", "true");
		super.clickOnSubmit("Create Memoranda");
		
		super.checkNotErrorsExist();
		super.signOut();

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/memoranda/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(50)
	public void negativeMemorandaCreateTest(final int recordIndex, final String report, final String info) {

		super.signIn("chef2", "chef2");
		super.clickOnMenu("Chef", "My Dishes");

		super.checkListingExists();
		super.clickOnListingRecord(2);
		
		super.checkButtonExists("Create New Memorandum");
		super.clickOnButton("Create New Memorandum");
		super.checkFormExists();

		super.fillInputBoxIn("report", report);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("confirmation", "true");
		super.clickOnSubmit("Create Memoranda");
		super.checkErrorsExist();
		
		super.signOut();
		

	}
}
