package acme.testing.chef.kitchenware;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefKitchenwareListAndShowTest extends TestHarness{

	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/kitchenware/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(100)
	public void positiveChefKitchenwaresTest(final int recordIndex, final String wareType, 
		final String name, final String code, final String description, 
		final String retailPrice, final String info) {
		
		super.signIn("chef3", "chef3");
		
		super.clickOnMenu("Chef", "My Kitchenwares");	
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, wareType);


		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("info", info);
		super.checkInputBoxHasValue("wareType", wareType);
		
		super.signOut();
		
		
	}
	
	@Test
	@Order(100)
	public void hackingTest() {
		final String pathShowKitchenware= "/chef/kitchenware/show";
		
		
		super.checkNotLinkExists("Chef");
		super.navigate(pathShowKitchenware);
		super.checkPanicExists();
		
		super.signIn("administrator", "administrator");
		super.checkNotLinkExists("Chef");
		super.navigate(pathShowKitchenware);
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("epicure1", "epicure1");
		super.checkNotLinkExists("Chef");
		super.navigate(pathShowKitchenware);
		super.checkPanicExists();
		
		super.signOut();
		
	}

}
