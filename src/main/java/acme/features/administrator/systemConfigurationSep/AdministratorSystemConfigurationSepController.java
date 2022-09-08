
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
	protected AdministratorSystemConfigurationSepSpamShowService showSpamService;
	
	@Autowired
	protected AdministratorSystemConfigurationSepSystemCurrencyShowService showSystemCurrencyService;
	
	@Autowired
	protected AdministratorSystemConfigurationSepAcceptedCurrenciesShowService showAcceptedCurrenciesService;
	
	
	@Autowired
	protected AdministratorSystemConfigurationSepUpdateService	updateService;
	
	@Autowired
	protected AdministratorSystemConfigurationSepSpamUpdateService updateSpamService;
	
	@Autowired
	protected AdministratorSystemConfigurationSepSystemCurrencyUpdateService updateSystemCurrencyService;
	
	@Autowired
	protected AdministratorSystemConfigurationSepAcceptedCurrenciesUpdateService updateaAceptedCurrenciesService;
	
	
	
	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("show-spam","show", this.showSpamService);
		super.addCommand("show-system-currency","show", this.showSystemCurrencyService);
		super.addCommand("show-accepted-currencies","show", this.showAcceptedCurrenciesService);
		
		super.addCommand("update", this.updateService);
		super.addCommand("update-spam", "update", this.updateSpamService);
		super.addCommand("update-system-currency", "update", this.updateSystemCurrencyService);
		super.addCommand("update-accepted-currencies", "update", this.updateaAceptedCurrenciesService);
		
	}

}
