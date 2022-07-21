package acme.features.any.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Ingredient;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyIngredientShowService implements AbstractShowService<Any, Ingredient>{

	@Autowired
	protected AnyIngredientRepository repo;
	
	@Override
	public boolean authorise(final Request<Ingredient> request) {
		assert request != null;
		int ingredientId;
		Ingredient ingredient;
		boolean res;
		
		ingredientId = request.getModel().getInteger("id");
		ingredient = this.repo.findOneIngredientById(ingredientId);
		res = ingredient.isPublished();
		return res;
	}

	@Override
	public Ingredient findOne(final Request<Ingredient> request) {
		assert request != null;
		int ingredientId;
		Ingredient ingredient;
		
		ingredientId = request.getModel().getInteger("id");
		ingredient = this.repo.findOneIngredientById(ingredientId);
		
		return ingredient;
	}

	@Override
	public void unbind(final Request<Ingredient> request, final Ingredient entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "name", "description", "retailPrice", "info", "published");
		
	}

	

}
