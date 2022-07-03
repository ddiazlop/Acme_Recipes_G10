
package acme.features.any.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.toolkits.Item;
import acme.features.any.item.component.AnyComponentListAllService;
import acme.features.any.item.tool.AnyToolListAllService;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyItemController extends AbstractController<Any, Item> {

	@Autowired
	protected AnyComponentListAllService	anyComponentListAllService;

	@Autowired
	protected AnyItemShowService			anyItemShowService;

	@Autowired
	protected AnyToolListAllService			anyToolListAllService;


	@PostConstruct
	protected void initialize() {
		super.addCommand("list-component", "list", this.anyComponentListAllService);
		super.addCommand("list-tool", "list", this.anyToolListAllService);
		super.addCommand("show", this.anyItemShowService);
	}
}
