package acme.features.chef.kitchenwareRecipe;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.recipes.KitchenwareRecipe;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefKitchenwareRecipeController extends AbstractController<Chef, KitchenwareRecipe>{

	@Autowired
	protected ChefKitchenwareRecipeShowService showService;
	
	@Autowired
	protected ChefKitchenwareRecipeListService listService;
	
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list", this.listService);
		/*super.addCommand("create", this.createService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("update", this.updateService);*/
	}
}
