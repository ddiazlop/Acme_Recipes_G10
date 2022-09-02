package acme.features.authenticated.epicure;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;
import acme.roles.Epicure;

@Controller
public class AuthenticatedEpicureController extends AbstractController<Authenticated, Epicure>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedEpicureCreateService	createService;


	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("create", this.createService);
	}

}
