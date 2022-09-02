package acme.features.chef.kitchenwareRecipe;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipes.KitchenwareRecipe;
import acme.entities.recipes.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefKitchenwareRecipeListService implements AbstractListService<Chef, KitchenwareRecipe>{

	@Autowired
	protected ChefKitchenwareRecipeRepository  repository;
	
	
	@Override
	public boolean authorise(final Request<KitchenwareRecipe> request) {
		
		assert request != null;
		final int recipeId = request.getModel().getInteger("recipeId");
		final Recipe recipe = this.repository.findRecipeById(recipeId);
		
		return recipe.getChef().getId() == request.getPrincipal().getActiveRoleId();
	}

	@Override
	public Collection<KitchenwareRecipe> findMany(final Request<KitchenwareRecipe> request) {
		
		assert request != null;
		Collection<KitchenwareRecipe> result;
		final int recipeId = request.getModel().getInteger("recipeId");
		
		result = this.repository.findKitchenwareRecipesByRecipeId(recipeId);
		return result;
		
	}

	@Override
	public void unbind(final Request<KitchenwareRecipe> request, final KitchenwareRecipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "quantity", "unitType", "kitchenware.name", "kitchenware.code", "kitchenware.wareType", "kitchenware.published");
		
	}

}
