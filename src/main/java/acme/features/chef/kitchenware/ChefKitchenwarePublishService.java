package acme.features.chef.kitchenware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipes.Kitchenware;
import acme.entities.recipes.WareType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefKitchenwarePublishService implements AbstractUpdateService<Chef, Kitchenware>{
	
	@Autowired
	protected ChefKitchenwareRepository repository;

	@Override
	public boolean authorise(final Request<Kitchenware> request) {
		assert request != null;
		int chefId;
		int kitchenwareId;
		Kitchenware kitchenware;
		
		chefId = request.getPrincipal().getActiveRoleId();
		kitchenwareId = request.getModel().getInteger("id");
		kitchenware = this.repository.findOneKitchenwareById(kitchenwareId);
		
		return kitchenware.getChef().getId() == chefId && !kitchenware.isPublished();
	}

	@Override
	public void bind(final Request<Kitchenware> request, final Kitchenware entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "name", "wareType", "description", "retailPrice", "info");
		
	}

	@Override
	public void unbind(final Request<Kitchenware> request, final Kitchenware entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "name", "wareType", "description", "retailPrice", "info");
		model.setAttribute("IngredientType", WareType.INGREDIENT);
		model.setAttribute("KitchenUtensilType", WareType.KITCHEN_UTENSIL);
		model.setAttribute("readOnly", true);
		
	}

	@Override
	public Kitchenware findOne(final Request<Kitchenware> request) {
		assert request != null;
		int id;
		Kitchenware kitchenWare;
		
		id = request.getModel().getInteger("id");
		kitchenWare = this.repository.findOneKitchenwareById(id);
		
		return kitchenWare;
	}

	@Override
	public void validate(final Request<Kitchenware> request, final Kitchenware entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void update(final Request<Kitchenware> request, final Kitchenware entity) {
		assert request != null;
		assert entity != null;
		
		entity.setPublished(true);
		this.repository.save(entity);
		
	}

	

}
