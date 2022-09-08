package acme.features.epicure.memoranda;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.Memoranda;
import acme.features.chef.memoranda.ChefMemorandaRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Epicure;

@Service
public class EpicureMemorandaListFromFineDishService implements AbstractListService<Epicure, Memoranda>{

	@Autowired
	protected ChefMemorandaRepository repository;
	
	
	@Override
	public boolean authorise(final Request<Memoranda> request) {
		
		assert request != null;
		return request.getPrincipal().hasRole(Epicure.class);
	}

	@Override
	public Collection<Memoranda> findMany(final Request<Memoranda> request) {
		
		assert request != null;
		final int fineDish = request.getModel().getInteger("fineDishId");
		final Collection<Memoranda> memoranda;
		memoranda = this.repository.findMemorandaByFineDishId(fineDish);
		
		return memoranda;
	}

	@Override
	public void unbind(final Request<Memoranda> request, final Memoranda entity, final Model model) {
		assert entity != null;
		assert request != null;
		assert model != null;
		
		request.unbind(entity, model, "moment", "sequenceNumber", "chef.userAccount.username");
		
	}

}
