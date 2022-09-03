package acme.features.authenticated.chef;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;
import acme.roles.Chef;

@Controller
public class AuthenticatedChefController extends AbstractController<Authenticated, Chef>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedChefCreateService	createService;
	
	@Autowired
	protected AuthenticatedChefUpdateService	updateService;


	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
	}

}
