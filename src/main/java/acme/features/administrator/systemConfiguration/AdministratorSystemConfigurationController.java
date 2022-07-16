
package acme.features.administrator.systemConfiguration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.components.configuration.SystemConfiguration;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Administrator;

@Controller
public class AdministratorSystemConfigurationController extends AbstractController<Administrator, SystemConfiguration> {

	@Autowired
	protected AdministratorSystemConfigurationShowService showService;
	
	@Autowired
	protected AdministratorSystemConfigurationUpdateService	updateService;
	
	@Autowired
	protected AdministratorSystemConfigurationAcceptedCurrenciesService	acceptedCurrenciesService;
	
	@Autowired
	protected AdministratorSystemConfigurationSystemCurrencyService	systemCurrencyService;
	
	@Autowired
	protected AdministratorSystemConfigurationSpamService	spamService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("accepted-currencies", "update", this.acceptedCurrenciesService);
		super.addCommand("system-currency", "update", this.systemCurrencyService);
		super.addCommand("spam", "update", this.spamService);
		super.addCommand("update", this.updateService);
	}

}
