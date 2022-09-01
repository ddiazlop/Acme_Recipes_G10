
package acme.testing.chef.recipe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class ChefRecipeCreateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String heading, 
			final String description, final String preparationNotes, final Boolean published, final String info) {
		
		super.signIn("chef3", "chef3");

		super.navigateHome();

		super.clickOnMenu("Chef", "My Recipes");
		super.clickOnButton("Create");

		super.fillInputBoxIn("code", code);

		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("legalStuff", legalStuff);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("info", info);

		super.clickOnSubmit("Create");

		super.sortListing(0, "asc");

		this.clickOnListingRecord(recordIndex); //1

		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("info", info);
		super.checkInputBoxHasValue("inventorFullName", inventorFullName);
		super.checkInputBoxHasValue("inventorEmail", inventorEmail);
		super.checkInputBoxHasValue("inventorCompany", inventorCompany);
		super.checkInputBoxHasValue("inventorStatement", inventorStatement);
		super.checkInputBoxHasValue("inventorInfo", inventorInfo);

		super.signOut();

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String code, final String heading, final String description, final String preparationNotes, 
			final Boolean published, final String info) {

		super.signIn("chef3", "chef3");

		super.navigateHome();

		super.clickOnMenu("Chef", "My Recipes");
		super.clickOnButton("Create");

		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("preparationNotes", preparationNotes);
		super.fillInputBoxIn("published", published);
		super.fillInputBoxIn("info", info);

		
		super.clickOnSubmit("Create");
		super.checkErrorsExist();
	}

	@Test
	@Order(30)
	public void hackingTest() {

		super.navigate("/chef/recipe/create");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/chef/recipe/create");
		super.checkPanicExists();
		super.signOut();


	}

}
