package acme.features.chef.fineDish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.DishStatus;
import acme.entities.fineDish.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefFineDishChangeStatusService implements AbstractUpdateService<Chef, FineDish>{

	@Autowired
	ChefFineDishRepository 			repository;
	
	
	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;
		
		final int fineDishId = request.getModel().getInteger("id");
		final int chefId = request.getPrincipal().getActiveRoleId();
		
		final FineDish fineDish = this.repository.findFineDishById(fineDishId);
		
		return fineDish.getChef().getId() == chefId;
	}

	@Override
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		request.bind(entity, errors, "code", "request", "budget", "creationDate", "startDate", "endDate", "info", "status");
		
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		request.unbind(entity, model, "code", "request", "budget", "creationDate", "startDate", "endDate", "info", "status");
		
	}

	@Override
	public FineDish findOne(final Request<FineDish> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		
		return this.repository.findFineDishById(id);
	}

	@Override
	public void validate(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		if (entity.getStatus().equals(DishStatus.ACCEPTED) || entity.getStatus().equals(DishStatus.DENIED)) {
			errors.state(request, entity.getStatus().equals(DishStatus.PROPOSED), "status", "chef.fineDish.errors.already-stated");
		}
		
	}

	@Override
	public void update(final Request<FineDish> request, final FineDish entity) {
		final String operation = request.getModel().getString("operation");
		if (operation.equals("accept")) {
			entity.setStatus(DishStatus.ACCEPTED);
		}else if(operation.equals("deny")){
			entity.setStatus(DishStatus.DENIED);
		}
		this.repository.save(entity);
		
	}

}
