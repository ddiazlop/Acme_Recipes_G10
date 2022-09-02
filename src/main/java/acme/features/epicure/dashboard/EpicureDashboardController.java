package acme.features.epicure.dashboard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.forms.EpicureDashboard;
import acme.framework.controllers.AbstractController;
import acme.roles.Epicure;

@Controller
public class EpicureDashboardController extends AbstractController<Epicure, EpicureDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected EpicureDashboardShowService showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
	}

}
