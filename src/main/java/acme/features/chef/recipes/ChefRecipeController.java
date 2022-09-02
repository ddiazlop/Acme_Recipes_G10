package acme.features.chef.recipes;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.recipes.Recipe;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller	
public class ChefRecipeController extends AbstractController<Chef, Recipe>{
	
	@Autowired
	private ChefRecipeShowService showService;
	
	@Autowired
	private ChefRecipeListService listService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list", this.listService);
		/*super.addCommand("delete", this.deleteService);
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		
		super.addCommand("publish", "update", this.publishService);*/
	}

}
