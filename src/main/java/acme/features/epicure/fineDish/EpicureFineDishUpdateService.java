
package acme.features.epicure.fineDish;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.FineDish;
import acme.entities.recipes.Kitchenware;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;
import acme.roles.Epicure;

@Service
public class EpicureFineDishUpdateService implements AbstractUpdateService<Epicure, FineDish> {

	@Autowired
	protected EpicureFineDishRepository repository;


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

		return fineDish.getEpicure().equals(epicure);
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
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"status", "code","request","budget","creationDate","startDate", 
				 "endDate","info");
		
		final Chef chef = entity.getChef();
		model.setAttribute("chef.username", chef.getUserAccount().getUsername());
		model.setAttribute("chef.organisation", chef.getOrganisation());
		model.setAttribute("chef.assertion", chef.getAssertion());
		model.setAttribute("chef.link", chef.getLink());

	}

	@Override
	public void bind(Request<FineDish> request, FineDish entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors,"status", "code","request","budget","creationDate","startDate", 
				 "endDate","info");
		
	}

	@Override
	public void validate(Request<FineDish> request, FineDish entity, Errors errors) {

		if (!errors.hasErrors("code")) {
			final FineDish existing = this.repository.findOneFineDishByCode(entity.getCode());
			errors.state(request, existing == null, "code", "chef.recipe.form.error.duplicated-code");
		}

		if (!errors.hasErrors("code")) {
			final Kitchenware existing = this.repository.findOneKitchenwareByCode(entity.getCode());
			errors.state(request, existing == null, "code", "chef.recipe.form.error.duplicated-code");
		}
		
	}

	@Override
	public void update(Request<FineDish> request, FineDish entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

}
