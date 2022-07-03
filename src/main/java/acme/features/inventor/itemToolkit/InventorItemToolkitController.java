package acme.features.inventor.itemToolkit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.toolkits.ItemToolkit;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorItemToolkitController extends AbstractController<Inventor, ItemToolkit>{
	
	//Internal State --------------------------------------------------------------------------------------------------
	
	@Autowired
	protected InventorItemToolkitCreateService createService;
	
	@Autowired 
	protected InventorItemToolkitListService listService;
	
	@Autowired
	protected InventorItemToolkitShowService showService;
	
	@Autowired
	protected InventorItemToolkitDeleteService deleteService;
	
	@Autowired
	protected InventorItemToolkitUpdateService updateService;
	
	// Constructors ---------------------------------------------------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list", this.listService);
		super.addCommand("create", this.createService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("update", this.updateService);
	}
}
