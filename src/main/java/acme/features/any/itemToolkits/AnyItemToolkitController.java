
package acme.features.any.itemToolkits;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.toolkits.ItemToolkit;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyItemToolkitController extends AbstractController<Any, ItemToolkit> {

	@Autowired
	protected AnyItemFromToolkitListService anyItemFromToolkitListService;


	@PostConstruct
	protected void initialize() {
		super.addCommand("list", this.anyItemFromToolkitListService);
	}
}
