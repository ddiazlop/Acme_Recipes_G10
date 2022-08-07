package acme.features.any.kitchenware.ingredient;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.recipes.Kitchenware;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyKitchenwareIngredientController extends AbstractController<Any, Kitchenware>{
	
	@Autowired
	protected AnyKitchenwareIngredientListService	listService;

	@Autowired
	protected AnyKitchenwareIngredientShowService			showService;


	@PostConstruct
	protected void initialize() {
		super.addCommand("list-ingredient", "list", this.listService);
		super.addCommand("show", this.showService);
	}

	

}
