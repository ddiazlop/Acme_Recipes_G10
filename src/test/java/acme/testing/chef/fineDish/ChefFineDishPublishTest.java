
package acme.testing.chef.fineDish;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class ChefFineDishPublishTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/fineDish/publish-fineDish-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveRecipeTest(final int recordIndex) {

		super.navigateHome();

        super.signIn("chef4", "chef4");
        super.clickOnMenu("Chef", "My FineDishes");

        super.checkListingExists();
        super.sortListing(0, "asc");

        super.clickOnListingRecord(recordIndex);
        super.checkFormExists();
        super.clickOnSubmit("Publish");
        super.checkNotErrorsExist();
        
        super.clickOnListingRecord(recordIndex);
        super.checkNotSubmitExists("Publish");
    
        super.signOut();

	}
}