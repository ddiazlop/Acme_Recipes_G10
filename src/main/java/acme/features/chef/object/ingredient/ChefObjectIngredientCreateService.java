package acme.features.chef.object.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Object;
import acme.entities.ObjectType;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefObjectIngredientCreateService implements AbstractCreateService<Chef, Object>{
	
	@Autowired
	protected ChefObjectIngredientRepository repo;
	
	@Autowired
	protected AdministratorSystemConfigurationRepository systemConfigRepository;

	@Override
	public boolean authorise(final Request<Object> request) {
		assert request != null;
		
		return request.getPrincipal().hasRole(Chef.class);
	}

	@Override
	public void bind(final Request<Object> request, final Object entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "name", "description", "retailPrice", "info");
		
	}

	@Override
	public void unbind(final Request<Object> request, final Object entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "name", "description", "retailPrice", "info");
		
	}

	@Override
	public Object instantiate(final Request<Object> request) {
		assert request != null;
		
		Object ingredient;
		Chef chef;
		int chefId;
		chefId = request.getPrincipal().getActiveRoleId();
		chef = this.repo.findOneChefById(chefId);
		
		ingredient = new Object();
		ingredient.setDescription("");
		ingredient.setName("");
		ingredient.setInfo("");
		ingredient.setPublished(false);
		ingredient.setObjectType(ObjectType.INGREDIENT);
		ingredient.setChef(chef);
		
		return ingredient;
	}

	@Override
	public void validate(final Request<Object> request, final Object entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		if(!errors.hasErrors("code")) {
			Object existing;
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
	public void create(final Request<Object> request, final Object entity) {
		assert request != null;
		assert entity != null;
		
		this.repo.save(entity);
		
	}

	

}
