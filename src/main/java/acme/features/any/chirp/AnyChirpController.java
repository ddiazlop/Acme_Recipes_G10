
package acme.features.any.chirp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Chirp;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyChirpController extends AbstractController<Any, Chirp> {

	@Autowired
	protected AnyChirpListService service;
	
	@Autowired
	protected AnyChirpCreateService createService;


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.service);
		super.addCommand("create", this.createService);

	}
}
