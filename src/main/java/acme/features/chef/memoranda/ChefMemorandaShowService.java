package acme.features.chef.memoranda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Memoranda;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefMemorandaShowService implements AbstractShowService<Chef, Memoranda>{

	@Autowired
	protected ChefMemorandaRepository repository;
	
	
	@Override
	public boolean authorise(final Request<Memoranda> request) {
		
		assert request != null;
		
		final int chefId = request.getPrincipal().getActiveRoleId();
		final int memorandumId = request.getModel().getInteger("id");
		
		final Memoranda memoranda = this.repository.findOneById(memorandumId);
		
		return memoranda.getChef().getId() == chefId;
	}

	@Override
	public Memoranda findOne(final Request<Memoranda> request) {
		
		assert request != null;
		
		final int memorandumId = request.getModel().getInteger("id");
		
		final Memoranda memoranda = this.repository.findOneById(memorandumId);
		
		return memoranda;
	}

	@Override
	public void unbind(final Request<Memoranda> request, final Memoranda entity, final Model model) {

		assert request != null;
		assert model != null;
		assert entity != null;
		
		request.unbind(entity, model, "moment", "sequenceNumber", "report", "info");
		model.setAttribute("epicureUsername", entity.getEpicure().getUserAccount().getUsername());
		model.setAttribute("fineDishCode", entity.getFineDish().getCode());
		
	}

}
