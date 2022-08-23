
package acme.features.authenticated.systemConfigurationSep;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.components.configuration.SystemConfigurationSep;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;

@Controller
public class AuthenticatedSystemConfigurationSepController extends AbstractController<Authenticated, SystemConfigurationSep> {

	@Autowired
	protected AuthenticatedSystemConfigurationSepShowService showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);

	}
}
