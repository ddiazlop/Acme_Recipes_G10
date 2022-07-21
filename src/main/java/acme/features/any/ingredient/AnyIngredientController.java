package acme.features.any.ingredient;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Ingredient;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyIngredientController extends AbstractController<Any, Ingredient>{
	
	@Autowired
	protected AnyIngredientListService	listService;

	@Autowired
	protected AnyIngredientShowService			showService;


	@PostConstruct
	protected void initialize() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
	}

	

}
