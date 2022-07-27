package acme.features.any.object.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Object;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyObjectIngredientShowService implements AbstractShowService<Any, Object>{

	@Autowired
	protected AnyObjectIngredientRepository repo;
	
	@Override
	public boolean authorise(final Request<Object> request) {
		assert request != null;
		int ingredientId;
		Object ingredient;
		boolean res;
		
		ingredientId = request.getModel().getInteger("id");
		ingredient = this.repo.findOneIngredientById(ingredientId);
		res = ingredient.isPublished();
		return res;
	}

	@Override
	public Object findOne(final Request<Object> request) {
		assert request != null;
		int ingredientId;
		Object ingredient;
		
		ingredientId = request.getModel().getInteger("id");
		ingredient = this.repo.findOneIngredientById(ingredientId);
		
		return ingredient;
	}

	@Override
	public void unbind(final Request<Object> request, final Object entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "name", "objectType", "description", "retailPrice", "info", "published");
		
	}

	

}
