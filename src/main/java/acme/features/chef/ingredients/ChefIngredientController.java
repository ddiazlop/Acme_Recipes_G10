package acme.features.chef.ingredients;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Ingredient;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefIngredientController extends AbstractController<Chef, Ingredient>{
	
	@Autowired
	protected ChefIngredientShowService	showService;

	@Autowired
	protected ChefIngredientListService	listService;
	
	@PostConstruct
	protected void initialize() {
		super.addCommand("show", this.showService);
		super.addCommand("list", this.listService);
	}
	
}
