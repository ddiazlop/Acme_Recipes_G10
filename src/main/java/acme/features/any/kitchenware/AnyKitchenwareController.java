package acme.features.any.kitchenware;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.recipes.Kitchenware;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyKitchenwareController extends AbstractController<Any, Kitchenware>{

	@Autowired
	protected AnyKitchenwareListService	listService;

	@Autowired
	protected AnyKitchenwareShowService			showService;

	@PostConstruct
	protected void initialize() {
		super.addCommand("list-ingredient", "list", this.listService);
		super.addCommand("list-utensils", "list", this.listService);
		super.addCommand("show", this.showService);
	}



}