package acme.features.chef.kitchenware;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.recipes.Kitchenware;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefKitchenwareController extends AbstractController<Chef, Kitchenware>{

	@Autowired
	protected ChefKitchenwareListService	listService;

	@Autowired
	protected ChefKitchenwareShowService	showService;


	@PostConstruct
	protected void initialize() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
	}

}
