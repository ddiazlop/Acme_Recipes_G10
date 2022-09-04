package acme.testing.chef.kitchenware;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefKitchenwareDeleteTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/kitchenware/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void positiveKitchenwareDeleteTest(final int recordIndex) {

		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My Kitchenwares");

		super.checkListingExists();

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();

	}

}
