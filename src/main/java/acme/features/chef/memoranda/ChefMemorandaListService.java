package acme.features.chef.memoranda;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefMemorandaListService implements AbstractListService<Chef, Memorandum>{

	@Autowired
	protected ChefMemorandaRepository repository;
	
	
	@Override
	public boolean authorise(final Request<Memorandum> request) {
		
		assert request != null;
		return true;
	}

	@Override
	public Collection<Memorandum> findMany(final Request<Memorandum> request) {
		
		assert request != null;
		final int chefId = request.getPrincipal().getActiveRoleId();
		final Collection<Memorandum> memorandum;
		memorandum = this.repository.findMemorandaByChefId(chefId);
		
		return memorandum;
	}

	@Override
	public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) {
		assert entity != null;
		assert request != null;
		assert model != null;
		
		request.unbind(entity, model, "moment", "sequenceNumber");
		model.setAttribute("epicureUsername", entity.getEpicure().getUserAccount().getUsername());
		model.setAttribute("fineDishCode", entity.getFineDish().getCode());
		
	}

}
