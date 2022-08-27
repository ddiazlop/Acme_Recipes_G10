package acme.features.chef.kitchenwareRecipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipes.KitchenwareRecipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefKitchenwareRecipeShowService implements AbstractShowService<Chef, KitchenwareRecipe>{

	@Autowired
	protected ChefKitchenwareRecipeRepository  repository;
	
	
	@Override
	public boolean authorise(final Request<KitchenwareRecipe> request) {
		
		assert request != null;
		final int kitchenwareRecipeId = request.getModel().getInteger("id");
		
		final KitchenwareRecipe kitchenware = this.repository.findOneById(kitchenwareRecipeId);
		
		return kitchenware.getRecipe().getChef().getId() == request.getPrincipal().getActiveRoleId();
		
	}

	@Override
	public KitchenwareRecipe findOne(final Request<KitchenwareRecipe> request) {

		assert request != null;
		KitchenwareRecipe result;
		
		final int kitchenwareRecipeId = request.getModel().getInteger("id");
		result = this.repository.findOneById(kitchenwareRecipeId);
		
		return result;
		
	}

	@Override
	public void unbind(final Request<KitchenwareRecipe> request, final KitchenwareRecipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "quantity", "unitType", "kitchenware.name", "kitchenware.code", "kitchenware.description", 
			                          "kitchenware.retailPrice", "kitchenware.wareType", "kitchenware.info", "kitchenware.published");
		
	}

}
