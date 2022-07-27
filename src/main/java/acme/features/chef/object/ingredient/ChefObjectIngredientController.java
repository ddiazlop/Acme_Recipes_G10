package acme.features.chef.object.ingredient;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Object;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefObjectIngredientController extends AbstractController<Chef, Object>{

	@Autowired
	protected ChefObjectIngredientListService	listService;

	@Autowired
	protected ChefObjectIngredientShowService	showService;
	
	@Autowired
	protected ChefObjectIngredientCreateService createService;
	
	@Autowired
	protected ChefObjectIngredientUpdateService updateService;
	
	@Autowired
	protected ChefObjectIngredientPublishService publishService;
	
	@Autowired
	protected ChefObjectIngredientDeleteService deleteService;


	@PostConstruct
	protected void initialize() {
		super.addCommand("list-ingredient", "list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create-ingredient", "create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("publish", "update", this.publishService);
		super.addCommand("delete", this.deleteService);
	}

}
