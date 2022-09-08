package acme.testing.chef.kitchenware;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class ChefKitchenwarePublishTest extends TestHarness{

	@Test
	@Order(90)
	public void positiveKitchenwarePublishTest() {

		super.signIn("chef2", "chef2");
		super.clickOnMenu("Chef", "My Kitchenwares");

		super.checkListingExists();
		super.clickOnListingRecord(2);
		super.checkFormExists();
		super.checkSubmitExists("Publish");
		super.clickOnSubmit("Publish");
		
		super.checkNotErrorsExist();
		super.signOut();

	}

}
