package acme.features.any.kitchenware.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipes.Kitchenware;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyKitchenwareIngredientShowService implements AbstractShowService<Any, Kitchenware>{

	@Autowired
	protected AnyKitchenwareIngredientRepository repo;
	
	@Override
	public boolean authorise(final Request<Kitchenware> request) {
		assert request != null;
		int ingredientId;
		Kitchenware ingredient;
		boolean res;
		
		ingredientId = request.getModel().getInteger("id");
		ingredient = this.repo.findOneIngredientById(ingredientId);
		res = ingredient.isPublished();
		return res;
	}

	@Override
	public Kitchenware findOne(final Request<Kitchenware> request) {
		assert request != null;
		int ingredientId;
		Kitchenware ingredient;
		
		ingredientId = request.getModel().getInteger("id");
		ingredient = this.repo.findOneIngredientById(ingredientId);
		
		return ingredient;
	}

	@Override
	public void unbind(final Request<Kitchenware> request, final Kitchenware entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "name", "wareType", "description", "retailPrice", "info", "published");
		
	}

	

}
