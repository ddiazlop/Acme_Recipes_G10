package acme.features.epicure.memoranda;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Memoranda;
import acme.framework.controllers.AbstractController;
import acme.roles.Epicure;


@Controller
public class EpicureMemorandaController extends AbstractController<Epicure, Memoranda> {

	@Autowired
	protected EpicureMemorandaListService	listService;

	@Autowired
	protected EpicureMemorandaShowService	showService;
	
	@Autowired
	protected EpicureMemorandaCreateService createService;


	@PostConstruct
	protected void initialse() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);

	}
}