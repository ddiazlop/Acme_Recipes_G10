package acme.testing.chef.kitchenware;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefKitchenwareUpdateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/kitchenware/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(70)
	public void positiveKitchenwareUpdateTest(final String name,  
		final String description, final String retailPrice, final String info) {

		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My Kitchenwares");

		super.checkListingExists();
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.checkSubmitExists("Update");
		
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmit("Update");
		
		super.checkNotErrorsExist();
		super.signOut();

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/kitchenware/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(80)
	public void negativeKitchenwareUpdateTest(final int recordIndex, final String name, 
		final String description, final String retailPrice, final String info) {

		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My Kitchenwares");

		super.checkListingExists();
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.checkSubmitExists("Update");
		
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmit("Update");
		
		super.checkErrorsExist();
		super.signOut();
		

	}


}
