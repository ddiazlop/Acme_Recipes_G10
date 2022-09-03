package acme.features.chef.kitchenware;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipes.Kitchenware;
import acme.entities.recipes.WareType;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefKitchenwareUpdateService implements AbstractUpdateService<Chef, Kitchenware>{

	@Autowired
	protected ChefKitchenwareRepository repository;
	
	@Autowired
	protected AdministratorSystemConfigurationRepository systemConfigRepository;
	
	@Override
	public boolean authorise(final Request<Kitchenware> request) {
		assert request != null;
		int chefId;
		int kitchewareId;
		Kitchenware kitchenware;
		
		chefId = request.getPrincipal().getActiveRoleId();
		kitchewareId = request.getModel().getInteger("id");
		kitchenware = this.repository.findOneKitchenwareById(kitchewareId);
		
		return kitchenware.getChef().getId() == chefId && !kitchenware.isPublished();
	}

	@Override
	public void bind(final Request<Kitchenware> request, final Kitchenware entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "name", "description", "retailPrice", "info");
		
	}

	@Override
	public void unbind(final Request<Kitchenware> request, final Kitchenware entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		request.unbind(entity, model,  "code", "name", "wareType", "description", "retailPrice", "info");
		model.setAttribute("published", entity.isPublished());
		model.setAttribute("IngredientType", WareType.INGREDIENT);
		model.setAttribute("KitchenUtensilType", WareType.KITCHEN_UTENSIL);
		model.setAttribute("readOnly", true);
	}

	@Override
	public Kitchenware findOne(final Request<Kitchenware> request) {
		assert request != null;
		int id;
		Kitchenware kitchenware;
		id = request.getModel().getInteger("id");
		kitchenware = this.repository.findOneKitchenwareById(id);
		
		return kitchenware;
	}

	@Override
	public void validate(final Request<Kitchenware> request, final Kitchenware entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;		

		
		if(!errors.hasErrors("published")) {
			errors.state(request, !entity.isPublished(), "published", 
				"chef.kitchenware.form.error.update.published-kitchenware");
		}
		
		if (!errors.hasErrors("retailPrice")) {
			final String[] acceptedCurrencies=this.systemConfigRepository.findAcceptedCurrencies().split(",");
			final List<String> currencies = Arrays.asList(acceptedCurrencies);
			errors.state(request, currencies.contains(entity.getRetailPrice().getCurrency()), 
				"retailPrice", "chef.kitchenware.form.error.retailPrice.currency-not-supported");
			errors.state(request, entity.getRetailPrice().getAmount()>0., "retailPrice", "chef.kitchenware.form.error.retailPrice.negativeOrZero");
		}
		
	}

	@Override
	public void update(final Request<Kitchenware> request, final Kitchenware entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

	

}
