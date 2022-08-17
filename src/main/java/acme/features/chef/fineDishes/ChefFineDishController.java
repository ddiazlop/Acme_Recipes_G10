package acme.features.chef.fineDishes;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.fineDish.FineDish;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefFineDishController extends AbstractController<Chef, FineDish>{

	@Autowired
	protected ChefFineDishListService	listService;
	@Autowired
	protected ChefFineDishShowService	showService;
	
	@PostConstruct
	protected void initialse() {
		//super.addCommand("change-status","update", this.inventorPatronageAcceptService);
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
	}
}
