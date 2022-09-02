
package acme.features.epicure.fineDish;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.fineDish.FineDish;
import acme.framework.controllers.AbstractController;
import acme.roles.Epicure;

@Controller
public class EpicureFineDishController extends AbstractController<Epicure, FineDish> {

	@Autowired
	protected EpicureFineDishListService	listService;

	@Autowired
	protected EpicureFineDishShowService	showService;


	@PostConstruct
	protected void initialse() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);

	}
}
