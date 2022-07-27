package acme.features.any.object.ingredient;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Object;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyObjectIngredientController extends AbstractController<Any, Object>{
	
	@Autowired
	protected AnyObjectIngredientListService	listService;

	@Autowired
	protected AnyObjectIngredientShowService			showService;


	@PostConstruct
	protected void initialize() {
		super.addCommand("list-ingredient", "list", this.listService);
		super.addCommand("show", this.showService);
	}

	

}
