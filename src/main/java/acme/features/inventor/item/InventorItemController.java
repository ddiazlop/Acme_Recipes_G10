
package acme.features.inventor.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.toolkits.Item;
import acme.features.inventor.item.component.InventorComponentCreateService;
import acme.features.inventor.item.component.InventorComponentListAllService;
import acme.features.inventor.item.tool.InventorToolCreateService;
import acme.features.inventor.item.tool.InventorToolListAllService;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorItemController extends AbstractController<Inventor, Item> {

	@Autowired
	protected InventorComponentListAllService	inventorComponentListAllService;

	@Autowired
	protected InventorToolListAllService		inventorToolListAllService;

	@Autowired
	protected InventorItemShowService inventorItemShowService;
	
	@Autowired
	protected InventorComponentCreateService inventorComponentCreateService;
	
	@Autowired
	protected InventorToolCreateService inventorToolCreateService;
	
	@Autowired
	protected InventorItemUpdateService inventorItemUpdateService;
	
	@Autowired
	protected InventorItemPublishService inventorItemPublishService;
	
	@Autowired
	protected InventorItemDeleteService inventorItemDeleteService;
	
	@PostConstruct
	protected void initialize() {
		super.addCommand("create-component","create", this.inventorComponentCreateService);
		super.addCommand("create-tool","create", this.inventorToolCreateService);
		super.addCommand("update", this.inventorItemUpdateService);
		super.addCommand("publish","update", this.inventorItemPublishService);
		super.addCommand("delete", this.inventorItemDeleteService);
		
		
		super.addCommand("list-component","list", this.inventorComponentListAllService);
		super.addCommand("list-tool", "list", this.inventorToolListAllService);
		super.addCommand("show", this.inventorItemShowService);

		

	}

}
