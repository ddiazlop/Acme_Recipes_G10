
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
	
	@Autowired
	protected AdministratorSystemConfigurationSepAcceptedCurrenciesService	acceptedCurrenciesService;
	
	@Autowired
	protected AdministratorSystemConfigurationSepSystemCurrencyService	systemCurrencyService;
	
	@Autowired
	protected AdministratorSystemConfigurationSepSpamService	spamService;

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
