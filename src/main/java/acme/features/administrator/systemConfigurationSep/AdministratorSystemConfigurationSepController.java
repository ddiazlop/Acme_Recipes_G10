
package acme.features.administrator.systemConfigurationSep;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.components.configuration.SystemConfigurationSep;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Administrator;

@Controller
public class AdministratorSystemConfigurationSepController extends AbstractController<Administrator, SystemConfigurationSep> {

	@Autowired
	protected AdministratorSystemConfigurationSepShowService showService;
	
	@Autowired
	protected AdministratorSystemConfigurationSepUpdateService	updateService;
	
	

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("update", this.updateService);
	}

}
