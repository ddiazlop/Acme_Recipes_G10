package acme.features.chef.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Ingredient;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefIngredientCreateService implements AbstractCreateService<Chef, Ingredient>{
	
	@Autowired
	protected ChefIngredientRepository repo;
	
	@Autowired
	protected AdministratorSystemConfigurationRepository systemConfigRepository;

	@Override
	public boolean authorise(final Request<Ingredient> request) {
		assert request != null;
		
		return request.getPrincipal().hasRole(Chef.class);
	}

	@Override
	public void bind(final Request<Ingredient> request, final Ingredient entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "name", "description", "retailPrice", "info");
		
	}

	@Override
	public void unbind(final Request<Ingredient> request, final Ingredient entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "name", "description", "retailPrice", "info");
		
	}

	@Override
	public Ingredient instantiate(final Request<Ingredient> request) {
		Ingredient ingredient;
		Chef chef;
		int chefId;
		chefId = request.getPrincipal().getActiveRoleId();
		chef = this.repo.findOneChefById(chefId);
		
		ingredient = new Ingredient();
		ingredient.setDescription("");
		ingredient.setName("");
		ingredient.setInfo("");
		ingredient.setPublished(false);
		ingredient.setChef(chef);
		
		return ingredient;
	}

	@Override
	public void validate(final Request<Ingredient> request, final Ingredient entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		if(!errors.hasErrors("code")) {
			Ingredient existing;
			String code;
			code = entity.getCode();
			existing = this.repo.findOneIngredientByCode(code);
			
			errors.state(request, existing == null ||  entity.getId() == existing.getId(), "code", "chef.ingredient.error.duplicated");
		}
		

		
		if (!errors.hasErrors("retailPrice")) {
			final String currency = entity.getRetailPrice().getCurrency();
			final boolean currencyIsSuported = this.systemConfigRepository.findSystemConfiguration().getAcceptedCurrencies().contains(currency);
			errors.state(request, currencyIsSuported, "retailPrice", "chef.ingredient.form.error.retailPrice.currency-not-supported");
			errors.state(request, entity.getRetailPrice().getAmount()>0, "retailPrice", "chef.ingredient.form.error.retailPrice.negativeOrZero");
		}
		
	}

	@Override
	public void create(final Request<Ingredient> request, final Ingredient entity) {
		this.repo.save(entity);
		
	}

	

}
