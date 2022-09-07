package acme.testing.chef.fineDish;

import org.junit.jupiter.api.Test;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class ChefFineDishChangeStatusServiceTest extends TestHarness{
	@Test
	@Order(10)
	public void changeDishStatusTest() {
		super.signIn("chef2", "chef2");
		super.navigateHome();
		
		super.clickOnMenu("Chef", "My Dishes");
		
		super.checkListingExists();
		super.clickOnListingRecord(1);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("status", "PROPOSED");
		super.checkButtonExists("Accept Dish");
		super.checkButtonExists("Deny Dish");
		super.clickOnButton("Accept Dish");
		
		super.checkListingExists();
		super.clickOnListingRecord(1);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("status", "ACCEPTED");
		super.checkNotButtonExists("Accept Dish");
		super.checkNotButtonExists("Deny Dish");
		super.signOut();
	}
}
