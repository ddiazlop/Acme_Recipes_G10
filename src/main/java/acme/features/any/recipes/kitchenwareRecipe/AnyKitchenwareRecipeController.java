package acme.features.any.recipes.kitchenwareRecipe;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.recipes.KitchenwareRecipe;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyKitchenwareRecipeController extends AbstractController<Any, KitchenwareRecipe>{
	
	//Internal State --------------------------------------------------------------------------------------------------
	
	@Autowired 
	protected AnyKitchenwareRecipeListService listService;
	
	@Autowired
	protected AnyKitchenwareRecipeShowService showService;
	
	
	// Constructors ---------------------------------------------------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list-ingredients", "list", this.listService);
		super.addCommand("list-utensils", "list", this.listService);
	}
}
