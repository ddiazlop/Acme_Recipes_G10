
package acme.features.epicure.fineDish;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Memoranda;
import acme.entities.fineDish.DishStatus;
import acme.entities.fineDish.FineDish;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;
import acme.roles.Epicure;

@Service
public class EpicureFineDishDeleteService implements AbstractDeleteService<Epicure, FineDish> {

	@Autowired
	public EpicureFineDishRepository repository;
	
	@Autowired
	protected AdministratorSystemConfigurationRepository administratorSystemConfigurationRepository;

	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;
		
		int epicureId;
		int fineDishId;
		Epicure epicure;
		FineDish fineDish;
		
		fineDishId = request.getModel().getInteger("id");
		fineDish = this.repository.findOneFineDishById(fineDishId);
		epicureId = request.getPrincipal().getActiveRoleId();
		epicure = this.repository.findOneEpicureById(epicureId);

		return fineDish.getEpicure().equals(epicure) && !fineDish.isPublished();
	}
	
	@Override
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors,"status", "code","request","budget","creationDate","startDate", 
				 "endDate","info", "published");
		

	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"status", "code","request","budget","creationDate","startDate", 
				 "endDate","info", "published");
		
		final Chef chef = entity.getChef();
		model.setAttribute("PROPOSED", DishStatus.PROPOSED);
		model.setAttribute("ACCEPTED", DishStatus.ACCEPTED);
		model.setAttribute("DENIED", DishStatus.DENIED);
		model.setAttribute("chef.username", chef.getUserAccount().getUsername());
		model.setAttribute("chef.organisation", chef.getOrganisation());
		model.setAttribute("chef.assertion", chef.getAssertion());
		model.setAttribute("chef.link", chef.getLink());
		model.setAttribute("readOnly", true);
	}
	
	@Override
	public FineDish findOne(final Request<FineDish> request) {
		assert request != null;

		FineDish result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneFineDishById(id);
		return result;
	}

	
	@Override
	public void validate(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		
		assert request != null;
		assert entity != null;
		assert errors != null;
	}


	
	@Override
	public void delete(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;
		
		final Collection<Memoranda> memoranda = this.repository.findManyMemorandaByFineDishId(entity.getId());
		this.repository.deleteAll(memoranda);

		this.repository.delete(entity);


	}
	
	
}
