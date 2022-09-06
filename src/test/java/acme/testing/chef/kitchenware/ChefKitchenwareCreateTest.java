package acme.testing.chef.kitchenware;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefKitchenwareCreateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/kitchenware/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(40)
	public void positiveKitchenwareCreateTest(final String name, final String code, 
		final String description, final String retailPrice, final String info) {

		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My Kitchenwares");

		super.checkListingExists();
		super.checkButtonExists("Create");
		super.clickOnButton("Create");
		super.checkFormExists();

		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmit("Create");
		
		super.checkNotErrorsExist();
		super.signOut();

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/kitchenware/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(50)
	public void negativeKitchenwareCreateTest(final int recordIndex, final String name, final String code, 
		final String description, final String retailPrice, final String info) {

		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My Kitchenwares");

		super.checkListingExists();
		super.checkButtonExists("Create");
		super.clickOnButton("Create");
		super.checkFormExists();

		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("info", info);
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();
		super.signOut();
		

	}
	
	@Test
	@Order(60)
	public void hackingTest() {
		final String pathCreateKitchenware= "/chef/kitchenware/create";
		
		
		super.checkNotLinkExists("Chef");
		super.navigate(pathCreateKitchenware);
		super.checkPanicExists();
		
		super.signIn("administrator", "administrator");
		super.checkNotLinkExists("Chef");
		super.navigate(pathCreateKitchenware);
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("epicure1", "epicure1");
		super.checkNotLinkExists("Chef");
		super.navigate(pathCreateKitchenware);
		super.checkPanicExists();
		
		super.signOut();
		
	}

}
