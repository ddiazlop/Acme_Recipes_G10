package acme.features.chef.pimpam;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Delor;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefDelorController extends AbstractController<Chef, Delor>{
	
	@Autowired
	protected ChefDelorListService listService;
	
	@Autowired
	protected ChefDelorShowService showService;
	
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
	}
}
