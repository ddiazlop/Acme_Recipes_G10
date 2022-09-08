
package acme.testing.administrator.systemConfigurationSep;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class AdministratorSystemConfigurationSepListAndShowTest extends TestHarness {

	@Test
	@Order(100)
	public void hackingTest() {

		super.navigate("/administrator/systemConfigurationSep/show");
		super.checkPanicExists();

		super.signIn("chef1", "chef1");
		super.navigate("/administrator/systemConfigurationSep/show");
		super.checkPanicExists();
		super.signOut();

		super.signIn("epicure1", "epicure1");
		super.navigate("/administrator/systemConfigurationSep/show");
		super.checkPanicExists();
		super.signOut();
	}

}
