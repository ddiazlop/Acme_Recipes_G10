
package acme.testing.administrator.systemConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class AdministratorSystemConfigurationListAndShowTest extends TestHarness {

	@Test
	@Order(30)
	public void hackingTest() {

		super.navigate("/administrator/systemConfiguration/show");
		super.checkPanicExists();

		super.signIn("patron1", "patron1");
		super.navigate("/administrator/systemConfiguration/show");
		super.checkPanicExists();
		super.signOut();

		super.signIn("inventor1", "inventor1");
		super.navigate("/administrator/systemConfiguration/show");
		super.checkPanicExists();
		super.signOut();
	}

}
