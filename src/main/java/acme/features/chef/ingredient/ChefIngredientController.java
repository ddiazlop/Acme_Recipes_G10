package acme.features.chef.ingredient;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Ingredient;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefIngredientController extends AbstractController<Chef, Ingredient>{

	@Autowired
	protected ChefIngredientListService	listService;

	@Autowired
	protected ChefIngredientShowService	showService;
	
	@Autowired
	protected ChefIngredientCreateService createService;


	@PostConstruct
	protected void initialize() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
	}

}
