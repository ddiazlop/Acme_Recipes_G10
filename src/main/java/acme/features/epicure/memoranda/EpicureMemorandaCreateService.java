package acme.features.epicure.memoranda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Memoranda;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.framework.services.AbstractShowService;
import acme.roles.Epicure;


@Service
public class EpicureMemorandaCreateService implements AbstractCreateService<Epicure, Memoranda> {

	@Autowired
	protected EpicureMemorandaRepository repository;


	@Override
	public boolean authorise(final Request<Memoranda> request) {
		assert request != null;

		return request.getPrincipal().hasRole(Epicure.class);
	}

	@Override
	public void unbind(final Request<Memoranda> request, final Memoranda entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"moment","sequenceNumber","report","info");

	}

	@Override
	public void bind(Request<Memoranda> request, Memoranda entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors,"moment","sequenceNumber","report","info");

		
	}

	@Override
	public Memoranda instantiate(Request<Memoranda> request) {
		Memoranda memoranda;
		memoranda = new Memoranda();
		return memoranda;
	}

	@Override
	public void validate(Request<Memoranda> request, Memoranda entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		boolean confirmation;
		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
		
	}
		


	@Override
	public void create(Request<Memoranda> request, Memoranda entity) {
			this.repository.save(entity);
	}

}