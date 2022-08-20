package acme.features.chef.memoranda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefMemorandaShowService implements AbstractShowService<Chef, Memorandum>{

	@Autowired
	protected ChefMemorandaRepository repository;
	
	
	@Override
	public boolean authorise(final Request<Memorandum> request) {
		
		assert request != null;
		
		final int chefId = request.getPrincipal().getActiveRoleId();
		final int memorandumId = request.getModel().getInteger("id");
		
		final Memorandum memorandum = this.repository.findOneById(memorandumId);
		
		return memorandum.getChef().getId() == chefId;
	}

	@Override
	public Memorandum findOne(final Request<Memorandum> request) {
		
		assert request != null;
		
		final int memorandumId = request.getModel().getInteger("id");
		
		final Memorandum memorandum = this.repository.findOneById(memorandumId);
		
		return memorandum;
	}

	@Override
	public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) {

		assert request != null;
		assert model != null;
		assert entity != null;
		
		request.unbind(entity, model, "moment", "sequenceNumber", "report", "info");
		model.setAttribute("epicureUsername", entity.getEpicure().getUserAccount().getUsername());
		model.setAttribute("fineDishCode", entity.getFineDish().getCode());
		
	}

}
