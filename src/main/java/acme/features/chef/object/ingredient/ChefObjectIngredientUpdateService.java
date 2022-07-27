package acme.features.chef.object.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Object;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefObjectIngredientUpdateService implements AbstractUpdateService<Chef, Object>{
	
	@Autowired
	protected ChefObjectIngredientRepository repo;
	
	@Autowired
	protected AdministratorSystemConfigurationRepository systemConfigRepository;

	@Override
	public boolean authorise(final Request<Object> request) {
		assert request != null;
		int chefId;
		int ingredientId;
		Object ingredient;
		
		chefId = request.getPrincipal().getActiveRoleId();
		ingredientId = request.getModel().getInteger("id");
		ingredient = this.repo.findOneIngredientById(ingredientId);
		
		return ingredient.getChef().getId() == chefId && !ingredient.isPublished();
	}

	@Override
	public void bind(final Request<Object> request, final Object entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "name", "description", "retailPrice", "info");
		
	}

	@Override
	public void unbind(final Request<Object> request, final Object entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("published", entity.isPublished());
		request.unbind(entity, model, "code", "name", "objectType", "description", "retailPrice", "info", "published");
		
	}

	@Override
	public Object findOne(final Request<Object> request) {
		assert request != null;
		int id;
		Object ingredient;
		id = request.getModel().getInteger("id");
		ingredient = this.repo.findOneIngredientById(id);
		
		return ingredient;
	}

	@Override
	public void validate(final Request<Object> request, final Object entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;		

		
		if (!errors.hasErrors("retailPrice")) {
			final String currency = entity.getRetailPrice().getCurrency();
			final boolean currencyIsSuported = this.systemConfigRepository.findSystemConfiguration().getAcceptedCurrencies().contains(currency);
			errors.state(request, currencyIsSuported, "retailPrice", "chef.ingredient.form.error.retailPrice.currency-not-supported");
			errors.state(request, entity.getRetailPrice().getAmount()>0, "retailPrice", "chef.ingredient.form.error.retailPrice.negativeOrZero");
		}
		
	}

	@Override
	public void update(final Request<Object> request, final Object entity) {
		assert request != null;
		assert entity != null;
		
		this.repo.save(entity);
		
	}

	

}
