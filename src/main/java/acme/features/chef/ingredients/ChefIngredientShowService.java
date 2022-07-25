package acme.features.chef.ingredients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Ingredient;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefIngredientShowService implements AbstractShowService<Chef, Ingredient>{

	@Autowired
	protected ChefIngredientRepository repository;
	
	@Override
	public boolean authorise(final Request<Ingredient> request) {
		assert request != null;
		boolean result;
		
		final int ingredientId = request.getModel().getInteger("id");
		
		final Ingredient ingredient = this.repository.findOneIngredientById(ingredientId);
		result = ingredient != null && request.isPrincipal(ingredient.getChef());
		
		return result;
	}

	@Override
	public Ingredient findOne(final Request<Ingredient> request) {
		assert request != null;
		
		final int ingredientId = request.getModel().getInteger("id");
		
		return this.repository.findOneIngredientById(ingredientId);
	}

	@Override
	public void unbind(final Request<Ingredient> request, final Ingredient entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "name", "code", "description", "retailPrice", "info");
		
	}

}
