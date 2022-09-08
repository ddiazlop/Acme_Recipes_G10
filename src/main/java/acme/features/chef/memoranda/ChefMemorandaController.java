package acme.features.chef.memoranda;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.fineDish.Memoranda;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefMemorandaController extends AbstractController<Chef, Memoranda>{
	
	@Autowired
	protected ChefMemorandaListService listService;
	
	@Autowired
	protected ChefMemorandaListFromFineDishService listFromFineDishService;
	
	@Autowired
	protected ChefMemorandaShowService showService;
	
	@Autowired
	protected ChefMemorandaCreateService createService;
	
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("list-from-fine-dish", "list", this.listFromFineDishService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
	}

}
